package gateTests;

import simulation.gates.AndGate;
import simulation.values.MultibitValue;
import interfaces.elements.IObservableValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AndGateTests {
    @Test
    public void TestAndResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        AndGate gate = new AndGate((byte) 4);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();

        input1.setValue(1);
        gate.calculateOutputs();
        assertEquals(0, output.getValue().intValue(), "1 & 0 => 0");
        input2.setValue(1);
        gate.calculateOutputs();
        assertEquals(1, output.getValue().intValue(), "1 & 1 => 1");
        input1.setValue(0b1101);
        gate.calculateOutputs();
        assertEquals(1, output.getValue().intValue(), "1101 & 1 => 1");
        input2.setValue(0b111);
        gate.calculateOutputs();
        assertEquals(0b101, output.getValue().intValue(), "1101 & 111 => 101");
    }
}
