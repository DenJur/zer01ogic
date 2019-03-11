package app.dragdrop.logicGates;

import app.graphics.logicGates.NandGateGraphic;
import simulation.gates.AndGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;
import interfaces.circuits.ICircuitElementRegister;


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