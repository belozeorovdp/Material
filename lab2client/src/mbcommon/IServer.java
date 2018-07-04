package mbcommon;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Client view of server
 * 
 * @author hajo
 * 
 */
public interface IServer extends Remote {
	// To see if we got a connection
	public String ping(User user) throws RemoteException;
	public void printMessage(IMessage m) throws RemoteException;
	public void registerClient(IClient c) throws RemoteException, IllegalArgumentException;
	public boolean removeClient(IClient c) throws RemoteException;
}
