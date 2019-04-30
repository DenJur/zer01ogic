package app.dragdrop.arithmetic;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.graphics.arithmetic.SubtractorGraphic;
import app.graphics.memory.SRFlipFlopGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;
import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class SubtractorDraggable extends DraggableNode {

    private final SubtractorGraphic graphic;

    public SubtractorDraggable() {
        this.graphic = new SubtractorGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(getPathStrokeWidth(SRFlipFlopGraphic.styles));
    }

    @Override
    protected void createPins(double lineWidth) {
        //create 3 input and 2 output pins
        InputPin inputPin1 = new InputPin(0, 18 + lineWidth / 2 + .5);
        AnchorAll(inputPin1, 0, 0, 0, 0);

        InputPin inputPin2 = new InputPin(0, 39 + lineWidth / 2 + .5);
        AnchorAll(inputPin2, 0, 0, 0, 0);

        InputPin inputPin3 = new InputPin(0, 60 + lineWidth / 2 + .5);
        AnchorAll(inputPin3, 0, 0, 0, 0);

        OutputPin outputPin1 = new OutputPin(164.0 + lineWidth, 29 + lineWidth / 2 + .5);
        AnchorAll(outputPin1, 0, 0, 0, 0);

        OutputPin outputPin2 = new OutputPin(164.0 + lineWidth, 50 + lineWidth / 2 + .5);
        AnchorAll(outputPin2, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPin1, inputPin2, inputPin3, outputPin1, outputPin2);
        this.getChildren().addAll(inputPin1, inputPin2, inputPin3, outputPin1, outputPin2);
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