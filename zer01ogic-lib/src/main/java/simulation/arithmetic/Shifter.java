package simulation.arithmetic;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

public class Shifter implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> inputA;
    protected IObservableValue<Integer> inputB;
    private ICircuitQueue parent;
    private ShiftMode mode;

    public Shifter(byte outputSize, ShiftMode mode) {
        output = new MultibitValue(0, outputSize);
        this.mode=mode;
    }

    @Override
    public void calculateOutputs() {
        Integer result=0;
        Integer shiftAmount=0;
        if(inputA!=null) result=inputA.getValue();
        if(inputB!=null) shiftAmount=inputB.getValue();
        int tmp;
        switch (mode){
            case LEFT:
                result=result<<shiftAmount;
                break;
            case RIGHT:
                result=result>>shiftAmount;
                break;
            case LEFT_WRAP:
                tmp=result>>(Integer.SIZE-shiftAmount);
                result=result<<shiftAmount;
                result|=tmp;
                break;
            case RIGHT_WRAP:
                tmp=result<<(Integer.SIZE-shiftAmount);
                result=result>>shiftAmount;
                result|=tmp;
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

    public enum ShiftMode{
        RIGHT,
        LEFT,
        RIGHT_WRAP,
        LEFT_WRAP,
    }
}
