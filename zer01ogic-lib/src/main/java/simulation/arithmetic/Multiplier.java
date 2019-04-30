package simulation.arithmetic;

import simulation.values.MultibitValue;

/**
 * Binary multiplier
 */
public class Multiplier extends BaseArithmeticGate {

    public Multiplier(byte outputSize) {
        super(outputSize);
        bonusOutput = new MultibitValue(0, outputSize);
    }

    @Override
    public void calculateOutputs() {
        long result = 0;
        if (inputA != null) result = Integer.toUnsignedLong(inputA.getValue());
        if (inputB != null) result *= Integer.toUnsignedLong(inputB.getValue());
        //add carry bits
        if (bonusInput != null) result += Integer.toUnsignedLong(bonusInput.getValue());
        output.setValue((int) result);
        //output carry bits that did not fit the output
        bonusOutput.setValue((int) (result >> outputSize));
    }
}
