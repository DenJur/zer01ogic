package simulation.circuits;

import interfaces.elements.ILogicElement;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IScheduledLogicElement;

import java.util.ArrayDeque;

/**
 * Version of busy waiting circuit but without synchronization so should not be used with GUI controls or
 * scheduled elements. Unsafe.
 */
public class SingleThreadCircuitBusyUnsynced extends SingleThreadCircuit {

    public SingleThreadCircuitBusyUnsynced() {
        super();
        queue = new ArrayDeque<>();
    }

    @Override
    public void run() {
        finalized = true;
        ILogicElement element;
        while (!stopped) {
            if (!paused) {
                element = queue.poll();
                if (element == null) continue;
                element.calculateOutputs();
                if (mode == SimulationMode.TICK) pause();
            }
        }
    }

    @Override
    public void queueElementForUpdate(ILogicElement item) {
        if (!queue.contains(item))
            queue.offer(item);
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
    public void addCircuitWorkingElement(ILogicElementFrontEnd source, IScheduledLogicElement item) {
        if (!workingNodes.containsKey(source) && !finalized) {
            workingNodes.put(source, item);
            item.setParentCircuit(this);
            scheduledLogicExecutor.addScheduledLogicElement(item);
        }
    }

}
