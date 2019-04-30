package interfaces.elements;

/**
 * Interface for objects that are capable to apply read/write transformations to the observable value.
 * Implemented as a proxies which allows to chain multiple transformers .
 *
 * @param <T>
 */
public interface IValueTransformer<T> extends IObservableValue<T> {
    /**
     * Assigns inner observable value that all value changes will get forwarded to
     *
     * @param value - inner observable value
     */
    void setInnerValue(IObservableValue<T> value);
}
