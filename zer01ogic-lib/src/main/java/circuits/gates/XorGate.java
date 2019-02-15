package circuits.gates;

import interfaces.IObservableValue;

public class XorGate extends BaseLogicGate {

    public XorGate(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void update() {
        Integer result = 0;
        for (IObservableValue<Integer> input : inputs) {
            result ^= input.getValue();
        }
        output.setValue(result);
    }
}