package simulation.values;

import interfaces.elements.IObservableValue;
import interfaces.elements.IObserver;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class MultibitValue implements IObservableValue<Integer> {
    private Integer value;
    private LinkedHashSet<IObserver> observers;
    private byte valueBitSize;
    private final Integer initialValue;

    public MultibitValue() {
        this(0,(byte)1);
    }

    public MultibitValue(Integer initialValue) {
        this(initialValue, (byte)1);
    }

    public MultibitValue(Integer initialValue, byte bitSize) {
        observers = new LinkedHashSet<>();
        valueBitSize = bitSize;
        this.setValue(initialValue);
        this.initialValue=initialValue;
    }

    public byte getValueBitSize() {
        return valueBitSize;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public synchronized void setValue(Integer newValue) {
        if(newValue==null) return;
        newValue = (newValue << (Integer.SIZE - valueBitSize)) >>> (Integer.SIZE - valueBitSize);
        if (!newValue.equals(value)) {
            value = newValue;
            notifyObservers();
        }
    }

    @Override
    public synchronized void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public synchronized void deregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public synchronized void reset() {
        this.setValue(initialValue);
    }

    @Override
    public Class<Integer> getValueType() {
        return Integer.class;
    }

    private synchronized void notifyObservers() {
        Iterator<IObserver> i=observers.iterator();
        i.forEachRemaining(iObserver -> iObserver.update(this));
    }
}
