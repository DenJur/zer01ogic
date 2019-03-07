package interfaces.elements;

import interfaces.circuits.ICircuitElementRegister;

public interface ILogicElementFrontEnd {
    void createLogicElement(ICircuitElementRegister register);
    void connectLogicElementInputs(ICircuitElementRegister register);
    void reset();
}
