package simulation.circuits;

import interfaces.elements.ILogicElement;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IScheduledLogicElement;

import java.util.LinkedList;

public class SingleThreadCircuitWaiting extends SingleThreadCircuit {
    private final Object lock = new Object();

    public SingleThreadCircuitWaiting() {
        super();
        queue = new LinkedList<>();
    }

    @Override
    public void run() {
        finalized = true;

        while (!stopped) {
            synchronized (lock) {
                try {
                    if (!queue.isEmpty() && !paused) {
                        queue.poll().calculateOutputs();
                        if (mode == SimulationMode.TICK) pause();
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
        System.out.println("hello");
    }

    @Override
    public void unpause() {
        synchronized (lock) {
            super.unpause();
            lock.notify();
        }
    }

}
