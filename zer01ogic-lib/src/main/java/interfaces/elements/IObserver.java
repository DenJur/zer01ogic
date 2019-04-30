package interfaces.elements;

/**
 * Observer interface for the observable value system
 */
public interface IObserver {
    /**
     * Method called when observable value notifies observer about the change
     *
     * @param source - observable value that issued the update
     */
    void update(IObservableValue source);
}
