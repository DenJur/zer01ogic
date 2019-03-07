package interfaces.circuits;

import interfaces.elements.ILogicElement;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IScheduledLogicElement;

public interface ICircuitElementRegister {
    void addCircuitWorkingElement(ILogicElementFrontEnd source, ILogicElement item);
    void addCircuitWorkingElement(ILogicElementFrontEnd source, IScheduledLogicElement item);
    ILogicElement getWorkingElementFor(ILogicElementFrontEnd source);
}
