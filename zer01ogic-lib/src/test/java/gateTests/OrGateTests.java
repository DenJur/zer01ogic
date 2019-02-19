package gateTests;

import circuits.values.MultibitValue;
import circuits.gates.OrGate;
import interfaces.IObservableValue;
import org.junit.Assert;
import org.junit.Test;

public class OrGateTests {
    @Test
    public void TestOrResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        OrGate gate = new OrGate((byte)4);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();

        gate.calculateOutputs();
        Assert.assertEquals("0 & 0 => 0", 0, output.getValue().intValue());
        input1.setValue(1);
        gate.calculateOutputs();
        Assert.assertEquals("1 & 0 => 1", 1, output.getValue().intValue());
        input2.setValue(1);
        gate.calculateOutputs();
        Assert.assertEquals("1 & 1 => 1", 1, output.getValue().intValue());
        input1.setValue(0b1101);
        gate.calculateOutputs();
        Assert.assertEquals("1101 & 1 => 1101", 0b1101, output.getValue().intValue());
        input2.setValue(0b111);
        gate.calculateOutputs();
        Assert.assertEquals("1101 & 111 => 1111", 0b1111, output.getValue().intValue());
    }
}
