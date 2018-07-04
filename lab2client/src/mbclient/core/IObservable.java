package mbclient.core;

import java.beans.PropertyChangeListener;



/**
 * Anything that can be observed, observer pattern.
 *
 * @author hajo
 *
 */
public interface IObservable {
    public void addObserver(PropertyChangeListener observer);
    public void removeObserver(PropertyChangeListener observer);
}
