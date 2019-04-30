package interfaces.elements;

import interfaces.circuits.ICircuitElementRegister;

/**
 * Interface used for front-end representations of simulation elements
 */
public interface ILogicElementFrontEnd {
    /**
     * Is called during circuit building process to create simulation element that corresponds to this front-end element
     *
     * @param register - circuit element register that should be used to add simulation element to the circuit
     */
    void createLogicElement(ICircuitElementRegister register);

    /**
     * Called after all simulation elements are created and we can create connections between those element
     *
     * @param register - circuit element register that can be used to retrieve simulation element that corresponds
     *                 to this front-end element
     */
    void connectLogicElementInputs(ICircuitElementRegister register);

    /**
     * Called when the simulation is reset so that front-end can update it's state
     */
    void reset();
}
