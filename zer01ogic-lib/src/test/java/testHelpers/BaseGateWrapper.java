package testHelpers;

import interfaces.ICircuitElementRegister;
import interfaces.ILogicElement;
import interfaces.ILogicElementFrontEnd;
import interfaces.IObservableValue;

import java.util.ArrayList;

public abstract class BaseGateWrapper implements ILogicElementFrontEnd {
    public ArrayList<IObservableValue<Integer>> inputValues;
    public ArrayList<ILogicElementFrontEnd> inputNodes;
    public IObservableValue output;
    public ILogicElement gate;
    public byte outputSize;

    public BaseGateWrapper(byte outputSize) {
        this.inputValues = new ArrayList<>();
        this.inputNodes = new ArrayList<>();
        this.outputSize = outputSize;
    }

    @Override
    public void connectLogicElementInputs(ICircuitElementRegister register) {
        inputNodes.forEach(x -> gate.addInput(register.getWorkingElementFor(x).getOutputByIndex(0)));
        inputValues.forEach(x -> gate.addInput(x));
    }

    @Override
    public void reset() {
    }

    public void addInputNode(ILogicElementFrontEnd node) {
        this.inputNodes.add(node);
    }

    public void addInputValue(IObservableValue<Integer> value) {
        this.inputValues.add(value);
    }

}
