package interfaces.circuits;

import exceptions.SimulationStoppingException;
import interfaces.elements.IScheduledLogicElement;

/**
 * Interface used to represent circuit simulation component that is responsible for executing time based update events
 */
public interface IScheduledLogicExecutor {
    /**
     * Register scheduled logic element to the scheduler
     *
     * @param element - element to register
     */
    void addScheduledLogicElement(IScheduledLogicElement element);

    /**
     * Pause clock counting for the scheduler. Clocks for simulation elements will not update until scheduler is unpaused
     */
    void pause();

    /**
     * Unpause scheduler
     */
    void unpause();

    /**
     * Stops the scheduler. Can't be started after this other than using restart.
     *
     * @throws SimulationStoppingException - thrown if scheduler failed to shutdown as expected
     */
    void stop() throws SimulationStoppingException;

    /**
     * Start the scheduler. Should be executed only once at the beginning of the simulation
     */
    void start();

    /**
     * Reset state of the scheduler. All clocks for simulations will be reset to initial delay time
     */
    void reset();
}
