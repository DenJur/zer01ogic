package app.dragdrop.logicGates;

import app.graphics.logicGates.NandGateGraphic;
import circuits.gates.AndGate;
import circuits.values.NotTransform;
import circuits.values.TransformerMode;
import interfaces.ICircuitElementRegister;


public class NandGateDraggable extends BaseLogicGateDraggable {

    public NandGateDraggable() {
        this.getChildren().add(new NandGateGraphic());
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        AndGate gate = new AndGate((byte) 1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}
