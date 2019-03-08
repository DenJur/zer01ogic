package app.dragdrop.logicGates;


import app.graphics.logicGates.NorGateGraphic;
import interfaces.circuits.ICircuitElementRegister;
import javafx.scene.layout.VBox;
import simulation.gates.OrGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class NorGateDraggable extends BaseLogicGateDraggable {

    public NorGateDraggable() {
        super(new VBox(new NorGateGraphic()));
        createPins(getPathStrokeWidth(NorGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        OrGate gate = new OrGate((byte) 1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}
