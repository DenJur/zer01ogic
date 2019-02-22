package interfaces;

import java.util.ArrayList;

public interface ILogicElement<T> extends IObserver {
    ArrayList<IObservableValue> getOutputs();
    IObservableValue getOutputByIndex(int index);
    void addInput(IObservableValue input);
    void calculateOutputs();
    void setParentCircuit(ICircuitQueue circuit);
}
