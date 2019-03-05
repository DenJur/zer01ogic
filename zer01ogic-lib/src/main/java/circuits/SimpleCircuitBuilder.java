package circuits;

import interfaces.ICircuit;
import interfaces.ICircuitBuilder;
import interfaces.ICircuitRunner;
import interfaces.ILogicElementFrontEnd;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleCircuitBuilder implements ICircuitBuilder {
    private ICircuitRunner circuitRunner;
    private ICircuit circuit;

    public SimpleCircuitBuilder(){
        circuitRunner=new CircuitRunner();
    }

    public SimpleCircuitBuilder buildBusyCircuit(){
        circuitRunner.assignInnerCircuit(new CircuitBusy());
        circuit=circuitRunner.getInnerCircuit();
        return this;
    }

    public SimpleCircuitBuilder buildWaitingCircuit(){
        circuitRunner.assignInnerCircuit(new CircuitWaiting());
        circuit=circuitRunner.getInnerCircuit();
        return this;
    }

    @Override
    public ICircuitRunner build(Iterable<ILogicElementFrontEnd> source) {
        if(circuit==null) return null;

        ArrayList<ILogicElementFrontEnd> s = new ArrayList<>();
        source.forEach(s::add);
        Collections.reverse(s);
        s.forEach(item -> item.createLogicElement(circuit));
        s.forEach(item -> item.connectLogicElementInputs(circuit));
        return circuitRunner;
    }
}
