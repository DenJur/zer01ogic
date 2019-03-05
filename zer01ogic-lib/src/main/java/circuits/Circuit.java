package circuits;

import interfaces.ICircuit;
import interfaces.ILogicElement;
import interfaces.ILogicElementFrontEnd;

import java.util.HashMap;
import java.util.Queue;

abstract class  Circuit implements ICircuit {
    protected boolean stopped;
    protected boolean paused;
    protected boolean finalized;
    protected SimulationMode mode;

    protected Queue<ILogicElement> queue;
    protected HashMap<ILogicElementFrontEnd, ILogicElement> workingNodes;

    protected Circuit() {
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
        stopped=false;
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void unpause() {
        paused = false;
    }

    @Override
    public void stop() { stopped = true; }

    @Override
    public void switchMode(SimulationMode mode) {
        this.mode = mode;
//        unpause();
    }

    @Override
    public ILogicElement getWorkingElementFor(ILogicElementFrontEnd source) {
        return workingNodes.get(source);
    }

}
