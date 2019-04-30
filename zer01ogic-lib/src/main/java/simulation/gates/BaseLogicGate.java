package simulation.gates;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for logic gates that have any number of inputs and single output
 */
public abstract class BaseLogicGate implements ILogicElement {
    protected static final int maxWireValue = 0xFFFFFFFF;
    protected IObservableValue<Integer> output;
    protected ArrayList<IObservableValue<Integer>> inputs;
    private ICircuitQueue parent;

    public BaseLogicGate(byte outputSize) {
        inputs = new ArrayList<>();
        output = new MultibitValue(0, outputSize);
    }

    /**
     * Add new input value to the gate
     *
     * @param input - input observable value to add
     */
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
    public void setParentCircuit(ICircuitQueue circuit) {
        parent = circuit;
    }

    @Override
    public void update(IObservableValue source) {
        if (parent != null) {
            parent.queueElementForUpdate(this);
        }
    }

    @Override
    public void reset() {
        output.reset();
    }

    /**
     * Output observable getter
     *
     * @return - output observable value
     */
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
            if (output == value) {
                output = transformer;
            }
        }
    }
}
