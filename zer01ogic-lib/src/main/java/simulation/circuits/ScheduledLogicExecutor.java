package simulation.circuits;

import interfaces.circuits.IScheduledLogicExecutor;
import interfaces.elements.IScheduledLogicElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Scheduled executor using ScheduledExecutorService. Requires small amount of system resources but is not precise
 * when used in sub 100 millisecond frequency tasks
 */
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
        finalized = false;
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
            //save current delays to reschedule tasks when unpaused
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
        finalized = true;
        elements = null;
        tasks.forEach(ScheduledTask::schedule);
    }

    @Override
    public void reset() {
        currentTasks.forEach((element, scheduledFuture) -> scheduledFuture.cancel(true));
        currentTasks.clear();
        pausedTasks.clear();
        start();
    }

    /**
     * Class used for tasks that have a fixed execution schedule
     */
    private class ScheduledTask implements Runnable {
        IScheduledLogicElement logicElement;

        ScheduledTask(IScheduledLogicElement element) {
            this.logicElement = element;
        }

        /**
         * Schedule task for the delay provided by logic element
         */
        void schedule() {
            currentTasks.put(this, executor.scheduleWithFixedDelay(this, logicElement.getDelay(),
                    logicElement.getDelay(), logicElement.getDelayTimeUnits()));
        }

        /**
         * Schedule task wit specified delay. Used to reschedule task after pause
         *
         * @param delay - delay in nanoseconds
         */
        void schedule(long delay) {
            currentTasks.put(this, executor.scheduleAtFixedRate(this, delay,
                    logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay()), TimeUnit.NANOSECONDS));
        }

        @Override
        public void run() {
            logicElement.update(null);
        }
    }

    /**
     * Class used for tasks that have a non-fixed execution schedule
     */
    private class ReScheduledTask extends ScheduledTask {

        private long oldDelay;

        ReScheduledTask(IScheduledLogicElement element) {
            super(element);
        }

        @Override
        void schedule() {
            oldDelay = logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
            currentTasks.put(this, executor.scheduleAtFixedRate(this, logicElement.getDelay(),
                    logicElement.getDelay(), logicElement.getDelayTimeUnits()));
        }

        @Override
        void schedule(long delay) {
            oldDelay = logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
            currentTasks.put(this, executor.scheduleAtFixedRate(this, delay,
                    logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay()), TimeUnit.NANOSECONDS));
        }

        @Override
        public void run() {
            logicElement.update(null);
            //get delay until the next update execution and reschedule to that delay
            long newDelay = logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
            if (newDelay != oldDelay) {
                currentTasks.get(this).cancel(false);
                this.schedule();
            }
        }
    }
}
