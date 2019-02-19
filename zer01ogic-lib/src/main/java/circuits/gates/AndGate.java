package circuits.gates;

import interfaces.IObservableValue;

public class AndGate extends BaseLogicGate {

    public AndGate(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void calculateOutputs() {
        Integer result = maxWireValue;
        for (IObservableValue<Integer> input : inputs) {
            result &= input.getValue();
        }
        output.setValue(result);
    }
}
