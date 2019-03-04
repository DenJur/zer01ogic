package circuits.values;

import interfaces.IObservableValue;
import interfaces.IObserver;
import interfaces.IValueTransformer;

public abstract class ValueWrapper<T> implements IValueTransformer<T> {
    protected IObservableValue<T> value;
    protected TransformerMode mode;

    public ValueWrapper(TransformerMode mode){
        this.mode=mode;
    }

    @Override
    public T getValue() {
        return value.getValue();
    }

    @Override
    public void setValue(T newValue) {
        value.setValue(newValue);
    }

    @Override
    public void setInnerValue(IObservableValue<T> value) {
        this.value=value;
    }

    @Override
    public void registerObserver(IObserver IObserver) {
        value.registerObserver(IObserver);
    }

    @Override
    public void deregisterObserver(IObserver IObserver) {
        value.deregisterObserver(IObserver);
    }

    @Override
    public void reset() {
        value.reset();
    }
}
