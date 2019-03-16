package app.dragdrop.io;

import app.components.OutputPin;
import app.dragdrop.DraggableNode;
import app.graphics.io.SwitchGraphic;
import app.logicComponents.SwitchLogic;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IObservableValue;
import javafx.scene.layout.VBox;

import static app.graphics.GraphicsHelper.AnchorAll;

public class SwitchDraggable extends DraggableNode implements ILogicElementFrontEnd {

    private final SwitchGraphic graphic;
    private OutputPin outputPin;

    public SwitchDraggable() {
        this.graphic = new SwitchGraphic();
        VBox graphicBox = new VBox(graphic);
        this.getChildren().add(graphicBox);
        AnchorAll(graphicBox, 0, 0, 0, 0);
        createPins(0);

    }

    @Override
    protected void createPins(double lineWidth) {
        outputPin = new OutputPin(0, 0);
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
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        SwitchLogic switchLogic = (SwitchLogic)register.getWorkingElementFor(this);
        for (WireLogic wireLogic: this.outputPin.getWiresLogic()) {
            switchLogic.getOutput().registerObserver(wireLogic);
        }
    }

    @Override
    public void reset() {
    }
}
