package app.dragdrop.logicGates;

import app.graphics.logicGates.OrGateGraphic;
import interfaces.circuits.ICircuitElementRegister;
import javafx.scene.layout.VBox;
import simulation.gates.OrGate;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class OrGateDraggable extends BaseLogicGateDraggable {

    public OrGateDraggable() {
        super(new VBox(new OrGateGraphic()));
        createPins(getPathStrokeWidth(OrGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        OrGate gate = new OrGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }

}