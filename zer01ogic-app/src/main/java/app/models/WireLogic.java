package app.models;

import app.components.InputPin;
import app.components.OutputPin;
import interfaces.elements.IObservableValue;
import interfaces.elements.IObserver;

public class WireLogic implements IObservableValue {

    private InputPin inputPin;
    private OutputPin outputPin;

    public WireLogic(InputPin inputPin, OutputPin outputPin){
        this.inputPin = inputPin;
        this.outputPin = outputPin;
    }

    public InputPin getInputPin() {
        return inputPin;
    }

    public OutputPin getOutputPin() {
        return outputPin;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void setValue(Object newValue) {

    }

    @Override
    public void registerObserver(IObserver IObserver) {

    }

    @Override
    public void deregisterObserver(IObserver IObserver) {

    }

    @Override
    public void reset() {

    }

    @Override
    public Class getValueType() {
        return null;
    }
}
