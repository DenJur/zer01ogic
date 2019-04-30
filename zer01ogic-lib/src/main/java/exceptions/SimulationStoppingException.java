package exceptions;

/**
 * Exception that is thrown if simulation did not menage to stop gracefully
 */
public class SimulationStoppingException extends Exception {
    public SimulationStoppingException(String message) {
        super(message);
    }
}
