package app.models.logicGates;

import circuits.gates.XnorGate;
import interfaces.ICircuitElementRegister;


public class XnorGateDraggable extends BaseLogicGateDraggable {

    public XnorGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/XNOR.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        XnorGate gate = new XnorGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }
}
