package interfaces.circuits;

import interfaces.elements.ILogicElementFrontEnd;

public interface ICircuitBuilder {
    ICircuitRunner build(Iterable<? extends ILogicElementFrontEnd> source);
}
