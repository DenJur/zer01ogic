package app.dragdrop.logicGates;

import app.components.OutputPin;
import app.graphics.logicGates.OrGateGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.scene.layout.VBox;
import simulation.gates.AndGate;
import simulation.gates.OrGate;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class OrGateDraggable extends BaseLogicGateDraggable {

    public OrGateDraggable() {
        super(new VBox(new OrGateGraphic()));
        createPins(getPathStrokeWidth(OrGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        OrGate gate = new OrGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((OrGate)register.getWorkingElementFor(this)).getOutput();
    }
}