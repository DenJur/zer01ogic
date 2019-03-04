package app.models.logicGates;

import circuits.gates.NotGate;
import interfaces.ICircuitElementRegister;


public class NotGateDraggable extends BaseLogicGateDraggable {

    public NotGateDraggable() {this.getChildren().add(SVGLoader("Basic_Gates/NOT.svg")); }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        NotGate gate = new NotGate((byte)1);
        register.addCircuitWorkingElement(this, gate);
    }
}
