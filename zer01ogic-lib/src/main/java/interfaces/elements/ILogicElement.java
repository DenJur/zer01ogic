package interfaces.elements;

import interfaces.circuits.ICircuitQueue;

import java.util.List;

public interface
ILogicElement extends IObserver {
    List<IObservableValue<Integer>> getOutputs();
    IObservableValue getOutputByIndex(int index);
    void addInput(IObservableValue input);
    void calculateOutputs();
    void setParentCircuit(ICircuitQueue circuit);
    void addValueTransformer(IObservableValue value, IValueTransformer transformer);
    void reset();
}
