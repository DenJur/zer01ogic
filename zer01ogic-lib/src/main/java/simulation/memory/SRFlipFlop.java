package simulation.memory;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

/**
 * SR Flip-flop logic element
 */
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
    public void calculateOutputs() {
        Integer clockValue = 0;
        //get clock value
        if (inputClock != null) clockValue = inputClock.getValue();
        //only read values on clock change
        if (!previousClock.equals(clockValue)) {
            previousClock = clockValue;
            //on positive clock
            if (clockValue != 0) {
                //read inputs
                Integer SValue = 0;
                Integer RValue = 0;
                if (inputS != null) SValue = inputS.getValue();
                if (inputR != null) RValue = inputR.getValue();
                //if only set input is high set output to high
                if (SValue != 0 && RValue == 0) {
                    output.setValue(1);
                }
                //else if only reset input is high set output to low
                else if (RValue != 0 && SValue == 0) {
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

    /**
     * Output observable getter
     *
     * @return - output observable value
     */
    public IObservableValue<Integer> getOutput() {
        return output;
    }

    /**
     * Getter for the SET input
     *
     * @return - observable value for SET input
     */
    public IObservableValue<Integer> getInputS() {
        return inputS;
    }

    /**
     * Setter for SET input
     *
     * @param inputS - new observable value for SET input
     */
    public void setInputS(IObservableValue<Integer> inputS) {
        if (this.inputS != null) this.inputS.deregisterObserver(this);
        this.inputS = inputS;
        inputS.registerObserver(this);
    }

    /**
     * Getter for the RESET input
     *
     * @return - observable value for RESET input
     */
    public IObservableValue<Integer> getInputR() {
        return inputR;
    }

    /**
     * Setter for RESET input
     *
     * @param inputR - new observable value for RESET input
     */
    public void setInputR(IObservableValue<Integer> inputR) {
        if (this.inputR != null) this.inputR.deregisterObserver(this);
        this.inputR = inputR;
        inputR.registerObserver(this);
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
            } else if (inputS == value) {
                inputS = transformer;
            } else if (inputR == value) {
                inputR = transformer;
            }
        }
    }
}