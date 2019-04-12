package simulation.arithmetic;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Arrays;
import java.util.List;

public abstract class BaseArithmeticGate implements ILogicElement {
    protected IObservableValue<Integer> bonusInput;
    protected IObservableValue<Integer> bonusOutput;
    protected IObservableValue<Integer> output;
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
    public void addInput(IObservableValue input) {
        //TODO throw
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        return Arrays.asList(output, bonusOutput);
    }

    @Override
    public IObservableValue getOutputByIndex(int index) {
        switch (index) {
            case 0:
                return output;
            case 1:
                return bonusOutput;
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
        output.reset();
        bonusOutput.reset();
    }

    public IObservableValue<Integer> getOutput() {
        return output;
    }

    public IObservableValue<Integer> getBonusInput() {
        return bonusInput;
    }

    public void setBonusInput(IObservableValue value) {
        if (Integer.class.isAssignableFrom(value.getValueType())) {
            bonusInput = value;
        }
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
