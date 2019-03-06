package app.dragdrop.logicGates;

import app.graphics.logicGates.AndGateGraphic;
import circuits.gates.AndGate;
import interfaces.ICircuitElementRegister;


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
