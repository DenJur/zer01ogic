package app.dragdrop.arithmetic;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.Pin;
import app.dragdrop.DraggableNode;
import app.graphics.arithmetic.SubtractorGraphic;
import app.graphics.memory.SRFlipFlopGraphic;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import simulation.arithmetic.Subtractor;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;
import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class SubtractorDraggable extends DraggableNode {

    private final SubtractorGraphic graphic;
    private InputPin inputPinA;
    private InputPin inputPinB;
    private InputPin inputPinBIn;
    private OutputPin outputPin;
    private OutputPin outputPinBOut;

    public SubtractorDraggable() {
        this.graphic = new SubtractorGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(getPathStrokeWidth(SRFlipFlopGraphic.styles));
    }

    @Override
    protected void createPins(double lineWidth) {
        //create 3 input and 2 output pins
        inputPinA = new InputPin(0, 18 + lineWidth / 2 + .5);
        AnchorAll(inputPinA, 0, 0, 0, 0);

        inputPinB = new InputPin(0, 39 + lineWidth / 2 + .5);
        AnchorAll(inputPinB, 0, 0, 0, 0);

        inputPinBIn = new InputPin(0, 60 + lineWidth / 2 + .5);
        AnchorAll(inputPinBIn, 0, 0, 0, 0);

        outputPin = new OutputPin(164.0 + lineWidth, 29 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        outputPinBOut = new OutputPin(164.0 + lineWidth, 50 + lineWidth / 2 + .5);
        AnchorAll(outputPinBOut, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPinA, inputPinB, inputPinBIn, outputPin, outputPinBOut);
        this.getChildren().addAll(inputPinA, inputPinB, inputPinBIn, outputPin, outputPinBOut);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        if (outputPin == this.outputPin)
            return ((Subtractor) register.getWorkingElementFor(this)).getOutput();
        else
            return ((Subtractor) register.getWorkingElementFor(this)).getBonusOutput();
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        Subtractor adder = new Subtractor((byte) 1);
        register.addCircuitWorkingElement(this, adder);
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        Subtractor adder = (Subtractor) register.getWorkingElementFor(this);
        OutputPin outputPin = inputPinA.getConnectedWire().getOutputPin();
        adder.setInputA(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));
        outputPin = inputPinB.getConnectedWire().getOutputPin();
        adder.setInputB(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));
        outputPin = inputPinBIn.getConnectedWire().getOutputPin();
        adder.setBonusInput(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));

        for (Pin pin : pins) {
            if (pin instanceof OutputPin) {
                outputPin = (OutputPin) pin;
                IObservableValue observableValue = getObservableValueForPin(outputPin, register);
                for (WireLogic wireLogic : outputPin.getWiresLogic()) {
                    observableValue.registerObserver(wireLogic);
                    wireLogic.update(observableValue);
                }
            }
        }
    }

    @Override
    public void reset() {

    }
}