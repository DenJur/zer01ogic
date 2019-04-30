package simulation.memory;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

/**
 * JK Flip-flop logic element
 */
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
        //get clock value
        if (inputClock != null) clockValue = inputClock.getValue();
        //only read values on clock change
        if (!previousClock.equals(clockValue)) {
            previousClock = clockValue;
            //on positive clock
            if (clockValue != 0) {
                //read inputs
                Integer JValue = 0;
                Integer KValue = 0;
                if (inputJ != null) JValue = inputJ.getValue();
                if (inputK != null) KValue = inputK.getValue();
                //if both inputs are on output should alternate
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

    /**
     * Output observable getter
     *
     * @return - output observable value
     */
    public IObservableValue<Integer> getOutput() {
        return output;
    }

    /**
     * Getter for the J input
     *
     * @return - observable value for J input
     */
    public IObservableValue<Integer> getInputJ() {
        return inputJ;
    }

    /**
     * Setter for J input
     *
     * @param inputJ - new observable value for J input
     */
    public void setInputJ(IObservableValue<Integer> inputJ) {
        if (this.inputJ != null) this.inputJ.deregisterObserver(this);
        this.inputJ = inputJ;
        inputJ.registerObserver(this);
    }

    /**
     * Getter for the K input
     *
     * @return - observable value for K input
     */
    public IObservableValue<Integer> getInputK() {
        return inputK;
    }

    /**
     * Setter for K input
     *
     * @param inputK - new observable value for K input
     */
    public void setInputK(IObservableValue<Integer> inputK) {
        if (this.inputK != null) this.inputK.deregisterObserver(this);
        this.inputK = inputK;
        inputK.registerObserver(this);
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
            } else if (inputJ == value) {
                inputJ = transformer;
            } else if (inputK == value) {
                inputK = transformer;
            }
        }
    }
}
