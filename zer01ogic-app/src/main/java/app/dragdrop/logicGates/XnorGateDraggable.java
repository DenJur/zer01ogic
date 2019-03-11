package app.dragdrop.logicGates;

import app.graphics.logicGates.XnorGateGraphic;
import simulation.gates.XorGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;
import interfaces.circuits.ICircuitElementRegister;


public class XnorGateDraggable extends BaseLogicGateDraggable {

    public XnorGateDraggable() {

        this.getChildren().add(new XnorGateGraphic());
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        XorGate gate = new XorGate((byte)1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}