package circuits.gates;

import interfaces.IObservableValue;

public class NorGate extends BaseLogicGate {

    public NorGate(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void update() {
    }

    @Override
    public void calculateOutputs() {
        Integer result = 0;
        for (IObservableValue<Integer> input : inputs) {
            result |= input.getValue();
        }
        output.setValue(~result);
    }
}
