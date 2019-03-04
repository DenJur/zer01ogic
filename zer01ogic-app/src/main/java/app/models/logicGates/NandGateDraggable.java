package app.models.logicGates;

import circuits.gates.NandGate;
import interfaces.ICircuitElementRegister;


public class NandGateDraggable extends BaseLogicGateDraggable {

    public NandGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/NAND.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        NandGate gate = new NandGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }
}
