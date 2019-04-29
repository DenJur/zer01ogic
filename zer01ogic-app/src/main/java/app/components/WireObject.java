package app.components;

import javafx.scene.shape.Line;

public class WireObject extends Line {
    public static final String WIRE_STYLE = "Wire";
    public static final String WIRE_BUILD_STYLE = "WireBuild";
    public static final String WIRE_ON_STYLE = "WireOn";
    public static final String WIRE_OFF_STYLE = "WireOff";
    private volatile WireStyle currentStyle;

    public WireObject() {
        //Set up the wire's CSS in build mode
        this.setWireStyle(WireStyle.Build);
    }

    /**
     * Takes 4 coordinates and redraws the wire when one if its connected nodes are moved
     */
    public void draw(double sourceX, double sourceY, double destX, double destY) {
        this.setStartX(sourceX);
        this.setStartY(sourceY);
        this.setEndX(destX);
        this.setEndY(destY);
    }

    /**
     * Takes two coordinates (the new start point of the wire) and redraws the wire
     */
    public void redrawStartPoint(double xPosition, double yPosition) {
        this.setStartX(xPosition);
        this.setStartY(yPosition);
    }

    /**
     * Takes two coordinates (the new end point of the wire) and redraws the wire
     */
    public void redrawEndPoint(double xPosition, double yPosition) {
        this.setEndX(xPosition);
        this.setEndY(yPosition);
    }

    public void updateColor() {
        this.getStyleClass().clear();
        switch(currentStyle){
            case Build:
                this.getStyleClass().addAll(WireObject.WIRE_STYLE, WireObject.WIRE_BUILD_STYLE);
                break;
            case On:
                this.getStyleClass().addAll(WireObject.WIRE_STYLE, WireObject.WIRE_ON_STYLE);
                break;
            case Off:
                this.getStyleClass().addAll(WireObject.WIRE_STYLE, WireObject.WIRE_OFF_STYLE);
                break;
        }
    }

    public enum WireStyle {
        Build,
        On,
        Off
    }

    public void setWireStyle(WireStyle newStyle){
        currentStyle = newStyle;
    }
}
