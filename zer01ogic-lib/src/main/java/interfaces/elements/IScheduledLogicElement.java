package interfaces.elements;

import java.util.concurrent.TimeUnit;

/**
 * Extension of logic element interface that adds time based extensions
 */
public interface IScheduledLogicElement extends ILogicElement {
    /**
     * Get time units used to measure time based update delay
     *
     * @return - TimeUnit object
     */
    TimeUnit getDelayTimeUnits();

    /**
     * Get delay of the time based updates
     *
     * @return - amount of time measured in time unites returned in getDelayTimeUnits() method
     */
    int getDelay();

    /**
     * Indicates whether delay is fixed or if it can change during simulation
     *
     * @return - true if delay is fixed
     */
    boolean isFixedDelay();
}
