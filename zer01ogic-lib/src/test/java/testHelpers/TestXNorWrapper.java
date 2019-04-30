package testHelpers;

import interfaces.circuits.ICircuitElementRegister;
import simulation.gates.XorGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;

public class TestXNorWrapper extends BaseGateWrapper {
    public ValueUpdateCounter counter;

    public TestXNorWrapper(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        gate = new XorGate((byte) 1);
        output = gate.getOutputByIndex(0);
        gate.addValueTransformer(output, new NotTransform(TransformerMode.SET));
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
