package simulation.gates;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;

import java.util.Collections;
import java.util.List;

/**
 * Single input, single output NOT gate
 */
public class NotGate implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> input;
    private ICircuitQueue parent;

    public NotGate(byte outputSize) {
        output = new MultibitValue(0, outputSize);
        NotTransform wrapper = new NotTransform(TransformerMode.SET);
        wrapper.setInnerValue(output);
        output = wrapper;
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        return Collections.singletonList(output);
    }

    /**
     * Set input observable. If input was previously set, it will be overwritten.
     *
     * @param input
     */
    public void setInput(IObservableValue input) {
        if (this.input != null) this.input.deregisterObserver(this);
        this.input = input;
        input.registerObserver(this);
    }

    @Override
    public void calculateOutputs() {
        output.setValue(input.getValue());
    }

    @Override
    public void setParentCircuit(ICircuitQueue circuit) {
        parent = circuit;
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (input == value) {
                input = transformer;
            }
            if (output == value) {
                output = transformer;
            }
        }
    }

    @Override
    public void reset() {
        output.reset();
    }

    @Override
    public void update(IObservableValue source) {
        if (parent != null) {
            parent.queueElementForUpdate(this);
        }
    }

    /**
     * Output observable getter
     *
     * @return - output observable value
     */
    public IObservableValue<Integer> getOutput() {
        return output;
    }
}
