package simulation.values;

import interfaces.elements.IObservableValue;
import interfaces.elements.IObserver;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Observable value that uses Integer as the data storage type and can be used for representing 1 to 32 bit values.
 */
public class MultibitValue implements IObservableValue<Integer> {
    private final Integer initialValue;
    private Integer value;
    private LinkedHashSet<IObserver> observers;
    private byte valueBitSize;

    public MultibitValue() {
        this(0, (byte) 1);
    }

    public MultibitValue(Integer initialValue) {
        this(initialValue, (byte) 1);
    }

    public MultibitValue(Integer initialValue, byte bitSize) {
        observers = new LinkedHashSet<>();
        valueBitSize = bitSize;
        this.setValue(initialValue);
        this.initialValue = initialValue;
    }

    /**
     * Get bit size of the value this observable represents
     *
     * @return - bit size of value
     */
    public byte getValueBitSize() {
        return valueBitSize;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public synchronized void setValue(Integer newValue) {
        if (newValue == null) return;
        //truncate value to bit size
        newValue = (newValue << (Integer.SIZE - valueBitSize)) >>> (Integer.SIZE - valueBitSize);
        //only change if value is different than the current value
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

    /**
     * Notify all observers that subscribed to this value
     */
    private synchronized void notifyObservers() {
        Iterator<IObserver> i = observers.iterator();
        i.forEachRemaining(iObserver -> iObserver.update(this));
    }
}
