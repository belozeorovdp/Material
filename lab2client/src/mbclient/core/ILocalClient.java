package mbclient.core;

import java.beans.PropertyChangeSupport;
import java.util.List;

import mbcommon.IMessage;
import mbcommon.IServer;
import mbcommon.User;

/**
 * Contract for client (from GUI view)
 *
 * @author hajo
 *
 */
public interface ILocalClient {
	public void setServer(IServer server);
	public void connect();
	public void disconnect();
	public void updateMessage(IMessage msg);
	public void setUser(User user);
	public PropertyChangeSupport getObservers();
}
