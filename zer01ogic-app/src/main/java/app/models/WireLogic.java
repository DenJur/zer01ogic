package app.models;

import app.components.InputPin;
import app.components.OutputPin;
import interfaces.elements.IObservableValue;
import interfaces.elements.IObserver;

public class WireLogic implements IObserver {

    private InputPin inputPin;
    private OutputPin outputPin;

    public void createConnection(InputPin inputPin, OutputPin outputPin){
        this.inputPin = inputPin;
        this.outputPin = outputPin;
    }


    @Override
    public void update() {

    }

    public InputPin getInputPin() {
        return inputPin;
    }

    public OutputPin getOutputPin() {
        return outputPin;
    }
}
