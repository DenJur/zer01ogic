package gateTests;

import circuits.values.MultibitValue;
import circuits.gates.NandGate;
import interfaces.IObservableValue;
import org.junit.Assert;
import org.junit.Test;

public class NandGateTests {
    @Test
    public void TestNandResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        NandGate gate = new NandGate((byte)4);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();

        input1.setValue(1);
        gate.calculateOutputs();
        Assert.assertEquals("1 & 0 => 0b1111", 0b1111, output.getValue().intValue());
        input2.setValue(1);
        gate.calculateOutputs();
        Assert.assertEquals("1 & 1 => 0b1110", 0b1110, output.getValue().intValue());
        input1.setValue(0b1101);
        gate.calculateOutputs();
        Assert.assertEquals("1101 & 1 => 0b1110", 0b1110, output.getValue().intValue());
        input2.setValue(0b0111);
        gate.calculateOutputs();
        Assert.assertEquals("1101 & 0111 => 0b1010", 0b1010, output.getValue().intValue());
    }
}
