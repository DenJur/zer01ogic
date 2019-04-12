package simulation.arithmetic;

import interfaces.elements.IObservableValue;

public class Adder extends BaseArithmeticGate {

    public Adder(byte outputSize) {
        super(outputSize);
    }

    @Override
    public void calculateOutputs() {
        long result=0;
        if(inputA!=null)
            result+=inputA.getValue();
        if(inputB!=null)
            result+=inputB.getValue();
        result+=bonusInput.getValue();
        output.setValue((int)result);
        bonusOutput.setValue((int)(result>>outputSize));
    }
}
