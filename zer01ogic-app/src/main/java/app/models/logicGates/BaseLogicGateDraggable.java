package app.models.logicGates;

import app.dragdrop.DraggableNode;
import circuits.gates.AndGate;
import interfaces.ICircuitElementRegister;
import interfaces.ILogicElementFrontEnd;

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
}



