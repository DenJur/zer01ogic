package simulation.circuits;

import exceptions.SimulationStoppingException;
import interfaces.circuits.ICircuit;
import interfaces.circuits.ICircuitRunner;

/**
 * Circuit runner for single thread circuits.
 */
public class SingleThreadCircuitRunner implements ICircuitRunner {
    private Thread simulationThread;
    private ICircuit innerCircuit;

    @Override
    public void assignInnerCircuit(ICircuit circuit) {
        innerCircuit = circuit;
        simulationThread = new Thread(innerCircuit.getCircuitRunnable());
        simulationThread.setPriority(10);
        simulationThread.setDaemon(true);
    }

    @Override
    public void startSimulation() {
        if (simulationThread != null && simulationThread.getState() == Thread.State.NEW)
            simulationThread.start();
    }

    @Override
    public void reset() throws SimulationStoppingException {
        if (innerCircuit != null) {
            stop();
            innerCircuit.reset();
            innerCircuit.pause();
            simulationThread = new Thread(innerCircuit.getCircuitRunnable());
            simulationThread.setPriority(10);
            simulationThread.setDaemon(true);
            simulationThread.start();
        }
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
    public void stop() throws SimulationStoppingException {
        if (innerCircuit != null && simulationThread != null) {
            innerCircuit.stop();
            innerCircuit.switchMode(SimulationMode.NONSTOP);
            innerCircuit.unpause();
            try {
                simulationThread.join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (simulationThread.isAlive()) {
                throw new SimulationStoppingException("Error stopping logic simulation thread.");
            }
        }
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
