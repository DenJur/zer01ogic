package app.models.logicGates;

import circuits.gates.AndGate;
import circuits.gates.OrGate;
import interfaces.ICircuitElementRegister;

public class OrGateDraggable extends BaseLogicGateDraggable {

    public OrGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/OR.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        OrGate gate = new OrGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }

}
