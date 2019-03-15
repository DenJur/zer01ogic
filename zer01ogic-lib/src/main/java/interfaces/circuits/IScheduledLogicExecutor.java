package interfaces.circuits;

import interfaces.elements.IScheduledLogicElement;

public interface IScheduledLogicExecutor {
    void addScheduledLogicElement(IScheduledLogicElement element);
    void pause();
    void unpause();
    void stop() throws Exception;
    void start();
    void reset();
}
