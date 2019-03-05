import circuits.SimpleCircuitBuilder;
import circuits.values.MultibitValue;
import interfaces.ICircuitRunner;
import org.junit.Assert;
import org.junit.Test;
import testHelpers.TestAndWrapper;
import testHelpers.TestXNorWrapper;

import java.util.Arrays;
import java.util.Collections;

public class CircuitTests {
    /**
     * Tests a circuit consisting of a single and gate. Uses the whole and truth table.
     */
    @Test
    public void TestAndSingleBitSingleNode() {
        SimpleCircuitBuilder builder = new SimpleCircuitBuilder();
        TestAndWrapper and = new TestAndWrapper((byte) 1);
        MultibitValue input1 = new MultibitValue(0, (byte) 1);
        MultibitValue input2 = new MultibitValue(0, (byte) 1);
        and.addInputValue(input1);
        and.addInputValue(input2);
        ICircuitRunner runner = builder.buildWaitingCircuit().build(Collections.singletonList(and));
        runner.startSimulation();

        try {
            Thread.sleep(10);
            Assert.assertEquals("0 & 0 => 0", 0, ((Integer)and.output.getValue()).intValue());
            input1.setValue(1);
            Thread.sleep(10);
            Assert.assertEquals("1 & 0 => 0", 0, ((Integer)and.output.getValue()).intValue());
            input2.setValue(1);
            Thread.sleep(10);
            Assert.assertEquals("1 & 1 => 1", 1, ((Integer)and.output.getValue()).intValue());
            input1.setValue(0);
            Thread.sleep(10);
            Assert.assertEquals("0 & 1 => 0", 0, ((Integer)and.output.getValue()).intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Assert.fail();
        }
        runner.stop();
    }

    /**
     * Tests a circuit consisting of 3 and gates. 2 and gates have their inputs set manually and
     * are feeding their output into 3rd and gate.
     */
    @Test
    public void TestAndSingleBitMultiNode() {
        SimpleCircuitBuilder builder = new SimpleCircuitBuilder();
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

        ICircuitRunner runner = builder.buildWaitingCircuit().build(Arrays.asList(and, and2, and3));
        runner.startSimulation();

        try {
            //Test that 3rd gate does not output invalid value in an initial state
            Thread.sleep(10);
            Assert.assertEquals("0 & 0 & 0 & 0 => 0", 0, ((Integer)and3.output.getValue()).intValue());

            //Test that both input and gates update their value and subsequently update the 3rd gate
            input1.setValue(1);
            input2.setValue(1);
            input3.setValue(1);
            input4.setValue(1);
            Thread.sleep(10);
            Assert.assertEquals("1 & 1 & 1 & 1 => 1", 1, ((Integer)and3.output.getValue()).intValue());

            //Test that gate results are valid
            input3.setValue(0);
            Thread.sleep(10);
            Assert.assertEquals("1 & 1 & 0 & 1 => 0", 0, ((Integer)and3.output.getValue()).intValue());

//            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Assert.fail();
        }
        runner.stop();
    }

    /**
     * Tests a circuit consisting of 2 oscillating xnor gates. Xnor gates have their outputs connected to each other
     * and one of the gates has it's second output set to true.
     */
    @Test
    public void TestXNorOscillations() {
        SimpleCircuitBuilder builder = new SimpleCircuitBuilder();

        TestXNorWrapper xnor = new TestXNorWrapper((byte) 1);
        TestXNorWrapper xnor2 = new TestXNorWrapper((byte) 1);
        xnor.addInputValue(new MultibitValue(0, (byte) 1));
        xnor2.addInputValue(new MultibitValue(1, (byte) 1));
        xnor.addInputNode(xnor2);
        xnor2.addInputNode(xnor);
        ICircuitRunner runner = builder.buildWaitingCircuit().build(Arrays.asList(xnor, xnor2));
        runner.startSimulation();

        try {
            Thread.sleep(100);
            runner.stop();
            System.out.println(xnor.counter.count);
            System.out.println(xnor2.counter.count);
            Assert.assertTrue("More than 10 updates: Xnor1", xnor.counter.count > 10);
            Assert.assertTrue("More than 10 updates: Xnor2", xnor2.counter.count > 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
