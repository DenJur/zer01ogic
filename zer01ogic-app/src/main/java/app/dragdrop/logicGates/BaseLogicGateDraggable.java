package app.dragdrop.logicGates;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.Pin;
import app.dragdrop.DraggableNode;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import javafx.scene.Node;
import simulation.gates.BaseLogicGate;
import simulation.values.MultibitValue;

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

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        BaseLogicGate gate = (BaseLogicGate)register.getWorkingElementFor(this);

        for(Pin pin:pins){
            if(pin instanceof InputPin){
                InputPin inputPin=(InputPin)pin;
                if(inputPin.getConnectedWire()!=null) {
                    OutputPin outputPin = inputPin.getConnectedWire().getOutputPin();
                    IObservableValue observableValue = outputPin.getDraggableNode().getObservableValueForPin(outputPin, register);
                    gate.addInput(observableValue);
                }
                else{
                    gate.addInput(new MultibitValue(0));
                }
            }
            else {
                OutputPin outputPin=(OutputPin) pin;
                IObservableValue observableValue=getObservableValueForPin(outputPin, register);
                for(WireLogic wireLogic: outputPin.getWiresLogic()){
                    observableValue.registerObserver(wireLogic);
                    wireLogic.update(observableValue);
                }
            }
        }
    }

}



