package interfaces.circuits;

public interface ICircuitRunner extends ICircuitCommon {
    void startSimulation();
    void assignInnerCircuit(ICircuit circuit);
    ICircuit getInnerCircuit();
}
