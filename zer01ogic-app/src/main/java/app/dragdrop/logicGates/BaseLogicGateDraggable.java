package app.dragdrop.logicGates;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElement;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import simulation.values.MultibitValue;

import java.util.ArrayList;
import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;

/*TODO DO THE GUI STUFF IN THESE CLASSES*/

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
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        ILogicElement gate = register.getWorkingElementFor(this);
        pins.stream().filter(pin -> pin instanceof InputPin).map(pin -> (InputPin)pin).forEach(pin -> {
            if(pin.getConnectedWire()!=null) {
                outputPin = pin.getConnectedWire().getOutputPin();
                IObservableValue observableValue = outputPin.getDraggableNode().getObservableValueForPin(outputPin, register);
                gate.addInput(observableValue);
            }
            else{
                gate.addInput(new MultibitValue(0));
            }
        });
    }

    @Override
    public void reset() {
    }

    @Override
    public void relocateToPoint(Point2D p) {
        super.relocateToPoint(p);
    }

}



