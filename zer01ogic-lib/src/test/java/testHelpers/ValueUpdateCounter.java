package testHelpers;

import interfaces.elements.IObservableValue;
import interfaces.elements.IObserver;

public class ValueUpdateCounter implements IObserver {
    public long count = 0;

    @Override
    public void update(IObservableValue value) {
        count++;
    }
}
