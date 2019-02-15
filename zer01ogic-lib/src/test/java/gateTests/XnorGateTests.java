package gateTests;

import circuits.MultibitValue;
import circuits.gates.XnorGate;
import interfaces.IObservableValue;
import org.junit.Assert;
import org.junit.Test;

public class XnorGateTests {
    @Test
    public void TestXorResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        XnorGate gate = new XnorGate((byte)4);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();

        gate.update();
        Assert.assertEquals("0000 & 0000 => 1111", 0b1111, output.getValue().intValue());
        input1.setValue(1);
        gate.update();
        Assert.assertEquals("0001 & 0000 => 1110", 0b1110, output.getValue().intValue());
        input2.setValue(1);
        gate.update();
        Assert.assertEquals("0001 & 0001 => 1111", 0b1111, output.getValue().intValue());
        input1.setValue(0b1101);
        gate.update();
        Assert.assertEquals("1101 & 0001 => 0011", 0b0011, output.getValue().intValue());
        input2.setValue(0b111);
        gate.update();
        Assert.assertEquals("1101 & 0111 => 1010", 0b0101, output.getValue().intValue());
    }
}
