package circuits.gates;

import interfaces.IObservableValue;

public class OrGate extends BaseLogicGate {

    public OrGate(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void calculateOutputs() {
        Integer result = 0;
        for (IObservableValue<Integer> input : inputs) {
            result |= input.getValue();
        }
        output.setValue(result);
    }
}
