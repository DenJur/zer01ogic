package app.dragdrop.memory;

import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.graphics.memory.TFlipFlopGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import static app.graphics.GraphicsHelper.AnchorAll;

public class TFlipFlopDraggable extends DraggableNode {

    private final TFlipFlopGraphic graphic;

    public TFlipFlopDraggable() {
        this.graphic = new TFlipFlopGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(0);
    }

    @Override
    protected void createPins(double lineWidth) {

    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return null;
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {

    }

    @Override
    public void reset() {

    }
}
