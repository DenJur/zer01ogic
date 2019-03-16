package app.dragdrop.logicGates;

import app.components.OutputPin;
import app.graphics.logicGates.NotGateGraphic;
import interfaces.elements.IObservableValue;
import javafx.scene.layout.VBox;
import simulation.gates.AndGate;
import simulation.gates.NotGate;
import interfaces.circuits.ICircuitElementRegister;


public class NotGateDraggable extends BaseLogicGateDraggable {

    public NotGateDraggable() {
        super(new VBox(new NotGateGraphic()));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        NotGate gate = new NotGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return null;
    }
}
