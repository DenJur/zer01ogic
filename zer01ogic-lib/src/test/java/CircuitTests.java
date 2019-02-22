import circuits.CircuitRunner;
import circuits.gates.*;
import circuits.values.MultibitValue;
import interfaces.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CircuitTests {
    @Test
    public void TestAndResults() {
        TestBuilder builder = new TestBuilder();
        AndWrapper and = new AndWrapper(new MultibitValue(0, (byte) 4),
                new MultibitValue(0, (byte) 4));
        AndWrapper and2 = new AndWrapper(new MultibitValue(0, (byte) 4),
                new MultibitValue(0, (byte) 4));
        AndWrapper and3 = new AndWrapper(and, and2);
        ICircuitRunner runner = builder.build(Arrays.asList(and, and2, and3));
        runner.startSimulation();

        try {
            and.input1.setValue(1);
            Thread.sleep(20);
            Assert.assertEquals("1 & 0 => 0", 0, and.output.getValue().intValue());
            and.input2.setValue(1);
            Thread.sleep(20);
            Assert.assertEquals("1 & 1 => 1", 1, and.output.getValue().intValue());
            and.input1.setValue(0b1101);
            Thread.sleep(20);
            Assert.assertEquals("1101 & 1 => 1", 1, and.output.getValue().intValue());
            and.input2.setValue(0b111);
            Thread.sleep(20);
            Assert.assertEquals("1101 & 111 => 101", 0b101, and.output.getValue().intValue());

            and.input1.setValue(1);
            and.input2.setValue(1);
            and2.input1.setValue(1);
            and2.input2.setValue(1);
            Thread.sleep(20);
            Assert.assertEquals("Should be 1", 1, and3.output.getValue().intValue());

            and2.input2.setValue(0);
            Thread.sleep(20);
            Assert.assertEquals("Should be 0", 0, and3.output.getValue().intValue());


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runner.stop();
    }

    @Test
    public void TestOscilationsResults() {
        TestBuilder builder = new TestBuilder();
        XnorWrapper xnor = new XnorWrapper(new MultibitValue(1, (byte) 1));
        XnorWrapper xnor2 = new XnorWrapper(new MultibitValue(0, (byte) 1));
        xnor.AddAnotherNor(xnor2);
        xnor2.AddAnotherNor(xnor);
        ICircuitRunner runner = builder.build(Arrays.asList(xnor, xnor2));
        runner.startSimulation();

        try {
            Thread.sleep(10000);
            runner.stop();
            System.out.println(xnor.counter.count);
            System.out.println(xnor2.counter.count);
            Assert.assertTrue("More than 10 updates: Xnor1", xnor.counter.count>10);
            Assert.assertTrue("More than 10 updates: Xnor2", xnor2.counter.count>10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class TestBuilder implements ICircuitBuilder {

        @Override
        public ICircuitRunner build(Iterable<ILogicElementFrontEnd> source) {
            CircuitRunner runner = new CircuitRunner();
            ArrayList<ILogicElementFrontEnd> s=new ArrayList<>();
            source.forEach(s::add);
            Collections.reverse(s);
            s.forEach(item -> item.createLogicElement(runner.getInnerCircuit()));
            s.forEach(item -> item.connectLogicElementInputs(runner.getInnerCircuit()));
            return runner;
        }
    }

    class AndWrapper implements ILogicElementFrontEnd {
        public IObservableValue<Integer> input1;
        public IObservableValue<Integer> input2;
        public IObservableValue<Integer> output;
        public AndWrapper and1;
        public AndWrapper and2;
        public AndGate gate;

        public AndWrapper(IObservableValue<Integer> input1, IObservableValue<Integer> input2) {
            this.input1 = input1;
            this.input2 = input2;
        }

        public AndWrapper(AndWrapper input1, AndWrapper input2) {
            this.and1 = input1;
            this.and2 = input2;
        }

        @Override
        public void createLogicElement(ICircuitElementRegister register) {
            gate = new AndGate((byte) 4);
            output = gate.getOutput();
            register.addCircuitWorkingElement(this, gate);
        }

        @Override
        public void connectLogicElementInputs(ICircuitElementRegister register) {
            if (and1 != null & and2 != null) {
                input1 = register.getWorkingElementFor(and1).getOutputByIndex(0);
                input2 = register.getWorkingElementFor(and2).getOutputByIndex(0);
            }
            gate.addInput(input1);
            gate.addInput(input2);
        }
    }

    class XnorWrapper implements ILogicElementFrontEnd {
        public IObservableValue<Integer> input1;
        public IObservableValue<Integer> input2;
        public IObservableValue<Integer> output;
        public XnorWrapper nor;
        public XnorGate gate;
        public UpdateCounter counter;

        public XnorWrapper(IObservableValue<Integer> input1) {
            this.input1 = input1;
        }

        public void AddAnotherNor(XnorWrapper gate){
            nor=gate;
        }

        @Override
        public void createLogicElement(ICircuitElementRegister register) {
            gate = new XnorGate((byte) 1);
            output = gate.getOutput();
            register.addCircuitWorkingElement(this, gate);
        }

        @Override
        public void connectLogicElementInputs(ICircuitElementRegister register) {
            if (nor != null) {
                input2 = register.getWorkingElementFor(nor).getOutputByIndex(0);
            }
            gate.addInput(input1);
            gate.addInput(input2);
            counter=new UpdateCounter();
            output.registerObserver(counter);
        }
    }

    class UpdateCounter implements IObserver{
        public long count=0;

        @Override
        public void update() {
            count++;
        }
    }
}
