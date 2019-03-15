package app.dragdrop.logicGates;

import app.graphics.logicGates.NotGateGraphic;
import javafx.scene.layout.VBox;
import simulation.gates.NotGate;
import interfaces.circuits.ICircuitElementRegister;


public class NotGateDraggable extends BaseLogicGateDraggable {

    public NotGateDraggable() {
        super(new VBox(new NotGateGraphic()));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        NotGate gate = new NotGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }
}
