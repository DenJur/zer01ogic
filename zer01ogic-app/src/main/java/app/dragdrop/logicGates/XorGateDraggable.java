package app.dragdrop.logicGates;


import app.graphics.logicGates.XorGateGraphic;
import interfaces.circuits.ICircuitElementRegister;
import javafx.scene.layout.VBox;
import simulation.gates.XorGate;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class XorGateDraggable extends BaseLogicGateDraggable {

    public XorGateDraggable() {
        super(new VBox(new XorGateGraphic()));
        createPins(getPathStrokeWidth(XorGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        XorGate gate = new XorGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }
}