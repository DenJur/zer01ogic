package interfaces.circuits;

import interfaces.elements.ILogicElement;

public interface ICircuitQueue {
    void queueElementForUpdate(ILogicElement item);
}
