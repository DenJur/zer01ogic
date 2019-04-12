package simulation.arithmetic;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

public class Comparer implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> inputA;
    protected IObservableValue<Integer> inputB;
    private ICircuitQueue parent;

    public Comparer(byte outputSize) {
        output = new MultibitValue(0, outputSize);
    }

    @Override
    public void addInput(IObservableValue input) {
        //TODO throw
    }

    @Override
    public void calculateOutputs() {
        Integer valueA = 0;
        Integer valueB = 0;
        if (inputA != null) valueA = inputA.getValue();
        if (inputB != null) valueB = inputB.getValue();
        output.setValue(Integer.compareUnsigned(valueA, valueB));
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
    public void update(IObservableValue source) {
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

    public IObservableValue<Integer> getInputA() {
        return inputA;
    }

    public void setInputA(IObservableValue<Integer> inputA) {
        if (Integer.class.isAssignableFrom(inputA.getValueType()))
            this.inputA = inputA;
    }

    public IObservableValue<Integer> getInputB() {
        return inputB;
    }

    public void setInputB(IObservableValue inputB) {
        if (Integer.class.isAssignableFrom(inputB.getValueType()))
            this.inputB = inputB;
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (output == value) {
                output = transformer;
            } else if (inputA == value) {
                inputA = transformer;
            } else if (inputB == value) {
                inputB = transformer;
            }
        }
    }
}
