package interfaces;

import circuits.SimulationMode;

public interface ICircuitCommon {
    void reset();
    void pause();
    void unpause();
    void stop();
    void switchMode(SimulationMode mode);
}
