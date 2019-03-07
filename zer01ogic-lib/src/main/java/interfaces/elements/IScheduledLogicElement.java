package interfaces.elements;

import java.util.concurrent.TimeUnit;

public interface IScheduledLogicElement extends ILogicElement,Runnable {
    TimeUnit getDelayTimeUnits();
    long getDelay();
    boolean isFixedDelay();
}
