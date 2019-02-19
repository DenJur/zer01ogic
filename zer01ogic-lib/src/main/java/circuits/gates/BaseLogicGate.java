package circuits.gates;

import circuits.values.MultibitValue;
import interfaces.ICircuitQueue;
import interfaces.ILogicElement;
import interfaces.IObservableValue;

import java.util.ArrayList;

public abstract class BaseLogicGate implements ILogicElement<Integer> {
    protected IObservableValue<Integer> output;
    protected ArrayList<IObservableValue<Integer>> inputs;
    protected static final int maxWireValue = 0xFFFFFFFF;
    private ICircuitQueue parent;

    public BaseLogicGate(byte outputSize) {
        inputs = new ArrayList<>();
        output = new MultibitValue(0, outputSize);
    }

    @Override
    public void addInput(IObservableValue<Integer> input) {
        inputs.add(input);
        input.registerObserver(this);
    }

    @Override
    public ArrayList<IObservableValue<Integer>> getOutputs() {
        return new ArrayList<IObservableValue<Integer>>() {
            {
                add(output);
            }
        };
    }

    @Override
    public IObservableValue<Integer> getOutputByIndex(int index){
        if(index!=0) return null;
        return output;
    }

    @Override
    public void setParentCircuit(ICircuitQueue circuit){
        parent=circuit;
    }

    @Override
    public void update() {
        if(parent!=null){
            parent.queueElementForUpdate(this);
        }
    }

    public IObservableValue<Integer> getOutput(){
        return output;
    }
}
