package app.dragdrop.logicGates;

import app.graphics.logicGates.NotGateGraphic;
import circuits.gates.NotGate;
import interfaces.ICircuitElementRegister;


public class NotGateDraggable extends BaseLogicGateDraggable {

    public NotGateDraggable() {
        this.getChildren().add(new NotGateGraphic());
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        NotGate gate = new NotGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }
}
