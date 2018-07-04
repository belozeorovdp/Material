package mbclient.core.impl;

import java.rmi.RemoteException;

import mbcommon.IClient;
import mbcommon.IServer;
import mbcommon.Constants.StateChanges;
import mbclient.core.ConnectedState;
import mbclient.core.DisconnectedState;
import mbclient.core.ILocalClient;
import mbclient.core.IState;
import mbclient.exception.ExceptionHandler;


/**
 * Handles the states and transitions between them
 * @author hajo
 *
 */
final class StateContext {
	private IState currentState;
	private final IState connectedState = new ConnectedState();
	private final IState disconnectedState = new DisconnectedState();
	
	public StateContext() {
		currentState = disconnectedState;
	}
		
	public IServer connect(ILocalClient client) {
		IServer server = null;
		try {
			client.setUser(Options.getUser());
			server = currentState.connect((IClient) client);
		} catch (IllegalStateException e) {
			ExceptionHandler.rethrow(e, e.getMessage());
		} 
		
		currentState = connectedState;
		
		return server;
	}
	
	public void disconnect(IServer server, ILocalClient client) {
		try {
			currentState.disconnect(server, (IClient) client);
		} catch (IllegalStateException e) {
			System.exit(0);
		} catch (RemoteException e) {
			currentState = disconnectedState;
			client.getObservers().firePropertyChange(
					StateChanges.DISCONNECTED.toString(), server,
					server = null);
			ExceptionHandler.rethrow(e, e.getMessage());
		}
		
		currentState = disconnectedState;
	}
}
