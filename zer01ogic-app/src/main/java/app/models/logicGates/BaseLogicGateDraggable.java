package app.models.logicGates;

import app.dragdrop.DraggableNode;
import simulation.gates.AndGate;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElementFrontEnd;

/*TODO DO THE GUI STUFF IN THESE CLASSES*/

public abstract class BaseLogicGateDraggable extends DraggableNode  implements ILogicElementFrontEnd {
    public int input1;
    public int input2;
    public int output;

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        AndGate gate= (AndGate) register.getWorkingElementFor(this);
        //gate.addInput(((AndGate)input1.getInputGate).getOutputByIndex(0));
        //gate.addInput(((AndGate)input2.getInputGate).getOutputByIndex(0));
        gate.getOutput();
    }

    @Override
    public void reset() {
    }
}



