package interfaces.circuits;

/**
 * Interface used to represent Objects that perform the simulation
 */
public interface ICircuit extends ICircuitCommon {
    /**
     * Used to pass simulation element register to the circuit in cases where a separate register is used during building
     * process.
     *
     * @param register - register that will be used
     */
    void setUpRegister(ICircuitElementRegister register);

    /**
     * Returns Runnable that can be used t startup the simulation
     *
     * @return - simulation runnable
     */
    Runnable getCircuitRunnable();

    /**
     * Used to attached executor for time based circuit elements.
     * If no executor is attached time based events won't execute.
     *
     * @param scheduledExecutor - executor implementing IScheduledLogicExecutor interface
     */
    void addScheduledExecutor(IScheduledLogicExecutor scheduledExecutor);
}
