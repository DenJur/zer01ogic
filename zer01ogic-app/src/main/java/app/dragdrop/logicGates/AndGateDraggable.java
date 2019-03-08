package app.dragdrop.logicGates;

import app.graphics.logicGates.AndGateGraphic;
import simulation.gates.AndGate;
import interfaces.circuits.ICircuitElementRegister;


public class AndGateDraggable extends BaseLogicGateDraggable {

    public AndGateDraggable() {
        this.getChildren().add(new AndGateGraphic());
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        AndGate gate = new AndGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }

}
