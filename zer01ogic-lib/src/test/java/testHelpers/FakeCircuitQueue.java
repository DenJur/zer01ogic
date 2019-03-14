package testHelpers;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;

public class FakeCircuitQueue implements ICircuitQueue {
    @Override
    public void queueElementForUpdate(ILogicElement item) {
        item.calculateOutputs();
    }
}
