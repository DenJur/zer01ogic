package circuits.gates;

import circuits.values.MultibitValue;
import circuits.values.NotTransformWrapper;
import circuits.values.TransformerMode;
import interfaces.ICircuitQueue;
import interfaces.ILogicElement;
import interfaces.IObservableValue;
import interfaces.IValueTransformer;

import java.util.Collections;
import java.util.List;

public class NotGate implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> input;
    private ICircuitQueue parent;

    public NotGate(byte outputSize) {
        output = new MultibitValue(0, outputSize);
        NotTransformWrapper wrapper=new NotTransformWrapper(TransformerMode.SET);
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
    public void update() {
        if (parent != null) {
            parent.queueElementForUpdate(this);
        }
    }

    public IObservableValue<Integer> getOutput() {
        return output;
    }
}
