package simulation.circuits;

import interfaces.elements.ILogicElement;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IScheduledLogicElement;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SingleThreadCircuitBusy extends SingleThreadCircuit {

    public SingleThreadCircuitBusy() {
        super();
        queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void run() {
        finalized = true;
        ILogicElement element;
        while (!stopped) {
            if (!paused) {
                element = queue.poll();
                if (element != null) element.calculateOutputs();
                if (mode == SimulationMode.TICK) pause();
            }
        }
    }

    @Override
    public synchronized void queueElementForUpdate(ILogicElement item) {
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
        System.out.println("hello");
    }

}
