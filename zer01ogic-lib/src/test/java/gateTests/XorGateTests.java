package gateTests;

import circuits.MultibitValue;
import circuits.gates.XorGate;
import interfaces.IObservableValue;
import org.junit.Assert;
import org.junit.Test;

public class XorGateTests {
    @Test
    public void TestXorResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        XorGate gate = new XorGate((byte)4);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();

        gate.update();
        Assert.assertEquals("0 & 0 => 0", 0, output.getValue().intValue());
        input1.setValue(1);
        gate.update();
        Assert.assertEquals("1 & 0 => 1", 1, output.getValue().intValue());
        input2.setValue(1);
        gate.update();
        Assert.assertEquals("1 & 1 => 0", 0, output.getValue().intValue());
        input1.setValue(0b1101);
        gate.update();
        Assert.assertEquals("1101 & 1 => 1100", 0b1100, output.getValue().intValue());
        input2.setValue(0b111);
        gate.update();
        Assert.assertEquals("1101 & 111 => 1010", 0b1010, output.getValue().intValue());
    }
}
