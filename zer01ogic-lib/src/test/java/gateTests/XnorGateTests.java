package gateTests;

import simulation.gates.XorGate;
import simulation.values.MultibitValue;
import simulation.values.NotTransform;
import simulation.values.TransformerMode;
import interfaces.elements.IObservableValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XnorGateTests {
    @Test
    public void TestXorResults() {
        MultibitValue input1 = new MultibitValue(0, (byte) 4);
        MultibitValue input2 = new MultibitValue(0, (byte) 4);
        XorGate gate = new XorGate((byte) 4);
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
        assertEquals(0b1111, output.getValue().intValue(), "0001 & 0001 => 1111");
        input1.setValue(0b1101);
        gate.calculateOutputs();
        assertEquals(0b0011, output.getValue().intValue(), "1101 & 0001 => 0011");
        input2.setValue(0b111);
        gate.calculateOutputs();
        assertEquals(0b0101, output.getValue().intValue(), "1101 & 0111 => 1010");
    }
}
