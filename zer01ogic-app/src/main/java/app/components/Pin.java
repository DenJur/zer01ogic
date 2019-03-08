package app.components;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Created by u1460287 on 05/03/2019.
 */
public class Pin extends Rectangle {
    private final int height = 10;
    private final int width = 10;


    /**
     * @param xPosition horizontal position of the pin relative to the container class
     * @param yPosition vertical position of the pin relative to the container class
     */
    public Pin(double xPosition, double yPosition){
        this.setHeight(height);
        this.setWidth(width);
        this.setTranslateX(xPosition);
        this.setTranslateY(yPosition);
    }

    protected void setFillColor(String color){
        //this.setStyle("-fx-fill: magenta");
        this.setStyle("-fx-fill: " + color);
    }
}
