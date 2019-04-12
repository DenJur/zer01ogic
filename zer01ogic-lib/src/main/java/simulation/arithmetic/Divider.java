package simulation.arithmetic;

import simulation.values.MultibitValue;

public class Divider extends BaseArithmeticGate {

    public Divider(byte outputSize) {
        super(outputSize);
        bonusOutput = new MultibitValue(0, outputSize);
    }

    @Override
    public void calculateOutputs() {
        long x = 0;
        long divisor = 1;
        if(inputB!=null && inputB.getValue()!=0) divisor|=inputB.getValue();
        if(inputA!=null) x=inputA.getValue();
        x|=(bonusInput.getValue()<<outputSize);
        output.setValue((int) Long.divideUnsigned(x,divisor));
        bonusOutput.setValue((int)Long.remainderUnsigned(x,divisor));
    }
}
