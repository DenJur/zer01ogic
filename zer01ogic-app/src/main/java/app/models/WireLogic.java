package app.models;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.WireObject;
import interfaces.elements.IObservableValue;
import interfaces.elements.IObserver;

public class WireLogic implements IObserver {

    private InputPin inputPin;
    private OutputPin outputPin;

    private WireObject wireObject;

    public WireLogic(){
        wireObject = new WireObject();
        wireObject.setMouseTransparent(true); //TODO When adding the eraser tool, make the wire selectable so it can be deleted
    }

    public void createConnection(InputPin inputPin, OutputPin outputPin){
        this.inputPin = inputPin;
        this.outputPin = outputPin;
    }

    @Override
    public void update(IObservableValue source) {
        if(((IObservableValue<Integer>)source).getValue()!=0){
            wireObject.recolor(WireObject.WireStyle.On);
        }
        else {
            wireObject.recolor(WireObject.WireStyle.Off);
        }
    }

    public WireObject getWireObject() {
        return wireObject;
    }

    public InputPin getInputPin() {
        return inputPin;
    }

    public OutputPin getOutputPin() {
        return outputPin;
    }
}
