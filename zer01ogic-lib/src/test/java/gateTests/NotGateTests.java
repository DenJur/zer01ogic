package gateTests;

import circuits.values.MultibitValue;
import circuits.gates.NotGate;
import interfaces.IObservableValue;
import org.junit.Assert;
import org.junit.Test;

public class NotGateTests {
    @Test
    public void TestNotResultsSinglebit() {
        MultibitValue input = new MultibitValue();
        NotGate gate = new NotGate((byte)1);
        gate.addInput(input);
        IObservableValue<Integer> output = gate.getOutput();

        gate.calculateOutputs();
        Assert.assertEquals("0 => 1", 1, output.getValue().intValue());
        input.setValue(0b1);
        gate.calculateOutputs();
        Assert.assertEquals("1 => 0", 0, output.getValue().intValue());
    }

    @Test
    public void TestNotResultsMultibit() {
        MultibitValue input = new MultibitValue(0, (byte) 4);
        NotGate gate = new NotGate((byte) 4);
        gate.addInput(input);
        IObservableValue<Integer> output = gate.getOutput();

        gate.calculateOutputs();
        Assert.assertEquals("0000 => 1111", 0b1111, output.getValue().intValue());
        input.setValue(0b0101);
        gate.calculateOutputs();
        Assert.assertEquals("0101 => 1010", 0b1010, output.getValue().intValue());
    }
}
