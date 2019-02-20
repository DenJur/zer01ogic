package circuits;

import interfaces.ICircuit;
import interfaces.ILogicElement;
import interfaces.ILogicElementFrontEnd;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedQueue;

class Circuit implements ICircuit {
    private boolean stopped;
    private boolean paused;
    private boolean shouldTick;
    private boolean finalized;

    private ConcurrentLinkedQueue<ILogicElement> queue;
    private Hashtable<ILogicElementFrontEnd, ILogicElement> workingNodes;


    public Circuit() {
        queue = new ConcurrentLinkedQueue<>();
        workingNodes = new Hashtable<>();
        finalized = false;
        stopped = false;
        shouldTick = false;
        paused = false;
    }

    @Override
    public void simulate() {
        shouldTick = false;
        paused = false;
    }

    @Override
    public synchronized void tick() {
        shouldTick = true;
        paused = false;
    }

    @Override
    public void reset() {
        throw new NotImplementedException();
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
    public void stop() {
        stopped = true;
    }

    @Override
    public void run() {
        finalized = true;
        while (!stopped) {
            if (!queue.isEmpty() && !paused) {
                queue.poll().calculateOutputs();
                if (shouldTick) paused = true;
            }
        }
    }

    @Override
    public synchronized void queueElementForUpdate(ILogicElement item) {
        if (!queue.contains(item))
            queue.add(item);
    }

    @Override
    public synchronized void addCircuitWorkingElement(ILogicElementFrontEnd source, ILogicElement item) {
        if (!workingNodes.containsKey(source) && !finalized) {
            workingNodes.put(source, item);
            queue.add(item);
            item.setParentCircuit(this);
        }
    }

    @Override
    public ILogicElement getWorkingElementFor(ILogicElementFrontEnd source) {
        return workingNodes.get(source);
    }


}
