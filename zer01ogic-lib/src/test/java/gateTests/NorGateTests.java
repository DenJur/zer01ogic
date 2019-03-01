package gateTests;

import circuits.gates.OrGate;
import circuits.values.MultibitValue;
import circuits.values.NotTransformWrapper;
import interfaces.IObservableValue;
import org.junit.Assert;
import org.junit.Test;

public class NorGateTests {
    @Test
    public void TestNorResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        OrGate gate = new OrGate((byte)4);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();
        gate.addValueTransformer(output,new NotTransformWrapper());
        output = gate.getOutput();

        gate.calculateOutputs();
        Assert.assertEquals("0000 & 0000 => 1111", 0b1111, output.getValue().intValue());
        input1.setValue(1);
        gate.calculateOutputs();
        Assert.assertEquals("0001 & 0000 => 1110", 0b1110, output.getValue().intValue());
        input2.setValue(1);
        gate.calculateOutputs();
        Assert.assertEquals("0001 & 0001 => 1110", 0b1110, output.getValue().intValue());
        input1.setValue(0b1101);
        gate.calculateOutputs();
        Assert.assertEquals("1101 & 0001 => 0010", 0b0010, output.getValue().intValue());
        input2.setValue(0b111);
        gate.calculateOutputs();
        Assert.assertEquals("1101 & 0111 => 0000", 0b0000, output.getValue().intValue());
    }

    @Test
    public void TestNorResultsSingleBit() {
        MultibitValue input1 = new MultibitValue(0, (byte) 1);
        MultibitValue input2 = new MultibitValue(0, (byte) 1);
        OrGate gate = new OrGate((byte)1);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();
        gate.addValueTransformer(output,new NotTransformWrapper());

        gate.calculateOutputs();
        Assert.assertEquals("0 & 0 => 1", 0b1, output.getValue().intValue());
        input1.setValue(1);
        gate.calculateOutputs();
        Assert.assertEquals("1 & 0 => 0", 0b0, output.getValue().intValue());
        input2.setValue(1);
        gate.calculateOutputs();
        Assert.assertEquals("1 & 1 => 0", 0b0, output.getValue().intValue());
        input1.setValue(0);
        gate.calculateOutputs();
        Assert.assertEquals("0 & 1 => 0", 0b0, output.getValue().intValue());
    }
}
