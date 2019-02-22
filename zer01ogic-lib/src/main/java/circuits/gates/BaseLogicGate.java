package circuits.gates;

import circuits.values.MultibitValue;
import interfaces.ICircuitQueue;
import interfaces.ILogicElement;
import interfaces.IObservableValue;

import java.util.ArrayList;

public abstract class BaseLogicGate implements ILogicElement {
    protected IObservableValue<Integer> output;
    protected ArrayList<IObservableValue<Integer>> inputs;
    protected static final int maxWireValue = 0xFFFFFFFF;
    private ICircuitQueue parent;

    public BaseLogicGate(byte outputSize) {
        inputs = new ArrayList<>();
        output = new MultibitValue(0, outputSize);
    }

    @Override
    public void addInput(IObservableValue input) {
        if(Integer.class.isAssignableFrom(input.getValueType())) {
            inputs.add(input);
            input.registerObserver(this);
        }
    }

    @Override
    public ArrayList<IObservableValue> getOutputs() {
        return new ArrayList<IObservableValue>() {
            {
                add(output);
            }
        };
    }

    @Override
    public IObservableValue getOutputByIndex(int index){
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
