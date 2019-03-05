package circuits;

import interfaces.ICircuit;
import interfaces.ICircuitRunner;

public class CircuitRunner implements ICircuitRunner {
    private Thread simulationThread;
    private ICircuit innerCircuit;

    @Override
    public void assignInnerCircuit(ICircuit circuit) {
        innerCircuit = circuit;
        simulationThread = new Thread(innerCircuit);
    }

    @Override
    public void startSimulation() {
        if (simulationThread != null)
            simulationThread.start();
    }

    @Override
    public void reset() {
//        if (innerCircuit != null) {
//            innerCircuit.stop();
            innerCircuit.reset();

//        }
    }

    @Override
    public void pause() {
        if (innerCircuit != null)
            innerCircuit.pause();
    }

    @Override
    public void unpause() {
        if (innerCircuit != null)
            innerCircuit.unpause();
    }

    @Override
    public void stop() {
        if (innerCircuit != null)
            innerCircuit.stop();
    }

    @Override
    public void switchMode(SimulationMode mode) {
        if (innerCircuit != null)
            innerCircuit.switchMode(mode);
    }

    @Override
    public ICircuit getInnerCircuit() {
        return innerCircuit;
    }
}
