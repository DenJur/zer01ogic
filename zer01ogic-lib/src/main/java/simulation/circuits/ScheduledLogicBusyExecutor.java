package simulation.circuits;

import interfaces.circuits.IScheduledLogicExecutor;
import interfaces.elements.ILogicElement;
import interfaces.elements.IScheduledLogicElement;

import java.util.ArrayList;
import java.util.HashSet;

public class ScheduledLogicBusyExecutor implements IScheduledLogicExecutor, Runnable {
    private final Object lock = new Object();
    private HashSet<IScheduledLogicElement> elements;
    private boolean finalized;
    private ArrayList<ScheduledTask> tasks;
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
    public synchronized void stop() throws Exception {
        stopped = true;
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
                //TODO Throw
                throw new Exception("23");
            }
        }
    }

    @Override
    public void start() {
        finalized = true;
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
            currentTime = System.nanoTime();
            delta = currentTime - previousTime;
            long smallestWait = Long.MAX_VALUE;
            for (ScheduledTask task : tasks) {
                task.run(delta);
                if (task.nanosWait < smallestWait) smallestWait = task.nanosWait;
            }
            previousTime = currentTime;
            if (smallestWait <= 30000000)
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

    private class ScheduledTask {
        private IScheduledLogicElement logicElement;
        private long nanosWait;

        ScheduledTask(IScheduledLogicElement element) {
            this.logicElement = element;
        }

        void run(long timeDelta) {
            if (!paused) {
                nanosWait -= timeDelta;
                if (nanosWait <= 0) {
                    nanosWait = logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
                    logicElement.update(null);
                }
            }
        }

        void start() {
            nanosWait = logicElement.getDelayTimeUnits().toNanos(logicElement.getDelay());
        }
    }
}
