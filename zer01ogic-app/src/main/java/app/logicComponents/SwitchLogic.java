package app.logicComponents;

import app.graphics.io.SwitchGraphic;
import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;
import simulation.values.MultibitValue;

import java.util.Collections;
import java.util.List;

public class SwitchLogic implements ILogicElement {
    private final SwitchGraphic parentGraphic;
    private IObservableValue<Integer> output;

    public SwitchLogic(SwitchGraphic parentGraphic) {
        this.parentGraphic = parentGraphic;
        this.output = new MultibitValue(0);
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        return Collections.singletonList(output);
    }

    @Override
    public IObservableValue getOutputByIndex(int index) {
//        if(index!=0) throw
        return output;
    }

    @Override
    public void addInput(IObservableValue input) {
        //TODO throw?
    }

    @Override
    public void calculateOutputs() {
    }

    @Override
    public void setParentCircuit(ICircuitQueue circuit) {
        // never needs to queue
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
    }

    @Override
    public void update() {
        //Is never updated
    }

    public IObservableValue getOutput() {
        return output;
    }

    public void switchState() {
        output.setValue(~output.getValue());
        if (output.getValue() != 0)
            parentGraphic.setStyle(SwitchGraphic.SwitchStyle.On);
        else
            parentGraphic.setStyle(SwitchGraphic.SwitchStyle.Off);
    }
}
