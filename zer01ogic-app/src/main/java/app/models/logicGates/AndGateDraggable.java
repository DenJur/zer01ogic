package app.models.logicGates;

import simulation.gates.AndGate;
import interfaces.circuits.ICircuitElementRegister;


public class AndGateDraggable extends BaseLogicGateDraggable {

    public AndGateDraggable() {
        this.getChildren().add(SVGLoader("Basic_Gates/AND.svg"));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        AndGate gate = new AndGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }

}
