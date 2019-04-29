package simulation.memory;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

public class JKFlipFlop implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> inputJ;
    protected IObservableValue<Integer> inputK;
    protected IObservableValue<Integer> inputClock;
    private ICircuitQueue parent;
    private Integer previousClock;


    public JKFlipFlop() {
        output = new MultibitValue(0, (byte) 1);
        previousClock = 0;
    }

    @Override
    public void calculateOutputs() {
        Integer clockValue = 0;
        if (inputClock != null) clockValue = inputClock.getValue();
        if (!previousClock.equals(clockValue)) {
            previousClock=clockValue;
            if (clockValue != 0) {
                Integer JValue = 0;
                Integer KValue = 0;
                if (inputJ != null) JValue = inputJ.getValue();
                if (inputK != null) KValue = inputK.getValue();
                if (JValue != 0 && KValue != 0) {
                    output.setValue(~output.getValue());
                } else if (JValue != 0) {
                    output.setValue(1);
                } else if (KValue != 0) {
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

    public IObservableValue<Integer> getInputJ() {
        return inputJ;
    }

    public void setInputJ(IObservableValue<Integer> inputJ) {
        this.inputJ = inputJ;
    }

    public IObservableValue<Integer> getInputK() {
        return inputK;
    }

    public void setInputK(IObservableValue<Integer> inputK) {
        this.inputK = inputK;
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
            } else if (inputJ == value) {
                inputJ = transformer;
            } else if (inputK == value) {
                inputK = transformer;
            }
        }
    }
}
