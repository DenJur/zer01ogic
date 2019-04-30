package simulation.arithmetic;

/**
 * Binary subtractor
 */
public class Subtractor extends BaseArithmeticGate {

    public Subtractor(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void calculateOutputs() {
        long result = 0;
        if (inputA != null) result = Integer.toUnsignedLong(inputA.getValue());
        if (inputB != null) result -= Integer.toUnsignedLong(inputB.getValue());
        //subtract borrow bit
        if (bonusInput != null) result -= Integer.toUnsignedLong(bonusInput.getValue());
        output.setValue((int) result);
        //set borrow bit if required
        if (result < 0)
            bonusOutput.setValue(1);
        else
            bonusOutput.setValue(0);
    }
}
