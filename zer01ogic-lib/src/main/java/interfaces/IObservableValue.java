package interfaces;

public interface IObservableValue<T> {
    T getValue();
    void setValue(T newValue);
    void registerObserver(IObserver IObserver);
    void deregisterObserver(IObserver IObserver);
}
