package circuits.gates;

import interfaces.IObservableValue;

public class NotGate extends BaseLogicGate {

    public NotGate(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void addInput(IObservableValue<Integer> input) {
        if (inputs.size() == 0)
            inputs.add(input);
        else
            inputs.set(0, input);
        input.registerObserver(this);
    }

    @Override
    public void calculateOutputs() {
        if (inputs.size() > 0) {
            output.setValue(~(inputs.get(0).getValue()));
        }
    }

    @Override
    public void update() {
    }
}
