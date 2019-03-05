package app.models.logicGates;


import circuits.gates.OrGate;
import circuits.values.NotTransform;
import circuits.values.TransformerMode;
import interfaces.ICircuitElementRegister;

public class NorGateDraggable extends BaseLogicGateDraggable {

    public NorGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/NOR.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        OrGate gate = new OrGate((byte)1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}
