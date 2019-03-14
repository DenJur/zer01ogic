package interfaces.circuits;

import simulation.circuits.SimulationMode;

public interface ICircuitCommon {
    void reset() throws Exception;
    void pause();
    void unpause();
    void stop() throws Exception;
    void switchMode(SimulationMode mode);
}
