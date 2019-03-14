package simulation.circuits;

import interfaces.circuits.ICircuit;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.circuits.ICircuitQueue;
import interfaces.circuits.IScheduledLogicExecutor;
import interfaces.elements.ILogicElement;
import interfaces.elements.ILogicElementFrontEnd;

import java.util.HashMap;
import java.util.Queue;

abstract class SingleThreadCircuit implements ICircuit, ICircuitQueue, ICircuitElementRegister, Runnable {
    protected boolean stopped;
    protected boolean paused;
    protected boolean finalized;
    protected SimulationMode mode;
    protected IScheduledLogicExecutor scheduledLogicExecutor;

    protected Queue<ILogicElement> queue;
    protected HashMap<ILogicElementFrontEnd, ILogicElement> workingNodes;

    protected SingleThreadCircuit() {
        workingNodes = new HashMap<>();
        finalized = false;
        stopped = false;
        mode = SimulationMode.NONSTOP;
        paused = false;
    }

    @Override
    public void reset() {
        workingNodes.forEach((iLogicElementFrontEnd, iLogicElement) -> {
            iLogicElement.reset();
            iLogicElementFrontEnd.reset();
        });
        queue.clear();
        workingNodes.forEach((iLogicElementFrontEnd, iLogicElement) -> {
            queue.add(iLogicElement);
        });
        if (scheduledLogicExecutor != null)
            scheduledLogicExecutor.reset();
        stopped = false;
    }

    @Override
    public void pause() {
        if (scheduledLogicExecutor != null)
            scheduledLogicExecutor.pause();
        paused = true;
    }

    @Override
    public void unpause() {
        if (scheduledLogicExecutor != null)
            scheduledLogicExecutor.unpause();
        paused = false;
    }

    @Override
    public void stop() throws Exception {
        if (scheduledLogicExecutor != null)
            scheduledLogicExecutor.stop();
        stopped = true;
    }

    @Override
    public void switchMode(SimulationMode mode) {
        this.mode = mode;
    }

    @Override
    public ILogicElement getWorkingElementFor(ILogicElementFrontEnd source) {
        return workingNodes.get(source);
    }

    @Override
    public void setUpRegister(ICircuitElementRegister register) {
//        if (register!==this) throw
    }

    @Override
    public Runnable getCircuitRunnable() {
        return this;
    }

    @Override
    public void addScheduledExecutor(IScheduledLogicExecutor scheduledExecutor) {
        scheduledLogicExecutor = scheduledExecutor;
    }
}
