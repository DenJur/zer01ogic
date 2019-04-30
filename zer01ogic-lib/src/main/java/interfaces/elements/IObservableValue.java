package interfaces.elements;

/**
 * Observable value interface using generics
 *
 * @param <T> - type that observable value returns value as
 */
public interface IObservableValue<T> {
    /**
     * Get current value of observable
     *
     * @return - value
     */
    T getValue();

    /**
     * Sets new value to the observable
     *
     * @param newValue - new value
     */
    void setValue(T newValue);

    /**
     * Register observer to receive notification about value changes
     *
     * @param IObserver - observer to be notified
     */
    void registerObserver(IObserver IObserver);

    /**
     * Stop sending notifications to specified observer
     *
     * @param IObserver - observer that should be deregistered
     */
    void deregisterObserver(IObserver IObserver);

    /**
     * Reset value to the original
     */
    void reset();

    /**
     * Due to limitations of Java generics need to manually keep track of generic type
     *
     * @return - type of the value that observable returns
     */
    Class<T> getValueType();
}
