package mbcommon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Contract for client (from Server view)
 * Callbacks from server
 *
 * @author hajo
 *
 */
public interface IClient extends Remote {
	public void updateUsers(List<User> users) throws RemoteException;
	public void updateMessages(IMessage msg) throws RemoteException;
    public User getUser() throws RemoteException;
}
