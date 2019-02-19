package circuits.gates;

import interfaces.IObservableValue;

public class NandGate extends BaseLogicGate {

    public NandGate(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void update() {
    }

    @Override
    public void calculateOutputs() {
        Integer result = maxWireValue;
        for (IObservableValue<Integer> input : inputs) {
            result &= input.getValue();
        }
        output.setValue(~result);
    }
}
