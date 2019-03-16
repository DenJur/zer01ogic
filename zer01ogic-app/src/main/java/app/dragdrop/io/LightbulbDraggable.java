package app.dragdrop.io;

import app.components.InputPin;
import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.graphics.io.LightbulbGraphic;
import app.logicComponents.LightbulbLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElement;
import interfaces.elements.IObservableValue;
import javafx.scene.layout.VBox;
import simulation.values.MultibitValue;

import static app.graphics.GraphicsHelper.AnchorAll;

public class LightbulbDraggable extends DraggableNode {

    private final LightbulbGraphic graphic;
    private InputPin inputPin;

    public LightbulbDraggable() {
        this.graphic = new LightbulbGraphic();
        VBox graphicBox = new VBox(this.graphic);
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox,0,0,0,0);
        createPins(0);
    }

    @Override
    protected void createPins(double lineWidth) {
        inputPin = new InputPin(0, 0);
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
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        LightbulbLogic lightbulbLogic = (LightbulbLogic)register.getWorkingElementFor(this);
        if(inputPin.getConnectedWire()!=null) {
            OutputPin outputPin = inputPin.getConnectedWire().getOutputPin();
            IObservableValue observableValue = outputPin.getDraggableNode().getObservableValueForPin(outputPin, register);
            lightbulbLogic.addInput(observableValue);
        }
        else
            lightbulbLogic.addInput(new MultibitValue(0));
    }

    @Override
    public void reset() {
    }
}
