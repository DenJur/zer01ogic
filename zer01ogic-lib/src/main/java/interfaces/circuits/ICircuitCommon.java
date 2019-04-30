package interfaces.circuits;

import exceptions.SimulationStoppingException;
import simulation.circuits.SimulationMode;

/**
 * Common functions between ICircuitRunner and the ICircuit objects
 */
public interface ICircuitCommon {
    /**
     * Resets simulation to it's initial state
     *
     * @throws SimulationStoppingException - thrown if simulation failed to shutdown as expected
     */
    void reset() throws SimulationStoppingException;

    /**
     * Pauses execution of simulation
     */
    void pause();

    /**
     * Unpause execution of simulation
     */
    void unpause();

    /**
     * Stops the simulation. Simulation can't be started after this other than using restart.
     *
     * @throws SimulationStoppingException - thrown if simulation failed to shutdown as expected
     */
    void stop() throws SimulationStoppingException;

    /**
     * Switch the mode that simulation is running in. Currently 2 modes are continues execution mode and ticking mode
     * where simulation is paused after each node update.
     *
     * @param mode - Mode to enable
     */
    void switchMode(SimulationMode mode);
}
