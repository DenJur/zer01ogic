package interfaces.elements;

import interfaces.circuits.ICircuitQueue;

import java.util.List;

/**
 * Interface for objects that represent single circuit element used in the simulation of the circuit
 */
public interface ILogicElement extends IObserver {
    /**
     * Returns list of all element outputs. No particular order is guarantied.
     *
     * @return - list of output observable values
     */
    List<IObservableValue<Integer>> getOutputs();

    /**
     * Calculate output values for the element based on current inputs
     */
    void calculateOutputs();

    /**
     * Assigns element to a circuit queue object that will be used to queue this element for updates during simulation
     *
     * @param circuit - circuit queue
     */
    void setParentCircuit(ICircuitQueue circuit);

    /**
     * Add value transformer that will be assigned to input/output of the element if the observable value
     * given belongs to the element
     *
     * @param value       - observable value to attach value transformer to
     * @param transformer - value transformer to use
     */
    void addValueTransformer(IObservableValue value, IValueTransformer transformer);

    /**
     * Reset state of the node to the initial state
     */
    void reset();
}
