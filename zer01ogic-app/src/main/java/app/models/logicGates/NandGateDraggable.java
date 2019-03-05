package app.models.logicGates;

import circuits.gates.AndGate;
import circuits.values.NotTransform;
import circuits.values.TransformerMode;
import interfaces.ICircuitElementRegister;


public class NandGateDraggable extends BaseLogicGateDraggable {

    public NandGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/NAND.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        AndGate gate = new AndGate((byte)1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}
