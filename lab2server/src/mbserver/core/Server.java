package mbserver.core;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import sun.net.dns.ResolverConfiguration.Options;

import common.IClient;
import common.IMessage;
import common.IServer;
import common.User;
import mbserver.view.MainFrame;

/**
 * The server
 * 
 * @author hajo
 * 
 */
public class Server implements IServer {

	private final int TIMER_DELAY = 5000; // Possible for reclaiming clients

	private MainFrame gui;
	private final int registryPort;
	private final int serverPort;
	private final List<User> users;
	private final List<User> connectedUsers;
	private final Map<User, IClient> clients;
	private final Timer timer;

	public Server(int registryPort, int serverPort) {
		this.registryPort = registryPort;
		this.serverPort = serverPort;
		users = new ArrayList<User>();
		connectedUsers = new ArrayList<User>();
		clients = new ConcurrentHashMap<User, IClient>();
		timer = new Timer();
		timer.schedule(new ClearDeadClients(), 1000, TIMER_DELAY);
	}

	// IServer ------------------------------------------------

	@Override
	public String ping(User user) throws RemoteException {
		if (!connectedUsers.contains(user))
			connectedUsers.add(user);
		
		return "Alive at serverPort " + serverPort + ". Registry port is "
				+ registryPort;
	}

	@Override
	public void printMessage(IMessage m) throws RemoteException {
		for (IClient client : clients.values())
			client.updateMessages(m);

		gui.update(m);
	}

	@Override
	public void registerClient(IClient c) throws RemoteException,
			IllegalArgumentException {
		User user = c.getUser();

		for (User registeredUser : users)
			if (registeredUser.getName().equals(user.getName()))
			throw new IllegalArgumentException("IllegalArgumentException: "
					+ "User " + user.getName() + " already registered.");

		users.add(user);
		connectedUsers.add(user);
		clients.put(user, c);		

		for (IClient client : clients.values())
			client.updateUsers(users);

		gui.update(clients);
	}

	@Override
	public boolean removeClient(IClient c) throws RemoteException {
		User user = c.getUser();
		
		if (!users.contains(user))
			return false;

		users.remove(user);
		connectedUsers.remove(user);
		clients.remove(user);

		for (IClient client : clients.values())
			client.updateUsers(users);

		gui.update(clients);

		return true;
	}

	// Simple connected GUI -----------------------------------

	public void setView(MainFrame gui) {
		this.gui = gui;
	}

	public int getRegistryPort() {
		return registryPort;
	}

	public int getServerPort() {
		return serverPort;
	}

	// Privates --------------------------------------------
	
	class ClearDeadClients extends TimerTask {
		public void run() {
			synchronized (users) {
				Iterator<User> it = users.iterator();
				while (it.hasNext()) {
					User user = it.next();
					if (!connectedUsers.contains(user))	{
						it.remove();
						clients.remove(user);
												
						for (IClient client : clients.values())
							try {
								client.updateUsers(users);
							} catch (RemoteException e) {
								e.printStackTrace();
							}
					gui.update(clients);
					}
				}			
				connectedUsers.clear();
			}			
		}
	}
}