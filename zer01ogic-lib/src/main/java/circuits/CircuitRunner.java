package circuits;

import interfaces.ICircuitRunner;
import interfaces.ICircuit;

public class CircuitRunner implements ICircuitRunner {
    private Thread simulationThread;
    private ICircuit innerCircuit;

    public CircuitRunner() {
        innerCircuit = new Circuit();
        simulationThread = new Thread(innerCircuit);
    }

    @Override
    public void startSimulation() {
        simulationThread.start();
    }

    @Override
    public void simulate() {
        innerCircuit.simulate();
    }

    @Override
    public void tick() {
        innerCircuit.tick();
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void pause() {
        innerCircuit.pause();
    }

    @Override
    public void unpause() {
        innerCircuit.unpause();
    }

    @Override
    public void stop() {
        innerCircuit.stop();
    }

    public ICircuit getInnerCircuit(){
        return innerCircuit;
    }
}
