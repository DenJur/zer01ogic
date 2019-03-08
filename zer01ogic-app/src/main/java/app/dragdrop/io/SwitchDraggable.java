package app.dragdrop.io;

import app.dragdrop.DraggableNode;
import app.graphics.io.SwitchGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElementFrontEnd;

public class SwitchDraggable  extends DraggableNode implements ILogicElementFrontEnd {

    public SwitchDraggable() {
        this.getChildren().add(new SwitchGraphic());
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {

    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
    }

    @Override
    public void reset() {
    }
}
