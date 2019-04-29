package interfaces.circuits;

import exceptions.SimulationBuildException;
import interfaces.elements.ILogicElementFrontEnd;

public interface ICircuitBuilder {
    ICircuitRunner build(Iterable<? extends ILogicElementFrontEnd> source) throws SimulationBuildException;
}
