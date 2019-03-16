package simulation.gates;

import simulation.values.MultibitValue;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;
import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;

import java.util.Collections;
import java.util.List;

public class NotGate implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> input;
    private ICircuitQueue parent;

    public NotGate(byte outputSize) {
        output = new MultibitValue(0, outputSize);
        NotTransform wrapper=new NotTransform(TransformerMode.SET);
        wrapper.setInnerValue(output);
        output=wrapper;
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
    public void addInput(IObservableValue input) {
        this.input=input;
    }

    @Override
    public void calculateOutputs() {
        output.setValue(input.getValue());
    }

    @Override
    public void setParentCircuit(ICircuitQueue circuit) {
        parent=circuit;
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (input==value) {
                input=transformer;
            }
            if(output==value){
                output=transformer;
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

    public IObservableValue<Integer> getOutput() {
        return output;
    }
}
