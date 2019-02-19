package interfaces;

import java.util.ArrayList;

public interface ILogicElement<T> extends IObserver {
    ArrayList<IObservableValue<T>> getOutputs();
    void addInput(IObservableValue<T> input);
    void calculateOutputs();
}
