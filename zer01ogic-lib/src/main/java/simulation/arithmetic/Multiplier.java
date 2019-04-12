package simulation.arithmetic;

import simulation.values.MultibitValue;

public class Multiplier extends BaseArithmeticGate {

    public Multiplier(byte outputSize) {
        super(outputSize);
        bonusOutput = new MultibitValue(0, outputSize);
    }

    @Override
    public void calculateOutputs() {
        long result = 0;
        if (inputA != null) result |= inputA.getValue();
        if (inputB != null) result *= inputB.getValue();
        result += bonusInput.getValue();
        output.setValue((int) result);
        bonusOutput.setValue((int) (result >> outputSize));
    }
}
