package simulation.arithmetic;

/**
 * Binary full-adder
 */
public class Adder extends BaseArithmeticGate {

    public Adder(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void calculateOutputs() {
        long result = 0;
        if (inputA != null) result += Integer.toUnsignedLong(inputA.getValue());
        if (inputB != null) result += Integer.toUnsignedLong(inputB.getValue());
        //add carry bit
        if (bonusInput != null) result += bonusInput.getValue();
        //value should be automatically truncated during output assigment
        output.setValue((int) result);
        //calculate carry bit
        bonusOutput.setValue((int) (result >> outputSize));
    }
}
