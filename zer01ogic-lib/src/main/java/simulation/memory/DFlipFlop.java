package simulation.memory;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

public class DFlipFlop implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> inputData;
    protected IObservableValue<Integer> inputClock;
    private ICircuitQueue parent;

    public DFlipFlop(byte outputSize) {
        output = new MultibitValue(0, outputSize);
    }

    @Override
    public void calculateOutputs() {
        Integer clockValue = 0;
        if (inputClock != null) clockValue = inputClock.getValue();
        if (clockValue != 0) {
            Integer newData = 0;
            if (inputData != null) newData = inputData.getValue();
            output.setValue(newData);
        }
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

    public IObservableValue<Integer> getInputData() {
        return inputData;
    }

    public void setInputData(IObservableValue<Integer> inputData) {
        this.inputData = inputData;
    }

    public IObservableValue<Integer> getInputClock() {
        return inputClock;
    }

    public void setInputClock(IObservableValue<Integer> inputClock) {
        this.inputClock = inputClock;
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (output == value) {
                output = transformer;
            } else if (inputClock == value) {
                inputClock = transformer;
            } else if (inputData == value) {
                inputData = transformer;
            }
        }
    }
}
