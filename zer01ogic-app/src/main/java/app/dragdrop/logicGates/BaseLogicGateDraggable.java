package app.dragdrop.logicGates;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import javafx.scene.Node;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;

public abstract class BaseLogicGateDraggable extends DraggableNode {
    private OutputPin outputPin;

    public BaseLogicGateDraggable(Node graphic ){
        this.getChildren().add(graphic);
        AnchorAll(graphic,0,0,0,0);
    }

    @Override
    protected void createPins(double lineWidth) {
        //create 2 input and 1 output pins
        InputPin inputPin1 = new InputPin(0, 5 + lineWidth / 2 + .5);
        AnchorAll(inputPin1, 0, 0, 0, 0);

        InputPin inputPin2 = new InputPin(0, 45 + lineWidth / 2 + .5);
        AnchorAll(inputPin2, 0, 0, 0, 0);

        outputPin = new OutputPin(91.0 + lineWidth, 25 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPin1, inputPin2, outputPin);
        this.getChildren().addAll(inputPin1, inputPin2, outputPin);
    }

    @Override
    public void reset() {
    }

}



