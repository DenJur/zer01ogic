package circuits;

import interfaces.ICircuitBuilder;
import interfaces.ICircuitRunner;
import interfaces.ILogicElementFrontEnd;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleCircuitBuilder implements ICircuitBuilder {
    @Override
    public ICircuitRunner build(Iterable<ILogicElementFrontEnd> source) {
        CircuitRunner runner = new CircuitRunner();
        ArrayList<ILogicElementFrontEnd> s = new ArrayList<>();
        source.forEach(s::add);
        Collections.reverse(s);
        s.forEach(item -> item.createLogicElement(runner.getInnerCircuit()));
        s.forEach(item -> item.connectLogicElementInputs(runner.getInnerCircuit()));
        return runner;
    }
}
