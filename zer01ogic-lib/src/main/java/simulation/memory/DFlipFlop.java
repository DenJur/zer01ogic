package simulation.memory;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

/**
 * D Flip-flop logic element
 */
public class DFlipFlop implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected IObservableValue<Integer> inputData;
    protected IObservableValue<Integer> inputClock;
    private ICircuitQueue parent;
    private Integer previousClock;

    public DFlipFlop(byte outputSize) {
        output = new MultibitValue(0, outputSize);
    }

    @Override
    public void calculateOutputs() {
        Integer clockValue = 0;
        //get clock value
        if (inputClock != null) clockValue = inputClock.getValue();
        if (!previousClock.equals(clockValue)) {
            previousClock = clockValue;
            //if clock set set output to data
            if (clockValue != 0) {
                Integer newData = 0;
                if (inputData != null) newData = inputData.getValue();
                output.setValue(newData);
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

    /**
     * Output observable getter
     *
     * @return - output observable value
     */
    public IObservableValue<Integer> getOutput() {
        return output;
    }

    /**
     * Getter for the data input
     *
     * @return - observable value for data input
     */
    public IObservableValue<Integer> getInputData() {
        return inputData;
    }

    /**
     * Setter for data input
     *
     * @param inputData - new observable value for data input
     */
    public void setInputData(IObservableValue<Integer> inputData) {
        if (this.inputData != null) this.inputData.deregisterObserver(this);
        this.inputData = inputData;
        inputData.registerObserver(this);
    }

    /**
     * Getter for the clock input
     *
     * @return - observable value for clock input
     */
    public IObservableValue<Integer> getInputClock() {
        return inputClock;
    }

    /**
     * Setter for clock input
     *
     * @param inputClock - new observable value for clock input
     */
    public void setInputClock(IObservableValue<Integer> inputClock) {
        if (this.inputClock != null) this.inputClock.deregisterObserver(this);
        this.inputClock = inputClock;
        previousClock = inputClock.getValue();
        inputClock.registerObserver(this);
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
