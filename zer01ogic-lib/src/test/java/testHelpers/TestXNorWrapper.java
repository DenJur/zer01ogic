package testHelpers;

import circuits.gates.XorGate;
import circuits.values.NotSetTransformWrapper;
import interfaces.ICircuitElementRegister;

public class TestXNorWrapper extends BaseGateWrapper {
    public ValueUpdateCounter counter;

    public TestXNorWrapper(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        gate = new XorGate((byte) 1);
        output = gate.getOutputByIndex(0);
        gate.addValueTransformer(output,new NotSetTransformWrapper());
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
