package simulation.arithmetic;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Arrays;
import java.util.List;

public class Comparer implements ILogicElement {
    protected IObservableValue<Integer> outputLess;
    protected IObservableValue<Integer> outputMore;
    protected IObservableValue<Integer> outputEquals;
    protected IObservableValue<Integer> inputA;
    protected IObservableValue<Integer> inputB;
    private ICircuitQueue parent;

    public Comparer(byte outputSize) {
        outputLess = new MultibitValue(0, (byte) 1);
        outputMore = new MultibitValue(0, (byte) 1);
        outputEquals = new MultibitValue(0, (byte) 1);
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
        switch (Integer.compareUnsigned(valueA, valueB)) {
            case -1:
                outputLess.setValue(1);
                outputEquals.setValue(0);
                outputMore.setValue(0);
                break;
            case 0:
                outputLess.setValue(0);
                outputEquals.setValue(1);
                outputMore.setValue(0);
                break;
            case 1:
                outputLess.setValue(0);
                outputEquals.setValue(0);
                outputMore.setValue(1);
                break;
        }
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        return Arrays.asList(outputLess, outputEquals, outputMore);
    }

    @Override
    public IObservableValue getOutputByIndex(int index) {
        switch (index) {
            case 0:
                return outputLess;
            case 1:
                return outputEquals;
            case 2:
                return outputMore;
            default:
                return null;
        }
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
        outputLess.reset();
        outputMore.reset();
        outputEquals.reset();
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
            if (outputEquals == value) {
                outputEquals = transformer;
            } else if (outputLess == value) {
                outputLess = transformer;
            } else if (outputMore == value) {
                outputMore = transformer;
            } else if (inputA == value) {
                inputA = transformer;
            } else if (inputB == value) {
                inputB = transformer;
            }
        }
    }
}
