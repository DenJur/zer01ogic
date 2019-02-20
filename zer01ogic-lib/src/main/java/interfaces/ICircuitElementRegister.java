package interfaces;

public interface ICircuitElementRegister {
    void addCircuitWorkingElement(ILogicElementFrontEnd source,ILogicElement item);
    ILogicElement getWorkingElementFor(ILogicElementFrontEnd source);
}
