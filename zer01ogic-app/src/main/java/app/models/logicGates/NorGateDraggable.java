package app.models.logicGates;


import circuits.gates.NorGate;
import interfaces.ICircuitElementRegister;

public class NorGateDraggable extends BaseLogicGateDraggable {

    public NorGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/NOR.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        NorGate gate = new NorGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }
}
