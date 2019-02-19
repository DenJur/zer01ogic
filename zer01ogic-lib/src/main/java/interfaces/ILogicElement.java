package interfaces;

import java.util.ArrayList;

public interface ILogicElement<T> extends IObserver {
    ArrayList<IObservableValue<T>> getOutputs();
    IObservableValue<T> getOutputByIndex(int index);
    void addInput(IObservableValue<T> input);
    void calculateOutputs();
    void setParentCircuit(ICircuitQueue circuit);
}
