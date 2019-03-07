package app.models.logicGates;

import simulation.gates.AndGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;
import interfaces.circuits.ICircuitElementRegister;


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
