package simulation.circuits;

import interfaces.circuits.IScheduledLogicExecutor;
import interfaces.elements.IScheduledLogicElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.*;


public class ScheduledLogicExecutor implements IScheduledLogicExecutor {
    private ScheduledExecutorService executor;
    private HashSet<IScheduledLogicElement> elements;
    private HashMap<ScheduledTask, ScheduledFuture> currentTasks;
    private ArrayList<ScheduledTask> tasks;
    private HashMap<ScheduledTask, Long> pausedTasks;
    private boolean finalized;

    public ScheduledLogicExecutor(byte threadCount) {
        executor = new ScheduledThreadPoolExecutor(threadCount);
        elements = new HashSet<>();
        tasks = new ArrayList<>();
        currentTasks = new HashMap<>();
        pausedTasks = new HashMap<>();
        finalized=false;
    }

    @Override
    public void addScheduledLogicElement(IScheduledLogicElement element) {
        if (!finalized && !elements.contains(element)) {
            elements.add(element);
            if (element.isFixedDelay())
                tasks.add(new ScheduledTask(element));
            else
                tasks.add(new ReScheduledTask(element));
        }
    }

    @Override
    public void pause() {
        pausedTasks.clear();
        currentTasks.forEach((scheduledTask, scheduledFuture) -> {
            scheduledFuture.cancel(false);
            pausedTasks.put(scheduledTask, scheduledFuture.getDelay(TimeUnit.NANOSECONDS));
        });
        currentTasks.clear();
    }

    @Override
    public void unpause() {
        pausedTasks.forEach(ScheduledTask::schedule);
        pausedTasks.clear();
    }

    @Override
    public void stop() {
        currentTasks.forEach((element, scheduledFuture) -> scheduledFuture.cancel(true));
        executor.shutdownNow();
        currentTasks.clear();
    }

    @Override
    public void start() {
        finalized=true;
        elements=null;
        tasks.forEach(ScheduledTask::schedule);
    }

    @Override
    public void reset() {
        currentTasks.forEach((element, scheduledFuture) -> scheduledFuture.cancel(true));
        currentTasks.clear();
        pausedTasks.clear();
        start();
    }

    private class ScheduledTask implements Runnable {
        IScheduledLogicElement logicElement;

        ScheduledTask(IScheduledLogicElement element) {
            this.logicElement = element;
        }

        void schedule() {
            currentTasks.put(this, executor.scheduleWithFixedDelay(this, logicElement.getDelay(),
                    logicElement.getDelay(), logicElement.getDelayTimeUnits()));
        }

        void schedule(long delay) {
            currentTasks.put(this, executor.scheduleAtFixedRate(this, delay,
                    logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay()), TimeUnit.NANOSECONDS));
        }

        @Override
        public void run() {
            logicElement.update();
        }
    }

    private class ReScheduledTask extends ScheduledTask {

        private long oldDelay;

        ReScheduledTask(IScheduledLogicElement element) {
            super(element);
        }

        @Override
        void schedule() {
            oldDelay=logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
            currentTasks.put(this, executor.scheduleAtFixedRate(this, logicElement.getDelay(),
                    logicElement.getDelay(), logicElement.getDelayTimeUnits()));
        }

        @Override
        void schedule(long delay) {
            oldDelay=logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
            currentTasks.put(this, executor.scheduleAtFixedRate(this, delay,
                    logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay()), TimeUnit.NANOSECONDS));
        }

        @Override
        public void run() {
            logicElement.update();
            long newDelay=logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
            if(newDelay!=oldDelay){
                currentTasks.get(this).cancel(false);
                this.schedule();
            }
        }
    }
}
