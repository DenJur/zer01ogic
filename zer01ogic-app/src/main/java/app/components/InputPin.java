package app.components;

/**
 * Created by u1460287 on 05/03/2019.
 */
public class InputPin extends Pin {
    /**
     * @param xPosition horizontal position of the pin relative to the container class
     * @param yPosition vertical position of the pin relative to the container class
     */
    public InputPin(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        super.setFillColor("#0000FF");
    }
}
