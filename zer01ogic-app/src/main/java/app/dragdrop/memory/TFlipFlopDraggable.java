package app.dragdrop.memory;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.graphics.memory.TFlipFlopGraphic;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import simulation.memory.DFlipFlop;
import simulation.memory.TFlipFlop;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;
import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class TFlipFlopDraggable extends DraggableNode {

    private final TFlipFlopGraphic graphic;
    private InputPin inputPinData;
    private InputPin inputPinClock;
    private OutputPin outputPin;

    public TFlipFlopDraggable() {
        this.graphic = new TFlipFlopGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(getPathStrokeWidth(TFlipFlopGraphic.styles));
    }

    @Override
    protected void createPins(double lineWidth) {
        //create 2 input and 1 output pins
        inputPinData = new InputPin(0, 18 + lineWidth / 2 + .5);
        AnchorAll(inputPinData, 0, 0, 0, 0);

        inputPinClock = new InputPin(0, 60 + lineWidth / 2 + .5);
        AnchorAll(inputPinClock, 0, 0, 0, 0);

        outputPin = new OutputPin(94.0 + lineWidth, 19 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPinData, inputPinClock, outputPin);
        this.getChildren().addAll(inputPinData, inputPinClock, outputPin);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((TFlipFlop) register.getWorkingElementFor(this)).getOutput();
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        TFlipFlop flipFlop = new TFlipFlop();
        register.addCircuitWorkingElement(this, flipFlop);
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        TFlipFlop flipFlop = (TFlipFlop) register.getWorkingElementFor(this);
        OutputPin outputPin = inputPinData.getConnectedWire().getOutputPin();
        flipFlop.setInputData(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));
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
