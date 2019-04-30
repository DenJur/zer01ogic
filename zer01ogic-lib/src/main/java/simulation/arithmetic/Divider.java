package simulation.arithmetic;

import simulation.values.MultibitValue;

/**
 * Binary divider
 */
public class Divider extends BaseArithmeticGate {

    public Divider(byte outputSize) {
        super(outputSize);
        bonusOutput = new MultibitValue(0, outputSize);
    }

    @Override
    public void calculateOutputs() {
        long x = 0;
        long divisor = 0;
        if (inputB != null && inputB.getValue() != 0) divisor = Integer.toUnsignedLong(inputB.getValue());
        if (inputA != null) x = Integer.toUnsignedLong(inputA.getValue());
        //prevent division by 0
        if (divisor == 0) divisor = 1;
        //add remainder from previous divisor for stacking divisors
        if (bonusInput != null) x += Integer.toUnsignedLong(bonusInput.getValue()) << outputSize;
        output.setValue((int) Long.divideUnsigned(x, divisor));
        bonusOutput.setValue((int) Long.remainderUnsigned(x, divisor));
    }
}
