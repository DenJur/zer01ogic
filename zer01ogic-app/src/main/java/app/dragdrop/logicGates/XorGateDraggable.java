package app.dragdrop.logicGates;


import app.graphics.logicGates.XorGateGraphic;
import simulation.gates.XorGate;
import interfaces.circuits.ICircuitElementRegister;

public class XorGateDraggable extends BaseLogicGateDraggable {

    public XorGateDraggable() {
        this.getChildren().add(new XorGateGraphic());
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        XorGate gate = new XorGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }
}