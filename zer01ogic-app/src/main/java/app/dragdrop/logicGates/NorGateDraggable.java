package app.dragdrop.logicGates;


import app.graphics.logicGates.NorGateGraphic;
import simulation.gates.OrGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;
import interfaces.circuits.ICircuitElementRegister;

public class NorGateDraggable extends BaseLogicGateDraggable {

    public NorGateDraggable() {
        this.getChildren().add(new NorGateGraphic());
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        OrGate gate = new OrGate((byte)1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}
