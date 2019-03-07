package testHelpers;

import simulation.gates.AndGate;
import interfaces.circuits.ICircuitElementRegister;

public class TestAndWrapper extends BaseGateWrapper {

    public TestAndWrapper(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        gate = new AndGate(outputSize);
        output = gate.getOutputByIndex(0);
        register.addCircuitWorkingElement(this, gate);
    }
}
