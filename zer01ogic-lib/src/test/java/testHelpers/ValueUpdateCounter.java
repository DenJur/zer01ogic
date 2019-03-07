package testHelpers;

import interfaces.elements.IObserver;

public class ValueUpdateCounter implements IObserver {
    public long count=0;

    @Override
    public void update() {
        count++;
    }
}
