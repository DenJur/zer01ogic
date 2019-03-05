package interfaces;

public interface IValueTransformer<T> extends IObservableValue<T> {
    void setInnerValue(IObservableValue<T> value);
}
