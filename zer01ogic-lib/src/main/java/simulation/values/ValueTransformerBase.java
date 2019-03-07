package simulation.values;

import interfaces.elements.IObservableValue;
import interfaces.elements.IObserver;
import interfaces.elements.IValueTransformer;

public abstract class ValueTransformerBase<T> implements IValueTransformer<T> {
    protected IObservableValue<T> value;
    protected TransformerMode mode;

    public ValueTransformerBase(TransformerMode mode){
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
