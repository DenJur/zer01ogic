package testHelpers;

import circuits.gates.XnorGate;
import interfaces.ICircuitElementRegister;

public class TestXNorWrapper extends BaseGateWrapper {
    public ValueUpdateCounter counter;

    public TestXNorWrapper(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        gate = new XnorGate((byte) 1);
        output = gate.getOutputByIndex(0);
        register.addCircuitWorkingElement(this, gate);
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        super.connectLogicElementInputs(register);
        counter = new ValueUpdateCounter();
        output.registerObserver(counter);
    }
}
