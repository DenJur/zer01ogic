package app.dragdrop.logicGates;


import app.graphics.logicGates.NorGateGraphic;
import circuits.gates.OrGate;
import circuits.values.NotTransform;
import circuits.values.TransformerMode;
import interfaces.ICircuitElementRegister;

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
