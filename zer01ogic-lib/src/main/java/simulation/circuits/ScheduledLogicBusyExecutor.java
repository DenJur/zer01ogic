package simulation.circuits;

import exceptions.SimulationStoppingException;
import interfaces.circuits.IScheduledLogicExecutor;
import interfaces.elements.ILogicElement;
import interfaces.elements.IScheduledLogicElement;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Scheduled executor using busy waiting to achieve high precision real-time timings
 */
public class ScheduledLogicBusyExecutor implements IScheduledLogicExecutor, Runnable {
    private final Object lock = new Object();
    //set of elements that need time based updates
    private HashSet<IScheduledLogicElement> elements;
    //indicates if simulation was finished creating and has started
    private boolean finalized;
    //list of time based tasks that need execution
    private ArrayList<ScheduledTask> tasks;
    //thread used to run the executor
    private Thread counterThread;
    private volatile boolean stopped;
    private boolean paused;

    public ScheduledLogicBusyExecutor() {
        elements = new HashSet<>();
        tasks = new ArrayList<>();
        finalized = false;
        stopped = false;
    }

    @Override
    public void addScheduledLogicElement(IScheduledLogicElement element) {
        if (!finalized && !elements.contains(element)) {
            elements.add(element);
            tasks.add(new ScheduledTask(element));
        }
    }

    @Override
    public synchronized void pause() {
        paused = true;
    }

    @Override
    public synchronized void unpause() {
        paused = false;
    }

    @Override
    public synchronized void stop() throws SimulationStoppingException {
        stopped = true;
        //unlock thread if waiting to allow it to stop
        synchronized (lock) {
            lock.notifyAll();
        }
        if (counterThread != null) {
            try {
                counterThread.join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counterThread.isAlive()) {
                throw new SimulationStoppingException("Error stopping clocked simulation thread.");
            }
        }
    }

    @Override
    public void start() {
        finalized = true;
        //start counters for all tasks
        tasks.forEach(ScheduledTask::start);
        if (tasks.size() > 0) {
            counterThread = new Thread(this);
            counterThread.setPriority(10);
            counterThread.setDaemon(true);
            counterThread.start();
        }
    }

    @Override
    public synchronized void reset() {
//        tasks.forEach(ScheduledTask::start);
        elements.forEach(ILogicElement::reset);
        stopped = false;
    }

    @Override
    public void run() {
        long previousTime = System.nanoTime();
        long currentTime;
        long delta;
        while (!stopped) {
            //record current time and calculate time delta to the previous loop iteration
            currentTime = System.nanoTime();
            delta = currentTime - previousTime;
            //find when the next task needs to be executed
            long smallestWait = Long.MAX_VALUE;
            for (ScheduledTask task : tasks) {
                task.run(delta);
                if (task.nanosWait < smallestWait) smallestWait = task.nanosWait;
            }
            previousTime = currentTime;
            //if next task to be executed is less than 30 millis away use busy waiting else suspend thread
            //until next task becomes 20 milli away
            if (smallestWait <= 30000000)
                //use tight wait loop to get precise timings
                while (!stopped && (System.nanoTime() - previousTime < smallestWait)) ;
            else {
                try {
                    synchronized (lock) {
                        lock.wait(smallestWait / 1000000 - 20);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Class used to keep track of wait timers for individual circuit elements
     */
    private class ScheduledTask {
        private IScheduledLogicElement logicElement;
        private long nanosWait;

        ScheduledTask(IScheduledLogicElement element) {
            this.logicElement = element;
        }

        /**
         * Execute logic element update if it's timer reached 0 since the last update
         *
         * @param timeDelta - amount of nanoseconds passed since last time task was run
         */
        void run(long timeDelta) {
            //if paused, do not keep track of passed time
            if (!paused) {
                nanosWait -= timeDelta;
                if (nanosWait <= 0) {
                    nanosWait = logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
                    logicElement.update(null);
                }
            }
        }

        /**
         * Initialize delay timer
         */
        void start() {
            nanosWait = logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
        }
    }
}
