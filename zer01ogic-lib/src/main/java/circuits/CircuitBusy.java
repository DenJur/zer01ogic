package circuits;

import interfaces.ILogicElement;
import interfaces.ILogicElementFrontEnd;

import java.util.concurrent.ConcurrentLinkedQueue;

class CircuitBusy extends Circuit {

    public CircuitBusy() {
        super();
        queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void reset(){

    }

    @Override
    public void run() {
        finalized = true;
        ILogicElement element;
        while (!stopped) {
            if (!paused) {
                element=queue.poll();
                if(element!=null) element.calculateOutputs();
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

}
