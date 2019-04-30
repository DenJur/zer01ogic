package testHelpers;

import interfaces.circuits.ICircuitElementRegister;
import simulation.gates.AndGate;

public class TestAndWrapper extends BaseGateWrapper {
    public ValueUpdateCounter counter;

    public TestAndWrapper(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        gate = new AndGate(outputSize);
        output = gate.getOutputByIndex(0);
        counter = new ValueUpdateCounter();
        output.registerObserver(counter);
        register.addCircuitWorkingElement(this, gate);
    }
}
