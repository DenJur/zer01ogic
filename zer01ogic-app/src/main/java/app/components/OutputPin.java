package app.components;

import javafx.event.EventHandler;

import java.awt.event.MouseEvent;

/**
 * Created by u1460287 on 05/03/2019.
 */
public class OutputPin extends Pin {
    /**
     * @param xPosition horizontal position of the pin relative to the container class
     * @param yPosition vertical position of the pin relative to the container class
     */
    public OutputPin(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        this.getStyleClass().add("OutputPin");
    }
}
