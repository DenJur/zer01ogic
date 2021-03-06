package app.dragdrop.logicGates;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.Pin;
import app.graphics.logicGates.NotGateGraphic;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.scene.layout.VBox;
import simulation.gates.BaseLogicGate;
import simulation.gates.NotGate;
import simulation.values.MultibitValue;

import java.util.Collections;

import static app.graphics.GraphicsHelper.AnchorAll;
import static app.graphics.GraphicsHelper.getPathStrokeWidth;


public class NotGateDraggable extends BaseLogicGateDraggable {

    public NotGateDraggable() {
        super(new VBox(new NotGateGraphic()));
        createPins(getPathStrokeWidth(NotGateGraphic.styles));
    }

    /**
     * As NOT gates have 1 input/1 output on their pins, override the createPins method to reflect this
     */
    @Override
    protected void createPins(double lineWidth) {
        //create 2 input and 1 output pins
        InputPin inputPin1 = new InputPin(0, 15.5 + lineWidth / 2 + .5);
        AnchorAll(inputPin1, 0, 0, 0, 0);

        OutputPin outputPin = new OutputPin(91.0 + lineWidth, 15.5 + lineWidth / 2 + .5);
        AnchorAll(outputPin, 0, 0, 0, 0);

        //Add the pins to the DraggableNode's Pins ArrayList
        Collections.addAll(super.pins, inputPin1, outputPin);
        this.getChildren().addAll(inputPin1, outputPin);
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        NotGate gate = new NotGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((NotGate) register.getWorkingElementFor(this)).getOutput();
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        NotGate gate = (NotGate)register.getWorkingElementFor(this);

        for(Pin pin:pins){
            if(pin instanceof InputPin){
                InputPin inputPin=(InputPin)pin;
                if(inputPin.getConnectedWire()!=null) {
                    OutputPin outputPin = inputPin.getConnectedWire().getOutputPin();
                    IObservableValue observableValue = outputPin.getDraggableNode().getObservableValueForPin(outputPin, register);
                    gate.setInput(observableValue);
                }
                else{
                    gate.setInput(new MultibitValue(0));
                }
            }
            else {
                OutputPin outputPin=(OutputPin) pin;
                IObservableValue observableValue=getObservableValueForPin(outputPin, register);
                for(WireLogic wireLogic: outputPin.getWiresLogic()){
                    observableValue.registerObserver(wireLogic);
                    wireLogic.update(observableValue);
                }
            }
        }
    }
}
