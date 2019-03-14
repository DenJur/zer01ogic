package testHelpers;

import simulation.gates.AndGate;
import interfaces.circuits.ICircuitElementRegister;

public class TestAndWrapper extends BaseGateWrapper {
    public ValueUpdateCounter counter;

    public TestAndWrapper(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        gate = new AndGate(outputSize);
        output = gate.getOutputByIndex(0);
        counter=new ValueUpdateCounter();
        output.registerObserver(counter);
        register.addCircuitWorkingElement(this, gate);
    }
}
