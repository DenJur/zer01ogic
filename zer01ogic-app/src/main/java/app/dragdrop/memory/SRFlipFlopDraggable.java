package app.dragdrop.memory;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.graphics.memory.SRFlipFlopGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;
import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class SRFlipFlopDraggable extends DraggableNode {

    private final SRFlipFlopGraphic graphic;

    public SRFlipFlopDraggable() {
        this.graphic = new SRFlipFlopGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(getPathStrokeWidth(SRFlipFlopGraphic.styles));
    }

    @Override
    protected void createPins(double lineWidth) {
        //create 2 input and 1 output pins
        InputPin inputPin1 = new InputPin(0, 18 + lineWidth / 2 + .5);
        AnchorAll(inputPin1, 0, 0, 0, 0);

        InputPin inputPin2 = new InputPin(0, 39 + lineWidth / 2 + .5);
        AnchorAll(inputPin2, 0, 0, 0, 0);

        InputPin inputPin3 = new InputPin(0, 60 + lineWidth / 2 + .5);
        AnchorAll(inputPin3, 0, 0, 0, 0);

        OutputPin outputPin = new OutputPin(94.0 + lineWidth, 19 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPin1, inputPin2, inputPin3, outputPin);
        this.getChildren().addAll(inputPin1, inputPin2, inputPin3, outputPin);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return null;
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {

    }

    @Override
    public void reset() {

    }
}