package simulation.otherElements;

import interfaces.circuits.ICircuitQueue;
import interfaces.elements.IObservableValue;
import interfaces.elements.IScheduledLogicElement;
import interfaces.elements.IValueTransformer;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Clock implements IScheduledLogicElement {
    @Override
    public TimeUnit getDelayTimeUnits() {
        return null;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public boolean isFixedDelay() {
        return false;
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        return null;
    }

    @Override
    public IObservableValue getOutputByIndex(int index) {
        return null;
    }

    @Override
    public void addInput(IObservableValue input) {

    }

    @Override
    public void calculateOutputs() {

    }

    @Override
    public void setParentCircuit(ICircuitQueue circuit) {

    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void update() {

    }

    @Override
    public void run() {

    }
}
