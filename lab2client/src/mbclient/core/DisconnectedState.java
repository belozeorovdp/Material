package mbclient.core;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import mbcommon.Constants;
import mbcommon.IClient;
import mbcommon.IServer;
import mbclient.core.impl.Options;
import mbclient.exception.ExceptionHandler;


/**
 * O
 * @author hajo
 *
 */
public class DisconnectedState implements IState {
	public IServer connect(IClient client) throws IllegalStateException {
		IServer server = null;
		try {
			Registry registry = LocateRegistry.getRegistry(
					Options.getRegistryIp(), Options.getRegistryPort());
			server = (IServer) registry.lookup(Constants.MB_SERVER_NAME);
					
			UnicastRemoteObject.exportObject(client, Options.getMyPort());
			server.registerClient(client);
		} catch (RemoteException e) {
			ExceptionHandler.rethrow(e, e.getMessage());
		} catch (NotBoundException e) {
			ExceptionHandler.rethrow(e, e.getMessage());
		}
		return server;
	}
	
	public void disconnect(IServer server, IClient client) throws IllegalStateException {
		throw new IllegalStateException("IllegalStateException: Not connected.");
	}
}
