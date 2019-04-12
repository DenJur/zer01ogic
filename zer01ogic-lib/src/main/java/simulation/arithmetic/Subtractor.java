package simulation.arithmetic;

public class Subtractor extends BaseArithmeticGate {

    public Subtractor(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void calculateOutputs() {
        long result = 0;
        if (inputA != null) result = Integer.toUnsignedLong(inputA.getValue());
        if (inputB != null) result -= Integer.toUnsignedLong(inputB.getValue());
        result -= Integer.toUnsignedLong(bonusInput.getValue());
        output.setValue((int) result);
        bonusOutput.setValue((int) (result >> outputSize));
    }
}
