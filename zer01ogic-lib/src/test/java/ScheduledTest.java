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

import static org.junit.jupiter.api.Assertions.fail;

public class ScheduledTest {
    //TODO write actual tests
//    @Test
//    public void Dirty() throws Exception {
//        ClockWrapper clock=new ClockWrapper();
//        TestAndWrapper and = new TestAndWrapper((byte) 1);
//        and.addInputValue(clock.clock.getOutput());
//        and.addInputValue(new MultibitValue(1, (byte) 1));
//
//        ICircuitRunner runner = new DefaultCircuitBuilder()
//                .usingSingleThreadRunner()
//                .buildWaitingCircuit()
//                .addBusyScheduledExecutor()
//                .build(Arrays.asList(and, clock));
//        try {
//            runner.startSimulation();
//            for (int i=0;i<20; i++){
////                long start = System.nanoTime();
//                for(int l=0; l<1000;l++) {
//                    runner.stop();
//                    runner.reset();
//                    runner.unpause();
//                }
//                Thread.sleep(1000);
////                System.out.println("S time"+(System.nanoTime() - start) / 1000000);
////                System.out.println(executor.count);
//                System.out.println(and.counter.count);
//                System.out.println(clock.counter.count);
//            }
//
//        } catch (Exception e) {
//            fail(e);
//        }
//        runner.stop();
//    }

    private class ClockWrapper implements ILogicElementFrontEnd {
        public Clock clock;
        public ValueUpdateCounter counter;

        ClockWrapper(){
            clock = new Clock(1);
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
