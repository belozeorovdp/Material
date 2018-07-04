package mbclient.core;

import java.rmi.RemoteException;

import mbcommon.IClient;
import mbcommon.IServer;


/**
 * The contract for the different states
 * @author hajo
 *
 */
public interface IState {
	public IServer connect(IClient client) throws IllegalStateException;
	public void disconnect(IServer server, IClient client) throws IllegalStateException, RemoteException;
}
