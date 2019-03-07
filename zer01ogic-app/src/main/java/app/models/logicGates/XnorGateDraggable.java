package app.models.logicGates;

import simulation.gates.XorGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;
import interfaces.circuits.ICircuitElementRegister;


public class XnorGateDraggable extends BaseLogicGateDraggable {

    public XnorGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/XNOR.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        XorGate gate = new XorGate((byte)1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }
}
