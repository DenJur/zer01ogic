package gateTests;

import interfaces.elements.IObservableValue;
import org.junit.jupiter.api.Test;
import simulation.gates.OrGate;
import simulation.values.MultibitValue;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NorGateTests {
    @Test
    public void TestNorResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        OrGate gate = new OrGate((byte) 4);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();
        gate.addValueTransformer(output, new NotTransform(TransformerMode.SET));
        output = gate.getOutput();

        gate.calculateOutputs();
        assertEquals(0b1111, output.getValue().intValue(), "0000 & 0000 => 1111");
        input1.setValue(1);
        gate.calculateOutputs();
        assertEquals(0b1110, output.getValue().intValue(), "0001 & 0000 => 1110");
        input2.setValue(1);
        gate.calculateOutputs();
        assertEquals(0b1110, output.getValue().intValue(), "0001 & 0001 => 1110");
        input1.setValue(0b1101);
        gate.calculateOutputs();
        assertEquals(0b0010, output.getValue().intValue(), "1101 & 0001 => 0010");
        input2.setValue(0b111);
        gate.calculateOutputs();
        assertEquals(0b0000, output.getValue().intValue(), "1101 & 0111 => 0000");
    }

    @Test
    public void TestNorResultsSingleBit() {
        MultibitValue input1 = new MultibitValue(0, (byte) 1);
        MultibitValue input2 = new MultibitValue(0, (byte) 1);
        OrGate gate = new OrGate((byte) 1);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();
        gate.addValueTransformer(output, new NotTransform(TransformerMode.SET));
        output = gate.getOutput();

        gate.calculateOutputs();
        assertEquals(0b1, output.getValue().intValue(), "0 & 0 => 1");
        input1.setValue(1);
        gate.calculateOutputs();
        assertEquals(0b0, output.getValue().intValue(), "1 & 0 => 0");
        input2.setValue(1);
        gate.calculateOutputs();
        assertEquals(0b0, output.getValue().intValue(), "1 & 1 => 0");
        input1.setValue(0);
        gate.calculateOutputs();
        assertEquals(0b0, output.getValue().intValue(), "0 & 1 => 0");
    }
}
