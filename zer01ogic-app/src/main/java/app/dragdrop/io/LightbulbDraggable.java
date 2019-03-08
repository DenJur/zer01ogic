package app.dragdrop.io;

import app.dragdrop.DraggableNode;
import app.graphics.io.LightbulbGraphic;
import app.graphics.io.SwitchGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElementFrontEnd;
import javafx.scene.layout.VBox;

import static app.graphics.GraphicsHelper.AnchorAll;

public class LightbulbDraggable   extends DraggableNode implements ILogicElementFrontEnd {

    public LightbulbDraggable() {
        VBox graphic = new VBox(new LightbulbGraphic());
        this.getChildren().add(graphic);
        AnchorAll(graphic,0,0,0,0);
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
