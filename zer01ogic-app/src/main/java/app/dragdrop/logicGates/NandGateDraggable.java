package app.dragdrop.logicGates;

import app.graphics.logicGates.NandGateGraphic;
import interfaces.circuits.ICircuitElementRegister;
import javafx.scene.layout.VBox;
import simulation.gates.AndGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;


public class NandGateDraggable extends BaseLogicGateDraggable {

    public NandGateDraggable() {
        super(new VBox(new NandGateGraphic()));
        createPins(getPathStrokeWidth(NandGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        AndGate gate = new AndGate((byte) 1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}