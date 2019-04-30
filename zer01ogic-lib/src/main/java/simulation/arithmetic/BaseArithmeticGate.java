package simulation.arithmetic;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract class used as a base for all arithmetic elements
 */
public abstract class BaseArithmeticGate implements ILogicElement {
    //bonus input used for carry bits, borrow bits etc
    protected IObservableValue<Integer> bonusInput;
    //bonus output used for carry bits, borrow bits etc
    protected IObservableValue<Integer> bonusOutput;
    //main output value
    protected IObservableValue<Integer> output;
    //two main inputs
    protected IObservableValue<Integer> inputA;
    protected IObservableValue<Integer> inputB;
    protected byte outputSize;
    private ICircuitQueue parent;

    public BaseArithmeticGate(byte outputSize) {
        output = new MultibitValue(0, outputSize);
        bonusOutput = new MultibitValue(0, (byte) 1);
        this.outputSize = outputSize;
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        return Arrays.asList(output, bonusOutput);
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
        bonusOutput.reset();
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
     * Returns bonus input that used for carry bits, borrow bits etc
     *
     * @return - observable value of bonus input
     */
    public IObservableValue<Integer> getBonusInput() {
        return bonusInput;
    }

    /**
     * Assigns bonus input that used for carry bits, borrow bits etc
     *
     * @param value - observable to assign to bonus input
     */
    public void setBonusInput(IObservableValue value) {
        //check that input observable is returning value that is assignable to int
        if (Integer.class.isAssignableFrom(value.getValueType())) {
            if (bonusInput != null) bonusInput.deregisterObserver(this);
            bonusInput = value;
            bonusInput.registerObserver(this);
        }
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
        if (Integer.class.isAssignableFrom(inputA.getValueType())) {
            if (this.inputA != null) this.inputA.deregisterObserver(this);
            this.inputA = inputA;
            inputA.registerObserver(this);
        }
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
        if (Integer.class.isAssignableFrom(inputB.getValueType())) {
            if (this.inputB != null) this.inputB.deregisterObserver(this);
            this.inputB = inputB;
            inputB.registerObserver(this);
        }
    }

    /**
     * Returns bonus output that used for carry bits, borrow bits etc
     *
     * @return - observable value of bonus output
     */
    public IObservableValue<Integer> getBonusOutput() {
        return bonusOutput;
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (bonusOutput == value) {
                bonusOutput = transformer;
            } else if (bonusInput == value) {
                bonusInput = transformer;
            } else if (output == value) {
                output = transformer;
            } else if (inputA == value) {
                inputA = transformer;
            } else if (inputB == value) {
                inputB = transformer;
            }
        }
    }
}
