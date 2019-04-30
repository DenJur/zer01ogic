package simulation.gates;

import interfaces.elements.IObservableValue;

/**
 * Binary AND gate
 */
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
