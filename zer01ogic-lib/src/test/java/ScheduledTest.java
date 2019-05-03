import interfaces.circuits.ICircuitElementRegister;
import interfaces.circuits.ICircuitRunner;
import interfaces.elements.ILogicElementFrontEnd;
import org.junit.jupiter.api.Test;
import simulation.DefaultCircuitBuilder;
import simulation.io.Clock;
import simulation.values.MultibitValue;
import testHelpers.TestAndWrapper;
import testHelpers.ValueUpdateCounter;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ScheduledTest {
    @Test
    public void ClockAndTest() throws Exception {
        ClockWrapper clock = new ClockWrapper();
        TestAndWrapper and = new TestAndWrapper((byte) 1);
        and.addInputValue(clock.clock.getOutput());
        and.addInputValue(new MultibitValue(1, (byte) 1));

        ICircuitRunner runner = new DefaultCircuitBuilder()
                .usingSingleThreadRunner()
                .buildWaitingCircuit()
                .addBusyScheduledExecutor()
                .build(Arrays.asList(and, clock));
        try {
            runner.startSimulation();
            Thread.sleep(1010);
            assertEquals(10, and.counter.count, "And was not updated 10 times");
            assertEquals(10, clock.counter.count, "Clock was not updated 10 times");

        } catch (Exception e) {
            fail(e);
        }
        runner.stop();
    }

    private class ClockWrapper implements ILogicElementFrontEnd {
        public Clock clock;
        public ValueUpdateCounter counter;

        ClockWrapper() {
            clock = new Clock(100);
            counter = new ValueUpdateCounter();
            clock.getOutput().registerObserver(counter);
        }

        @Override
        public void createLogicElement(ICircuitElementRegister register) {
            register.addCircuitWorkingElement(this, clock);
        }

        @Override
        public void connectLogicElementInputs(ICircuitElementRegister register) {

        }

        @Override
        public void reset() {

        }
    }
}
