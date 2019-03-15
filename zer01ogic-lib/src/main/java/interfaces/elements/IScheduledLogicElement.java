package interfaces.elements;

import java.util.concurrent.TimeUnit;

public interface IScheduledLogicElement extends ILogicElement {
    TimeUnit getDelayTimeUnits();
    int getDelay();
    boolean isFixedDelay();
}
