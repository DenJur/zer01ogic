package app.models;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.WireObject;
import interfaces.elements.IObserver;

public class WireLogic implements IObserver {

    private InputPin inputPin;
    private OutputPin outputPin;

    private WireObject wireObject;

    public WireLogic(){
        wireObject = new WireObject();
    }

    public void createConnection(InputPin inputPin, OutputPin outputPin){
        this.inputPin = inputPin;
        this.outputPin = outputPin;
    }

    @Override
    public void update() {

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
