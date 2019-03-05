package circuits.gates;

import circuits.values.MultibitValue;
import interfaces.ICircuitQueue;
import interfaces.ILogicElement;
import interfaces.IObservableValue;
import interfaces.IValueTransformer;

import java.util.*;

public abstract class BaseLogicGate implements ILogicElement {
    protected static final int maxWireValue = 0xFFFFFFFF;
    protected IObservableValue<Integer> output;
    protected ArrayList<IObservableValue<Integer>> inputs;
    private ICircuitQueue parent;

    public BaseLogicGate(byte outputSize) {
        inputs = new ArrayList<>();
        output = new MultibitValue(0, outputSize);
    }

    @Override
    public void addInput(IObservableValue input) {
        if (Integer.class.isAssignableFrom(input.getValueType()) && !inputs.contains(input)) {
            inputs.add(input);
            input.registerObserver(this);
        }
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        return Collections.singletonList(output);
    }

    @Override
    public IObservableValue getOutputByIndex(int index) {
        if (index != 0) return null;
        return output;
    }

    @Override
    public void setParentCircuit(ICircuitQueue circuit) {
        parent = circuit;
    }

    @Override
    public void update() {
        if (parent != null) {
            parent.queueElementForUpdate(this);
        }
    }

    @Override
    public void reset() {
        output.reset();
    }

    public IObservableValue<Integer> getOutput() {
        return output;
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (inputs.contains(value)) {
                inputs.remove(value);
                inputs.add(transformer);
            }
            if(output==value){
                output=transformer;
            }
        }
    }
}
