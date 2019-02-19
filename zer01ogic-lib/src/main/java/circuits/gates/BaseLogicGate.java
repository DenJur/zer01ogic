package circuits.gates;

import circuits.values.MultibitValue;
import interfaces.ILogicElement;
import interfaces.IObservableValue;

import java.util.ArrayList;

public abstract class BaseLogicGate implements ILogicElement<Integer> {
    protected IObservableValue<Integer> output;
    protected ArrayList<IObservableValue<Integer>> inputs;
    protected static final int maxWireValue = 0xFFFFFFFF;

    public BaseLogicGate(byte outputSize) {
        inputs = new ArrayList<>();
        output = new MultibitValue(0, outputSize);
    }

    @Override
    public void addInput(IObservableValue<Integer> input) {
        inputs.add(input);
        input.registerObserver(this);
    }

    @Override
    public ArrayList<IObservableValue<Integer>> getOutputs() {
        return new ArrayList<IObservableValue<Integer>>() {
            {
                add(output);
            }
        };
    }

    public IObservableValue<Integer> getOutput(){
        return output;
    }
}
