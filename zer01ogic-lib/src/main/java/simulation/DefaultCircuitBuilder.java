package simulation;

import exceptions.SimulationBuildException;
import interfaces.circuits.*;
import interfaces.elements.ILogicElementFrontEnd;
import simulation.circuits.ScheduledLogicBusyExecutor;
import simulation.circuits.SingleThreadCircuitBusy;
import simulation.circuits.SingleThreadCircuitRunner;
import simulation.circuits.SingleThreadCircuitWaiting;

import java.util.ArrayList;
import java.util.Collections;

public class DefaultCircuitBuilder implements ICircuitBuilder {
    private ICircuitRunner circuitRunner;
    private ICircuit circuit;
    private ICircuitElementRegister register;
    private IScheduledLogicExecutor scheduledExecutor;

    public DefaultCircuitBuilder usingSingleThreadRunner(){
        circuitRunner=new SingleThreadCircuitRunner();
        return this;
    }

    public DefaultCircuitBuilder buildBusyCircuit(){
        SingleThreadCircuitBusy circuitBusy = new SingleThreadCircuitBusy();
        circuit=circuitBusy;
        register=circuitBusy;
        return this;
    }

    public DefaultCircuitBuilder buildWaitingCircuit(){
        SingleThreadCircuitWaiting circuitWaiting = new SingleThreadCircuitWaiting();
        circuit=circuitWaiting;
        register=circuitWaiting;
        return this;
    }

    public DefaultCircuitBuilder addBusyScheduledExecutor(){
        scheduledExecutor=new ScheduledLogicBusyExecutor();
        return this;
    }

    @Override
    public ICircuitRunner build(Iterable<? extends ILogicElementFrontEnd> source) throws SimulationBuildException {
        if(circuit==null) throw new SimulationBuildException("Simulation Circuit type not set");
        if(circuitRunner==null) throw new SimulationBuildException("Circuit runner type not set");

        if(scheduledExecutor!=null){
            circuit.addScheduledExecutor(scheduledExecutor);
        }

        ArrayList<ILogicElementFrontEnd> s = new ArrayList<>();
        source.forEach(s::add);
        Collections.reverse(s);
        s.forEach(item -> item.createLogicElement(register));
        s.forEach(item -> item.connectLogicElementInputs(register));

        circuit.setUpRegister(register);
        circuitRunner.assignInnerCircuit(circuit);
        return circuitRunner;
    }
}
