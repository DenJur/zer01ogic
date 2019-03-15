package app.dragdrop.logicGates;

import app.graphics.logicGates.XnorGateGraphic;
import interfaces.circuits.ICircuitElementRegister;
import javafx.scene.layout.VBox;
import simulation.gates.XorGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;


public class XnorGateDraggable extends BaseLogicGateDraggable {

    public XnorGateDraggable() {
        super(new VBox(new XnorGateGraphic()));
        createPins(getPathStrokeWidth(XnorGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        XorGate gate = new XorGate((byte) 1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}