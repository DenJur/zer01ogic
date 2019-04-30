package simulation.circuits;

import interfaces.elements.ILogicElement;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IScheduledLogicElement;

import java.util.ArrayDeque;

/**
 * Circuit that uses wait/notify mechanism when queue is empty.
 */
public class SingleThreadCircuitWaiting extends SingleThreadCircuit {
    private final Object lock = new Object();

    public SingleThreadCircuitWaiting() {
        super();
        queue = new ArrayDeque<>();
    }

    @Override
    public void run() {
        if (scheduledLogicExecutor != null)
            scheduledLogicExecutor.start();
        finalized = true;
        ILogicElement element;
        while (!stopped) {
            synchronized (lock) {
                try {
                    if (!paused) {
                        element = queue.poll();
                        if (element == null) lock.wait();
                        else {
                            element.calculateOutputs();
                            if (mode == SimulationMode.TICK) pause();
                        }
                    } else {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void queueElementForUpdate(ILogicElement item) {
        synchronized (lock) {
            if (!queue.contains(item)) {
                queue.add(item);
                lock.notify();
            }
        }
    }

    @Override
    public void addCircuitWorkingElement(ILogicElementFrontEnd source, ILogicElement item) {
        synchronized (lock) {
            if (!workingNodes.containsKey(source) && !finalized) {
                workingNodes.put(source, item);
                queue.add(item);
                item.setParentCircuit(this);
                lock.notify();
            }
        }
    }

    @Override
    public void addCircuitWorkingElement(ILogicElementFrontEnd source, IScheduledLogicElement item) {
        synchronized (lock) {
            if (!workingNodes.containsKey(source) && !finalized) {
                workingNodes.put(source, item);
                item.setParentCircuit(this);
                if (scheduledLogicExecutor != null)
                    scheduledLogicExecutor.addScheduledLogicElement(item);
                lock.notify();
            }
        }
    }

    @Override
    public void unpause() {
        synchronized (lock) {
            super.unpause();
            lock.notify();
        }
    }

}
