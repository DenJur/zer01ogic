package interfaces.circuits;

/**
 * Interface for object that should manage how the circuit is running. Primary manages threading model used to run
 * circuit runnable
 */
public interface ICircuitRunner extends ICircuitCommon {
    /**
     * Start the simulation. Should be executed only once at the beginning of the simulation
     */
    void startSimulation();

    /**
     * Assign circuit object to the runner. This object is responsible for the actual simulation process
     *
     * @param circuit - circuit object to assign to runner
     */
    void assignInnerCircuit(ICircuit circuit);

    /**
     * Get circuit object assigned to the runner
     *
     * @return - current circuit object
     */
    ICircuit getInnerCircuit();
}
