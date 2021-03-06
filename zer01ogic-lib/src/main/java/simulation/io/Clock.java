package simulation.io;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.IObservableValue;
import interfaces.elements.IScheduledLogicElement;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Clock simulation element
 */
public class Clock implements IScheduledLogicElement {
    private final int delay;
    protected IObservableValue<Integer> output;
    private ICircuitQueue parent;

    public Clock(int delay) {
        this.delay = delay;
        output = new MultibitValue(0, (byte) 1);
    }

    @Override
    public TimeUnit getDelayTimeUnits() {
        return TimeUnit.MILLISECONDS;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public boolean isFixedDelay() {
        return true;
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        return Collections.singletonList(output);
    }

    /**
     * Output observable getter
     *
     * @return - output observable value
     */
    public IObservableValue<Integer> getOutput() {
        return output;
    }

    @Override
    public void calculateOutputs() {
        output.setValue(~output.getValue());
    }

    @Override
    public void setParentCircuit(ICircuitQueue circuit) {
        this.parent = circuit;
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (output == value) {
                output = transformer;
            }
        }
    }

    @Override
    public void reset() {
        output.setValue(0);
    }

    @Override
    public void update(IObservableValue source) {
//        calculateOutputs();
        parent.queueElementForUpdate(this);
    }
}
