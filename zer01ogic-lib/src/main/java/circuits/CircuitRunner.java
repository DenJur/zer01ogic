package circuits;

import interfaces.ICircuitRunner;
import interfaces.ICircuit;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    public void reset() {
        throw new NotImplementedException();
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

    @Override
    public void switchMode(SimulationMode mode) {
        innerCircuit.switchMode(mode);
    }

    public ICircuit getInnerCircuit(){
        return innerCircuit;
    }
}
