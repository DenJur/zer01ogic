package interfaces.circuits;

public interface ICircuit extends ICircuitCommon {
    void setUpRegister(ICircuitElementRegister register);
    Runnable getCircuitRunnable();
}
