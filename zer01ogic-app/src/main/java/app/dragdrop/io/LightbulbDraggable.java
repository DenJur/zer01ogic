package app.dragdrop.io;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.Pin;
import app.dragdrop.DraggableNode;
import app.enums.DrawStyle;
import app.graphics.io.LightbulbGraphic;
import app.interfaces.StatefulNode;
import app.logicComponents.LightbulbLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import simulation.values.MultibitValue;

import static app.graphics.GraphicsHelper.AnchorAll;

public class LightbulbDraggable extends DraggableNode implements StatefulNode {

    private final LightbulbGraphic graphic;
    private InputPin inputPin;

    public LightbulbDraggable() {
        this.graphic = new LightbulbGraphic();
        VBox graphicBox = new VBox(this.graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(0);

        ///Set up the clock's CSS in build mode
        setNodeStyle(DrawStyle.Build);
    }

    @Override
    protected void createPins(double lineWidth) {
        inputPin = new InputPin(0, 30);
        AnchorAll(inputPin, 0, 0, 0, 0);

        super.pins.add(inputPin);
        this.getChildren().add(inputPin);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        //TODO no output pins, should throw
        return null;
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        LightbulbLogic lightbulbLogic = new LightbulbLogic(this.graphic);
        register.addCircuitWorkingElement(this, lightbulbLogic);
    }

    @Override
    public void reset() {
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        LightbulbLogic lightBulb = (LightbulbLogic) register.getWorkingElementFor(this);

        for (Pin pin : pins) {
            if (pin instanceof InputPin) {
                InputPin inputPin = (InputPin) pin;
                if (inputPin.getConnectedWire() != null) {
                    OutputPin outputPin = inputPin.getConnectedWire().getOutputPin();
                    IObservableValue observableValue = outputPin.getDraggableNode().getObservableValueForPin(outputPin, register);
                    lightBulb.addInput(observableValue);
                } else {
                    lightBulb.addInput(new MultibitValue(0));
                }
                return;
            }
        }
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
