package app.dragdrop.logicGates;

import app.graphics.logicGates.OrGateGraphic;
import simulation.gates.OrGate;
import interfaces.circuits.ICircuitElementRegister;

public class OrGateDraggable extends BaseLogicGateDraggable {

    public OrGateDraggable() {
        this.getChildren().add(new OrGateGraphic());
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        OrGate gate = new OrGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }

}