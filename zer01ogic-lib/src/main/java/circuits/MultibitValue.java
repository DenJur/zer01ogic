package circuits;

import interfaces.IObservableValue;
import interfaces.IObserver;

import java.util.HashSet;

public class MultibitValue implements IObservableValue<Integer> {
    private Integer value;
    private HashSet<IObserver> observers;
    private byte valueBitSize;

    public MultibitValue() {
        value = 0;
        observers = new HashSet<>();
        valueBitSize = 1;
    }

    public MultibitValue(Integer initialValue) {
        observers = new HashSet<>();
        valueBitSize = 1;
        this.setValue(initialValue);
    }


    public MultibitValue(Integer initialValue, byte bitSize) {
        observers = new HashSet<>();
        valueBitSize = bitSize;
        this.setValue(initialValue);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer newValue) {
        if(newValue==null) return;
        newValue = (newValue << (Integer.SIZE - valueBitSize)) >>> (Integer.SIZE - valueBitSize);
        if (!newValue.equals(value)) {
            value = newValue;
            notifyObservers();
        }
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        // TODO Queue updates in the circuit object
    }
}
