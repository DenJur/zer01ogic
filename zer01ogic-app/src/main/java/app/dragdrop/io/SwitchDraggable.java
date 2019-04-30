package app.dragdrop.io;

import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.enums.DrawStyle;
import app.graphics.io.SwitchGraphic;
import app.interfaces.InputNode;
import app.interfaces.StatefulNode;
import app.logicComponents.SwitchLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import static app.graphics.GraphicsHelper.AnchorAll;

public class SwitchDraggable extends DraggableNode implements ILogicElementFrontEnd, StatefulNode, InputNode {

    private final SwitchGraphic graphic;
    private OutputPin outputPin;

    public SwitchDraggable() {
        this.graphic = new SwitchGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(0);

        ///Set up the clock's CSS in build mode
        setNodeStyle(DrawStyle.Build);
    }

    @Override
    protected void createPins(double lineWidth) {
        outputPin = new OutputPin(60, 30);
        AnchorAll(outputPin, 0, 0, 0, 0);

        super.pins.add(outputPin);
        this.getChildren().add(outputPin);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((SwitchLogic) register.getWorkingElementFor(this)).getOutput();
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        SwitchLogic switchLogic = new SwitchLogic(this.graphic);
        register.addCircuitWorkingElement(this, switchLogic);

        this.setOnMouseClicked(event -> {
            switchLogic.switchState();
        });
    }

    @Override
    public void reset() {
    }

    @Override
    public void updateStyle() {
        graphic.updateStyle();
    }

    @Override
    public void setNodeStyle(DrawStyle newStyle) {
        graphic.setStyle(newStyle);
    }
}
