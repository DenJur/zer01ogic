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

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;

/*TODO DO THE GUI STUFF IN THESE CLASSES*/

public abstract class BaseLogicGateDraggable extends DraggableNode {

    private InputPin inputPin1;
    private InputPin inputPin2;
    private OutputPin outputPin;

    public BaseLogicGateDraggable(Node graphic ){
        this.getChildren().add(graphic);
        AnchorAll(graphic,0,0,0,0);
    }

    @Override
    protected void createPins(double lineWidth) {
        //create 2 input and 1 output pins
        inputPin1 = new InputPin(0, 5 + lineWidth / 2 + .5);
        AnchorAll(inputPin1, 0, 0, 0, 0);

        inputPin2 = new InputPin(0, 45 + lineWidth / 2 + .5);
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
        //TODO needs null checks to either ignore or provide user with appropriate error
        OutputPin outputPin=inputPin1.getConnectedWire().getOutputPin();
        IObservableValue observableValue = outputPin.getDraggableNode().getObservableValueForPin(outputPin, register);
        gate.addInput(observableValue);

        outputPin=inputPin2.getConnectedWire().getOutputPin();
        observableValue = outputPin.getDraggableNode().getObservableValueForPin(outputPin, register);
        gate.addInput(observableValue);

        for (WireLogic wireLogic: this.outputPin.getWiresLogic()) {
            gate.getOutputByIndex(0).registerObserver(wireLogic);
        }
    }

    @Override
    public void reset() {
    }

    @Override
    public void relocateToPoint(Point2D p) {
        super.relocateToPoint(p);
    }

}



