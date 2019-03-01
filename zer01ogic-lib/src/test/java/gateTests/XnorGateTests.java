package gateTests;

import circuits.gates.XorGate;
import circuits.values.MultibitValue;
import circuits.values.NotTransformWrapper;
import interfaces.IObservableValue;
import org.junit.Assert;
import org.junit.Test;

public class XnorGateTests {
    @Test
    public void TestXorResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        XorGate gate = new XorGate((byte)4);
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
        Assert.assertEquals("0001 & 0001 => 1111", 0b1111, output.getValue().intValue());
        input1.setValue(0b1101);
        gate.calculateOutputs();
        Assert.assertEquals("1101 & 0001 => 0011", 0b0011, output.getValue().intValue());
        input2.setValue(0b111);
        gate.calculateOutputs();
        Assert.assertEquals("1101 & 0111 => 1010", 0b0101, output.getValue().intValue());
    }
}
