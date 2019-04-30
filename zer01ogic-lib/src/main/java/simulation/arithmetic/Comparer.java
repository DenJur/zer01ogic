package simulation.arithmetic;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Arrays;
import java.util.List;

/**
 * Binary comparer element
 */
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
    public void calculateOutputs() {
        //use 0 as default value
        Integer valueA = 0;
        Integer valueB = 0;
        if (inputA != null) valueA = inputA.getValue();
        if (inputB != null) valueB = inputB.getValue();
        //use unsigned comparison
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

    /**
     * Get first main input value
     *
     * @return - observable value for the first input
     */
    public IObservableValue<Integer> getInputA() {
        return inputA;
    }

    /**
     * Set first main input value
     *
     * @param inputA - new observable value to assign
     */
    public void setInputA(IObservableValue<Integer> inputA) {
        //check that input observable is returning value that is assignable to int
        if (Integer.class.isAssignableFrom(inputA.getValueType()))
            this.inputA = inputA;
    }

    /**
     * Get second main input value
     *
     * @return - observable value for the second input
     */
    public IObservableValue<Integer> getInputB() {
        return inputB;
    }

    /**
     * Set second main input value
     *
     * @param inputB - new observable value to assign
     */
    public void setInputB(IObservableValue inputB) {
        //check that input observable is returning value that is assignable to int
        if (Integer.class.isAssignableFrom(inputB.getValueType()))
            this.inputB = inputB;
    }

    /**
     * Get output value that indicates that input A is less than input B
     *
     * @return - observable value
     */
    public IObservableValue<Integer> getOutputLess() {
        return outputLess;
    }

    /**
     * Get output value that indicates that input A is more than input B
     *
     * @return - observable value
     */
    public IObservableValue<Integer> getOutputMore() {
        return outputMore;
    }

    /**
     * Get output value that indicates that input A equals input B
     *
     * @return - observable value
     */
    public IObservableValue<Integer> getOutputEquals() {
        return outputEquals;
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
