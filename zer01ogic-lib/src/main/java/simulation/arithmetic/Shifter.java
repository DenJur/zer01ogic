package simulation.arithmetic;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

/**
 * Binary value shifter
 */
public class Shifter implements ILogicElement {
    protected IObservableValue<Integer> output;
    //indicates value to shift bits in
    protected IObservableValue<Integer> inputA;
    //indicates amount to shift by
    protected IObservableValue<Integer> inputB;
    private ICircuitQueue parent;
    private ShiftMode mode;

    public Shifter(byte outputSize, ShiftMode mode) {
        output = new MultibitValue(0, outputSize);
        this.mode = mode;
    }

    @Override
    public void calculateOutputs() {
        Integer result = 0;
        Integer shiftAmount = 0;
        if (inputA != null) result = inputA.getValue();
        if (inputB != null) shiftAmount = inputB.getValue();
        //temporary variable used to store shifted part in wrap shift mode
        int tmp;
        switch (mode) {
            case LEFT:
                result = result << shiftAmount;
                break;
            case RIGHT:
                result = result >> shiftAmount;
                break;
            case LEFT_WRAP:
                tmp = result >> (Integer.SIZE - shiftAmount);
                result = result << shiftAmount;
                result |= tmp;
                break;
            case RIGHT_WRAP:
                tmp = result << (Integer.SIZE - shiftAmount);
                result = result >> shiftAmount;
                result |= tmp;
                break;
        }
        output.setValue(result);
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
     * Returns main output value observable
     *
     * @return - output observable
     */
    public IObservableValue<Integer> getOutput() {
        return output;
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

    /**
     * Modes that shifter can operate in:
     * RIGHT - shifts bits to right
     * LEFT - shifts bits to left
     * RIGHT_WRAP - shifts bits to right and wrap around bits that trail out of the output size window
     * RIGHT_WRAP - shifts bits to left and wrap around bits that trail out of the output size window
     */
    public enum ShiftMode {
        RIGHT,
        LEFT,
        RIGHT_WRAP,
        LEFT_WRAP,
    }
}
