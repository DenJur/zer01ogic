package app.models.logicGates;


import circuits.gates.NandGate;
import circuits.gates.XorGate;
import interfaces.ICircuitElementRegister;

public class XorGateDraggable extends BaseLogicGateDraggable {

    public XorGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/XOR.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        XorGate gate = new XorGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }
}
