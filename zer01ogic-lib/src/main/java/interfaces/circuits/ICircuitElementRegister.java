package interfaces.circuits;

import interfaces.elements.ILogicElement;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IScheduledLogicElement;

/**
 * Interface used for circuit element registration to be later using inside the circuit object
 */
public interface ICircuitElementRegister {
    /**
     * Register regular element that does not require time based updates
     *
     * @param source - corresponding front-end element that represents circuit node
     * @param item   - simulation element of the node
     */
    void addCircuitWorkingElement(ILogicElementFrontEnd source, ILogicElement item);

    /**
     * Register element that requires time based update execution
     *
     * @param source - corresponding front-end element that represents circuit node
     * @param item   - simulation element of the node
     */
    void addCircuitWorkingElement(ILogicElementFrontEnd source, IScheduledLogicElement item);

    /**
     * Retrieve simulation element that corresponds to specified front-end element. If no element found returns null
     *
     * @param source - front-end element that corresponding simulation element should be retrieved for
     * @return - logic element or null if none were found
     */
    ILogicElement getWorkingElementFor(ILogicElementFrontEnd source);
}
