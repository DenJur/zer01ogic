package app.models.logicGates;

import circuits.gates.XorGate;
import circuits.values.NotTransform;
import circuits.values.TransformerMode;
import interfaces.ICircuitElementRegister;


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
