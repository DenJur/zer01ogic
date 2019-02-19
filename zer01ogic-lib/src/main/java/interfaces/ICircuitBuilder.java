package interfaces;

public interface ICircuitBuilder {
    ICircuitRunner build(Iterable<ILogicElementFrontEnd> source);
}
