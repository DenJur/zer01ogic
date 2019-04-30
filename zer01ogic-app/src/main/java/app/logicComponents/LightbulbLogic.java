package app.logicComponents;

import app.enums.DrawStyle;
import app.graphics.io.LightbulbGraphic;
import interfaces.circuits.ICircuitQueue;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import interfaces.elements.IValueTransformer;

import java.util.List;

public class LightbulbLogic implements ILogicElement {
    private final LightbulbGraphic parentGraphic;
    private IObservableValue<Integer> input;
    private ICircuitQueue queue;

    public LightbulbLogic(LightbulbGraphic parentGraphic) {
        this.parentGraphic = parentGraphic;
    }

    @Override
    public List<IObservableValue<Integer>> getOutputs() {
        //TODO throw exception?
        return null;
    }

    public void addInput(IObservableValue input) {
        if (Integer.class.isAssignableFrom(input.getValueType())) {
            this.input = input;
            input.registerObserver(this);
        }
    }

    @Override
    public void calculateOutputs() {
        if (input.getValue() != 0)
            parentGraphic.setStyle(DrawStyle.On);
        else
            parentGraphic.setStyle(DrawStyle.Off);
    }

    @Override
    public void setParentCircuit(ICircuitQueue circuit) {
        queue = circuit;
    }

    @Override
    public void addValueTransformer(IObservableValue value, IValueTransformer transformer) {
        if (Integer.class.isAssignableFrom(transformer.getValueType())) {
            transformer.setInnerValue(value);
            if (input == value) {
                input = transformer;
            }
        }
    }

    @Override
    public void reset() {
    }

    @Override
    public void update(IObservableValue source) {
        queue.queueElementForUpdate(this);
    }
}
