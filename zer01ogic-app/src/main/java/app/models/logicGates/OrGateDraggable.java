package app.models.logicGates;

import simulation.gates.OrGate;
import interfaces.circuits.ICircuitElementRegister;

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
