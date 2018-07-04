package mbclient.core.impl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import mbcommon.Constants;
import mbcommon.Constants.StateChanges;
import mbcommon.IClient;
import mbcommon.IMessage;
import mbcommon.IServer;
import mbcommon.User;
import mbclient.core.ILocalClient;
import mbclient.core.IObservable;
import mbclient.exception.ExceptionHandler;

/**
 * Implementation of the many interfaces. The "real" client
 * Serializable important! Singleton
 * 
 * @author hajo
 */
public class Client implements IClient, ILocalClient, IObservable, Serializable {

   // Important, client will be serialized
   private static final long serialVersionUID = Constants.SERIAL_V_ID;

   // Server doesn't use these so make'em transient
   private transient final PropertyChangeSupport observers = new PropertyChangeSupport(this);
   private transient final List<User> users = new ArrayList<User>();
   private transient final StateContext context = new StateContext();
   private transient final Timer timer = new Timer();
   private transient IServer server;   

   // Used on server side
   private User user;

   // This is also a Singleton
   private static Client instance;

   public static Client getInstance() throws RemoteException {
      if (instance == null) {
         instance = new Client();
      }
      return instance;
   }
   
   private Client() throws RemoteException {
		timer.schedule(new PingServer(), 3000, 3000);
   }
   
   class PingServer extends TimerTask {
		public void run() {
			try {
				if (server != null)
					server.ping(user);
			} catch (RemoteException e) {
				disconnect();
				ExceptionHandler.rethrow(e, e.getMessage());
			}
		}
  }
   
   // ILocalClient -------------------------------------------------------
    
   @Override
   public void setServer(IServer server){
	   this.server = server;
   }
   
	@Override
	public void connect() {
		try {
			observers.firePropertyChange(
					StateChanges.CONNECTED.toString(), null,
					server = context.connect(this));
		} catch (IllegalArgumentException e) {
			ExceptionHandler.rethrow(e, e.getMessage());
		}
	}
   
   @Override
   public void disconnect() {
	   context.disconnect(server, this);
	   
	   observers.firePropertyChange(
				StateChanges.DISCONNECTED.toString(), server,
				server = null);
   }

   @Override
   public void updateMessage(IMessage msg) {
	   try {
		   server.printMessage(msg);
	} catch (RemoteException e) {
		disconnect();
		ExceptionHandler.rethrow(e, e.getMessage());
	}
   }
   
   @Override
   public void setUser(User user){
	   this.user = user;
   }
   
   @Override
   public PropertyChangeSupport getObservers() {
	   return observers;
   }
   
   // IClient --------------------------------------------------------------------
   
   @Override
   public void updateUsers(List<User> users) {
	   this.users.clear();
	   for (User u : users)
		   this.users.add((u));
	   
		observers.firePropertyChange(
				StateChanges.USERS.toString(), null,
				users);   	
   }
   
   @Override
   public void updateMessages(IMessage msg) throws RemoteException {
	   observers.firePropertyChange(
				StateChanges.MESSAGE.toString(), null,
				msg); 
   }
   
   @Override
   public User getUser() throws RemoteException {
	   return user;
   }
   
   // IPeer ------------------------------------------------------------------------

   // TODO
   
   // IObservable ---------------------------------------------------------------

   @Override
   public void addObserver(PropertyChangeListener observer) {
       observers.addPropertyChangeListener(observer);
   }

   @Override
   public void removeObserver(PropertyChangeListener observer) {
       observers.removePropertyChangeListener(observer);
   }
}
