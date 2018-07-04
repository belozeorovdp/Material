package mbclient.util;


import java.rmi.RemoteException;

import mbcommon.IClient;
import mbclient.core.ILocalClient;
import mbclient.core.IObservable;
import mbclient.core.impl.Client;

/**
 * A class returning services (interfaces)
 * to other other parts of application.
 * 
 * 
 * @author hajo
 *
 */
public final class ResourceLocator {

	public static IClient getClient() throws RemoteException {
		return (IClient) Client.getInstance();
	}
	
	public static ILocalClient getLocalClient() throws RemoteException {
		return (ILocalClient) Client.getInstance();
	}
	
	public static IObservable getClientAsObservable() throws RemoteException {
		return (IObservable) Client.getInstance();
	}
}
