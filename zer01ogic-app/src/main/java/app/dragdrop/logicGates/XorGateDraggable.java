package app.dragdrop.logicGates;


import app.components.OutputPin;
import app.graphics.logicGates.XorGateGraphic;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.scene.layout.VBox;
import simulation.gates.AndGate;
import simulation.gates.XorGate;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class XorGateDraggable extends BaseLogicGateDraggable {

    public XorGateDraggable() {
        super(new VBox(new XorGateGraphic()));
        createPins(getPathStrokeWidth(XorGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        XorGate gate = new XorGate((byte) 1);
        register.addCircuitWorkingElement(this, gate);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((XorGate)register.getWorkingElementFor(this)).getOutput();
    }
}