package mbclient.core;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import mbcommon.IClient;
import mbcommon.IServer;
import mbcommon.MBFile;
import mbclient.core.impl.Options;
import mbclient.exception.ExceptionHandler;
import mbclient.exception.MBClientException;


/**
 * O
 * @author hajo
 *
 */
public class ConnectedState implements IState {
	public IServer connect(IClient client) throws IllegalStateException{
		throw new IllegalStateException("IllegalStateException: Already connected.");
	}
	
	public void disconnect(IServer server, IClient client) throws IllegalStateException, RemoteException {
		server.removeClient(client);
		UnicastRemoteObject.unexportObject(client, false);
	}
}