import interfaces.circuits.ICircuitRunner;
import org.junit.jupiter.api.Test;
import simulation.DefaultCircuitBuilder;
import simulation.values.MultibitValue;
import testHelpers.TestAndWrapper;
import testHelpers.TestXNorWrapper;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class SingleThreadCircuitTests {
    /**
     * Tests a circuit consisting of a single and gate. Uses the whole and truth table.
     */
    @Test
    public void TestAndSingleBitSingleNode() throws Exception {
        DefaultCircuitBuilder builder = new DefaultCircuitBuilder();
        TestAndWrapper and = new TestAndWrapper((byte) 1);
        MultibitValue input1 = new MultibitValue(0, (byte) 1);
        MultibitValue input2 = new MultibitValue(0, (byte) 1);
        and.addInputValue(input1);
        and.addInputValue(input2);
        ICircuitRunner runner = builder
                .usingSingleThreadRunner()
                .buildWaitingCircuit()
                .build(Collections.singletonList(and));
        runner.startSimulation();

        try {
            Thread.sleep(10);
            assertEquals(0, ((Integer) and.output.getValue()).intValue(), "0 & 0 => 0");
            input1.setValue(1);
            Thread.sleep(10);
            assertEquals(0, ((Integer) and.output.getValue()).intValue(), "1 & 0 => 0");
            input2.setValue(1);
            Thread.sleep(10);
            assertEquals(1, ((Integer) and.output.getValue()).intValue(), "1 & 1 => 1");
            input1.setValue(0);
            Thread.sleep(10);
            assertEquals(0, ((Integer) and.output.getValue()).intValue(), "0 & 1 => 0");
        } catch (InterruptedException e) {
            fail(e);
        }
        runner.stop();
    }

    /**
     * Tests a circuit consisting of 3 and gates. 2 and gates have their inputs set manually and
     * are feeding their output into 3rd and gate.
     */
    @Test
    public void TestAndSingleBitMultiNode() throws Exception {
        DefaultCircuitBuilder builder = new DefaultCircuitBuilder();
        TestAndWrapper and = new TestAndWrapper((byte) 1);
        TestAndWrapper and2 = new TestAndWrapper((byte) 1);
        TestAndWrapper and3 = new TestAndWrapper((byte) 1);
        and3.addInputNode(and);
        and3.addInputNode(and2);
        MultibitValue input1 = new MultibitValue(0, (byte) 1);
        MultibitValue input2 = new MultibitValue(0, (byte) 1);
        MultibitValue input3 = new MultibitValue(0, (byte) 1);
        MultibitValue input4 = new MultibitValue(0, (byte) 1);
        and.addInputValue(input1);
        and.addInputValue(input2);
        and2.addInputValue(input3);
        and2.addInputValue(input4);

        ICircuitRunner runner = builder
                .usingSingleThreadRunner()
                .buildWaitingCircuit()
                .build(Arrays.asList(and, and2, and3));
        runner.startSimulation();

        try {
            //Test that 3rd gate does not output invalid value in an initial state
            Thread.sleep(10);
            assertEquals(0, ((Integer) and3.output.getValue()).intValue(), "0 & 0 & 0 & 0 => 0");

            //Test that both input and gates update their value and subsequently update the 3rd gate
            input1.setValue(1);
            input2.setValue(1);
            input3.setValue(1);
            input4.setValue(1);
            Thread.sleep(10);
            assertEquals(1, ((Integer) and3.output.getValue()).intValue(), "1 & 1 & 1 & 1 => 1");

            //Test that gate results are valid
            input3.setValue(0);
            Thread.sleep(10);
            assertEquals(0, ((Integer) and3.output.getValue()).intValue(), "1 & 1 & 0 & 1 => 0");

//            Thread.sleep(60000);
        } catch (InterruptedException e) {
            fail(e);
        }
        runner.stop();
    }

    /**
     * Tests a circuit consisting of 2 oscillating xnor gates. Xnor gates have their outputs connected to each other
     * and one of the gates has it's second input set to true.
     */
    @Test
    public void TestXNorOscillations() throws Exception {
        DefaultCircuitBuilder builder = new DefaultCircuitBuilder();

        TestXNorWrapper xnor = new TestXNorWrapper((byte) 1);
        TestXNorWrapper xnor2 = new TestXNorWrapper((byte) 1);
        xnor.addInputValue(new MultibitValue(0, (byte) 1));
        xnor2.addInputValue(new MultibitValue(1, (byte) 1));
        xnor.addInputNode(xnor2);
        xnor2.addInputNode(xnor);
        ICircuitRunner runner = builder
                .usingSingleThreadRunner()
                .buildWaitingCircuit()
                .build(Arrays.asList(xnor, xnor2));
        runner.startSimulation();

        try {
            Thread.sleep(100);
            runner.stop();
            System.out.println(xnor.counter.count);
            System.out.println(xnor2.counter.count);
            assertTrue(xnor.counter.count > 10, "More than 10 updates: Xnor1");
            assertTrue(xnor2.counter.count > 10, "More than 10 updates: Xnor2");
        } catch (InterruptedException e) {
            fail(e);
        }
    }

    @Test
    public void TestReset() throws Exception {
        DefaultCircuitBuilder builder = new DefaultCircuitBuilder();
        TestAndWrapper and = new TestAndWrapper((byte) 1);
        TestAndWrapper and2 = new TestAndWrapper((byte) 1);
        TestAndWrapper and3 = new TestAndWrapper((byte) 1);
        and3.addInputNode(and);
        and3.addInputNode(and2);
        MultibitValue input1 = new MultibitValue(0, (byte) 1);
        MultibitValue input2 = new MultibitValue(0, (byte) 1);
        MultibitValue input3 = new MultibitValue(0, (byte) 1);
        MultibitValue input4 = new MultibitValue(0, (byte) 1);
        and.addInputValue(input1);
        and.addInputValue(input2);
        and2.addInputValue(input3);
        and2.addInputValue(input4);

        ICircuitRunner runner = builder
                .usingSingleThreadRunner()
                .buildWaitingCircuit()
                .build(Arrays.asList(and, and2, and3));
        runner.startSimulation();

        try {
            //Test that both input and gates update their value and subsequently update the 3rd gate
            input1.setValue(1);
            input2.setValue(1);
            input3.setValue(1);
            input4.setValue(1);
            Thread.sleep(50);
            assertEquals(1, ((Integer) and3.output.getValue()).intValue(), "1 & 1 & 1 & 1 => 1");

            runner.reset();
            Thread.sleep(50);
            assertEquals(0, ((Integer) and3.output.getValue()).intValue(), "1 & 1 & 1 & 1 => 0");

            runner.unpause();
            Thread.sleep(50);
            assertEquals(1, ((Integer) and3.output.getValue()).intValue(), "1 & 1 & 1 & 1 => 1");
        } catch (InterruptedException e) {
            fail(e);
        }
        runner.stop();
    }
}
