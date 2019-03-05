package gateTests;

import circuits.gates.NotGate;
import circuits.values.MultibitValue;
import interfaces.IObservableValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotGateTests {
    @Test
    public void TestNotResultsSinglebit() {
        MultibitValue input = new MultibitValue();
        NotGate gate = new NotGate((byte) 1);
        gate.addInput(input);
        IObservableValue<Integer> output = gate.getOutput();

        gate.calculateOutputs();
        assertEquals(1, output.getValue().intValue(), "0 => 1");
        input.setValue(0b1);
        gate.calculateOutputs();
        assertEquals(0, output.getValue().intValue(), "1 => 0");
    }

    @Test
    public void TestNotResultsMultibit() {
        MultibitValue input = new MultibitValue(0, (byte) 4);
        NotGate gate = new NotGate((byte) 4);
        gate.addInput(input);
        IObservableValue<Integer> output = gate.getOutput();

        gate.calculateOutputs();
        assertEquals(0b1111, output.getValue().intValue(), "0000 => 1111");
        input.setValue(0b0101);
        gate.calculateOutputs();
        assertEquals(0b1010, output.getValue().intValue(), "0101 => 1010");
    }
}
