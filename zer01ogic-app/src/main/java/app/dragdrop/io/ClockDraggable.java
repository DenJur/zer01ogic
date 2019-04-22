package app.dragdrop.io;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.graphics.io.ClockGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import simulation.io.Clock;

import static app.graphics.GraphicsHelper.AnchorAll;

public class ClockDraggable extends DraggableNode {

    private final ClockGraphic graphic;

    public ClockDraggable() {
        this.graphic = new ClockGraphic();
        VBox graphicBox = new VBox(this.graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);
        createPins(0);
    }

    @Override
    protected void createPins(double lineWidth) {
        OutputPin outputPin = new OutputPin(60, 30);
        AnchorAll(outputPin, 0, 0, 0, 0);

        super.pins.add(outputPin);
        this.getChildren().add(outputPin);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((Clock) register.getWorkingElementFor(this)).getOutput();
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        Clock clockLogic = new Clock(1000);
        register.addCircuitWorkingElement(this, clockLogic);
    }

    @Override
    public void reset() {
    }
}