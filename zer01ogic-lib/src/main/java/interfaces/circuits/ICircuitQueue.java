package interfaces.circuits;

import interfaces.elements.ILogicElement;

/**
 * Interface for simulation element update queue
 */
public interface ICircuitQueue {
    /**
     * Queues element up to be updated at the next opportunity to do so
     *
     * @param item - logic element to queue
     */
    void queueElementForUpdate(ILogicElement item);
}
