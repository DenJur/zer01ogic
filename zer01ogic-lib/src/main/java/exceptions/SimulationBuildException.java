package exceptions;

/**
 * Exception that is thrown if not enough information was provided to circuit builder to build a functional circuit
 */
public class SimulationBuildException extends Exception {
    public SimulationBuildException(String message) {
        super(message);
    }
}
