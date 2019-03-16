package app.dragdrop.logicGates;


import app.components.OutputPin;
import app.graphics.logicGates.NorGateGraphic;
import com.sun.org.apache.xpath.internal.operations.Or;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.IObservableValue;
import javafx.scene.layout.VBox;
import simulation.gates.AndGate;
import simulation.gates.OrGate;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;

import static app.graphics.GraphicsHelper.getPathStrokeWidth;

public class NorGateDraggable extends BaseLogicGateDraggable {

    public NorGateDraggable() {
        super(new VBox(new NorGateGraphic()));
        createPins(getPathStrokeWidth(NorGateGraphic.styles));
    }

    @Override
    public void createLogicElement(ICircuitElementRegister register) {
        OrGate gate = new OrGate((byte) 1);
        gate.addValueTransformer(gate.getOutput(), new NotTransform(TransformerMode.SET));
        register.addCircuitWorkingElement(this, gate);
    }

    @Override
    public IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register) {
        return ((OrGate)register.getWorkingElementFor(this)).getOutput();
    }
}
