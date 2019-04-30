package interfaces.circuits;

import exceptions.SimulationBuildException;
import interfaces.elements.ILogicElementFrontEnd;

/**
 * Interface for a builder class that can be used to build a CircuitRunner object from a collection of LogicElementFrontEnd
 * objects.
 */
public interface ICircuitBuilder {
    /**
     * Build ICircuitRunner object based on previously set build parameters
     *
     * @param source - collection of ILogicElementFrontEnd or it's children that will be used to create the circuit
     * @return - CircuitRunner that can be used to control operation of the circuit
     * @throws SimulationBuildException - thrown if there was some error during building process. For example, some
     *                                  of the required parameters for building were not specified.
     */
    ICircuitRunner build(Iterable<? extends ILogicElementFrontEnd> source) throws SimulationBuildException;
}
