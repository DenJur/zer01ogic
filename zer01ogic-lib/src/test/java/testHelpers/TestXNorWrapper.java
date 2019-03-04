package testHelpers;

import circuits.gates.XorGate;
import circuits.values.NotTransform;
import circuits.values.TransformerMode;
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
        gate.addValueTransformer(output,new NotTransform(TransformerMode.SET));
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
