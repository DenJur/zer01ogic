package app.dragdrop.memory;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.graphics.memory.JKFlipFlopGraphic;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import simulation.memory.JKFlipFlop;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;
import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class JKFlipFlopDraggable extends DraggableNode {

    private final JKFlipFlopGraphic graphic;
    private InputPin inputPinJ;
    private InputPin inputPinK;
    private OutputPin outputPin;
    private InputPin inputPinClock;

    public JKFlipFlopDraggable() {
        this.graphic = new JKFlipFlopGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(getPathStrokeWidth(JKFlipFlopGraphic.styles));
    }

    @Override
    protected void createPins(double lineWidth) {
        inputPinJ = new InputPin(0, 18 + lineWidth / 2 + .5);
        AnchorAll(inputPinJ, 0, 0, 0, 0);

        inputPinK = new InputPin(0, 39 + lineWidth / 2 + .5);
        AnchorAll(inputPinK, 0, 0, 0, 0);

        inputPinClock = new InputPin(0, 60 + lineWidth / 2 + .5);
        AnchorAll(inputPinClock, 0, 0, 0, 0);

        outputPin = new OutputPin(94.0 + lineWidth, 19 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPinJ, inputPinK, inputPinClock, outputPin);
        this.getChildren().addAll(inputPinJ, inputPinK, inputPinClock, outputPin);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((JKFlipFlop) register.getWorkingElementFor(this)).getOutput();
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        JKFlipFlop flipFlop = new JKFlipFlop();
        register.addCircuitWorkingElement(this, flipFlop);
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        JKFlipFlop flipFlop = (JKFlipFlop) register.getWorkingElementFor(this);
        OutputPin outputPin = inputPinJ.getConnectedWire().getOutputPin();
        flipFlop.setInputJ(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));
        outputPin = inputPinK.getConnectedWire().getOutputPin();
        flipFlop.setInputK(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));
        outputPin = inputPinClock.getConnectedWire().getOutputPin();
        flipFlop.setInputClock(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));

        IObservableValue<Integer> observableValue = flipFlop.getOutput();
        for (WireLogic wireLogic : this.outputPin.getWiresLogic()) {
            observableValue.registerObserver(wireLogic);
            wireLogic.update(observableValue);
        }
    }

    @Override
    public void reset() {

    }
}
