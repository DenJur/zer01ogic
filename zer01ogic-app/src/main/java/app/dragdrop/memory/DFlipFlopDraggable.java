package app.dragdrop.memory;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.Pin;
import app.dragdrop.DraggableNode;
import app.graphics.memory.DFlipFlopGraphic;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import simulation.memory.DFlipFlop;
import simulation.values.MultibitValue;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;
import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class DFlipFlopDraggable extends DraggableNode {

    private final DFlipFlopGraphic graphic;

    public DFlipFlopDraggable() {
        this.graphic = new DFlipFlopGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(getPathStrokeWidth(DFlipFlopGraphic.styles));
    }

    @Override
    protected void createPins(double lineWidth) {
        //create 2 input and 1 output pins
        InputPin inputPin1 = new InputPin(0, 18 + lineWidth / 2 + .5);
        AnchorAll(inputPin1, 0, 0, 0, 0);

        InputPin inputPin2 = new InputPin(0, 60 + lineWidth / 2 + .5);
        AnchorAll(inputPin2, 0, 0, 0, 0);

        OutputPin outputPin = new OutputPin(94.0 + lineWidth, 19 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPin1, inputPin2, outputPin);
        this.getChildren().addAll(inputPin1, inputPin2, outputPin);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        //return ((DFlipFlop) register.getWorkingElementFor(this)).getOutput();
        return null;
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        //DFlipFlop flipFlop = new DFlipFlop((byte) 1);
        //register.addCircuitWorkingElement(this, flipFlop);
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        /*
        DFlipFlop flipFlop = (DFlipFlop) register.getWorkingElementFor(this);

        for (Pin pin : pins) {
            if (pin instanceof InputPin) {
                InputPin inputPin = (InputPin) pin;
                if (inputPin.getConnectedWire() != null) {
                    OutputPin outputPin = inputPin.getConnectedWire().getOutputPin();
                    IObservableValue observableValue = outputPin.getDraggableNode().getObservableValueForPin(outputPin, register);
                    flipFlop.setInputData(observableValue);
                } else {
                    flipFlop.setInputData(new MultibitValue(0));
                }
            } else {
                OutputPin outputPin = (OutputPin) pin;
                IObservableValue observableValue = getObservableValueForPin(outputPin, register);
                for (WireLogic wireLogic : outputPin.getWiresLogic()) {
                    observableValue.registerObserver(wireLogic);
                    wireLogic.update(observableValue);
                }
            }
        }
        */
    }

    @Override
    public void reset() {

    }
}
