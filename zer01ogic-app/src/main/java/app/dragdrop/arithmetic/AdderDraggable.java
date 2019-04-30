package app.dragdrop.arithmetic;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.Pin;
import app.dragdrop.DraggableNode;
import app.graphics.arithmetic.AdderGraphic;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import simulation.arithmetic.Adder;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;
import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class AdderDraggable extends DraggableNode {

    private final AdderGraphic graphic;
    private InputPin inputPinA;
    private InputPin inputPinB;
    private InputPin inputPinCIn;
    private OutputPin outputPin;
    private OutputPin outputPinCOut;

    public AdderDraggable() {
        this.graphic = new AdderGraphic();
        VBox graphicBox = new VBox(graphic);
        graphicBox.setMargin(graphic, new Insets(10));
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);

        createPins(getPathStrokeWidth(AdderGraphic.styles));
    }

    @Override
    protected void createPins(double lineWidth) {
        //create 3 input and 2 output pins
        inputPinA = new InputPin(0, 18 + lineWidth / 2 + .5);
        AnchorAll(inputPinA, 0, 0, 0, 0);

        inputPinB = new InputPin(0, 39 + lineWidth / 2 + .5);
        AnchorAll(inputPinB, 0, 0, 0, 0);

        inputPinCIn = new InputPin(0, 60 + lineWidth / 2 + .5);
        AnchorAll(inputPinCIn, 0, 0, 0, 0);

        outputPin = new OutputPin(164.0 + lineWidth, 29 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        outputPinCOut = new OutputPin(164.0 + lineWidth, 50 + lineWidth / 2 + .5);
        AnchorAll(outputPinCOut, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPinA, inputPinB, inputPinCIn, outputPin, outputPinCOut);
        this.getChildren().addAll(inputPinA, inputPinB, inputPinCIn, outputPin, outputPinCOut);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        if (outputPin == this.outputPin)
            return ((Adder) register.getWorkingElementFor(this)).getOutput();
        else
            return ((Adder) register.getWorkingElementFor(this)).getBonusOutput();
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        Adder adder = new Adder((byte) 1);
        register.addCircuitWorkingElement(this, adder);
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        Adder adder = (Adder) register.getWorkingElementFor(this);
        OutputPin outputPin = inputPinA.getConnectedWire().getOutputPin();
        adder.setInputA(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));
        outputPin = inputPinB.getConnectedWire().getOutputPin();
        adder.setInputB(outputPin.getDraggableNode().getObservableValueForPin(outputPin, register));
        outputPin = inputPinCIn.getConnectedWire().getOutputPin();
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