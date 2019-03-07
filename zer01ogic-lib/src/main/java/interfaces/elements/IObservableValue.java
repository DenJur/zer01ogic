package interfaces.elements;

public interface IObservableValue<T> {
    T getValue();
    void setValue(T newValue);
    void registerObserver(IObserver IObserver);
    void deregisterObserver(IObserver IObserver);
    void reset();
    Class<T> getValueType();
}
