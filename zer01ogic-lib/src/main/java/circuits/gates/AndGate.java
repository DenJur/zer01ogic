package circuits.gates;

import interfaces.IObservableValue;

public class AndGate extends BaseLogicGate {

    public AndGate(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void update() {
        Integer result = 0xFFFFFFFF;
        for (IObservableValue<Integer> input : inputs) {
            result &= input.getValue();
        }
        output.setValue(result);
    }
}
