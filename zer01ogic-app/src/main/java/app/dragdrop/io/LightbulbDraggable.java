package app.dragdrop.io;

import app.dragdrop.DraggableNode;
import app.graphics.io.LightbulbGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElementFrontEnd;

public class LightbulbDraggable   extends DraggableNode implements ILogicElementFrontEnd {

    public LightbulbDraggable() {
        this.getChildren().add(new LightbulbGraphic());
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
