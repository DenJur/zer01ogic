package app.dragdrop.logicGates;

import app.components.OutputPin;
import app.graphics.logicGates.AndGateGraphic;
import app.models.WireLogic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.layout.VBox;
import simulation.gates.AndGate;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;


public class AndGateDraggable extends BaseLogicGateDraggable {

    public AndGateDraggable() {
        super(new VBox(new AndGateGraphic()));
        createPins(getPathStrokeWidth(AndGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        AndGate gate = new AndGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((AndGate)register.getWorkingElementFor(this)).getOutput();
    }

}
