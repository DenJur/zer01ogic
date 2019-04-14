package simulation.memory;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

public class SRFlipFlop implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> inputS;
    protected IObservableValue<Integer> inputR;
    protected IObservableValue<Integer> inputClock;
    private ICircuitQueue parent;
    private Integer previousClock;


    public SRFlipFlop() {
        output = new MultibitValue(0, (byte) 1);
        previousClock = 0;
    }

    @Override
    public void addInput(IObservableValue input) {
        //TODO throw
    }

    @Override
    public void calculateOutputs() {
        Integer clockValue = 0;
        if (inputClock != null) clockValue = inputClock.getValue();
        if (!previousClock.equals(clockValue)) {
            previousClock = clockValue;
            if (clockValue != 0) {
                Integer SValue = 0;
                Integer RValue = 0;
                if (inputS != null) SValue = inputS.getValue();
                if (inputR != null) RValue = inputR.getValue();
                if (SValue != 0 && RValue == 0) {
                    output.setValue(1);
                } else if (RValue != 0 && SValue == 0) {
                    output.setValue(0);
                }
            }
        }
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

    public IObservableValue<Integer> getInputS() {
        return inputS;
    }

    public void setInputS(IObservableValue<Integer> inputS) {
        this.inputS = inputS;
    }

    public IObservableValue<Integer> getInputR() {
        return inputR;
    }

    public void setInputR(IObservableValue<Integer> inputR) {
        this.inputR = inputR;
    }

    public IObservableValue<Integer> getInputClock() {
        return inputClock;
    }

    public void setInputClock(IObservableValue<Integer> inputClock) {
        this.inputClock = inputClock;
        previousClock = inputClock.getValue();
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (output == value) {
                output = transformer;
            } else if (inputClock == value) {
                inputClock = transformer;
            } else if (inputS == value) {
                inputS = transformer;
            } else if (inputR == value) {
                inputR = transformer;
            }
        }
    }
}