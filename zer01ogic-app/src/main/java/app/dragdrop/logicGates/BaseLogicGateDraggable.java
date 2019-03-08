package app.dragdrop.logicGates;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElementFrontEnd;
import javafx.scene.Node;
import simulation.gates.AndGate;
import simulation.gates.BaseLogicGate;

import static app.graphics.GraphicsHelper.AnchorAll;

/*TODO DO THE GUI STUFF IN THESE CLASSES*/

public abstract class BaseLogicGateDraggable extends DraggableNode implements ILogicElementFrontEnd {
    public int input1;
    public int input2;
    public int output;

    public BaseLogicGateDraggable(){}

    public BaseLogicGateDraggable(Node graphic ){
        this.getChildren().add(graphic);
        AnchorAll(graphic,0,0,0,0);
    }


    protected void createPins(double lineWidth) {
        //create 2 input and 1 output pins
        InputPin inputPin1 = new InputPin(0, 5 + lineWidth / 2 + .5);
        AnchorAll(inputPin1, 0, 0, 0, 0);
        InputPin inputPin2 = new InputPin(0, 45 + lineWidth / 2 + .5);

        AnchorAll(inputPin2, 0, 0, 0, 0);
        OutputPin outputPin = new OutputPin(91.0 + lineWidth, 25 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        this.getChildren().addAll(inputPin1, inputPin2, outputPin);
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        AndGate gate = (AndGate) register.getWorkingElementFor(this);
        //gate.addInput(((AndGate)input1.getInputGate).getOutputByIndex(0));
        //gate.addInput(((AndGate)input2.getInputGate).getOutputByIndex(0));
        gate.getOutput();
    }

    @Override
    public void reset() {
    }
}



