package gateTests;

import interfaces.elements.IObservableValue;
import org.junit.jupiter.api.Test;
import simulation.gates.AndGate;
import simulation.values.MultibitValue;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NandGateTests {
    @Test
    public void TestNandResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        AndGate gate = new AndGate((byte) 4);
        gate.addInput(input1);
        gate.addInput(input2);
        IObservableValue<Integer> output = gate.getOutput();
        gate.addValueTransformer(output, new NotTransform(TransformerMode.SET));
        output = gate.getOutput();

        input1.setValue(1);
        gate.calculateOutputs();
        assertEquals(0b1111, output.getValue().intValue(), "1 & 0 => 0b1111");
        input2.setValue(1);
        gate.calculateOutputs();
        assertEquals(0b1110, output.getValue().intValue(), "1 & 1 => 0b1110");
        input1.setValue(0b1101);
        gate.calculateOutputs();
        assertEquals(0b1110, output.getValue().intValue(), "1101 & 1 => 0b1110");
        input2.setValue(0b0111);
        gate.calculateOutputs();
        assertEquals(0b1010, output.getValue().intValue(), "1101 & 0111 => 0b1010");
    }
}
