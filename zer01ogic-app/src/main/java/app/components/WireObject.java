package app.components;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class WireObject extends Line {
    public static final String WIRE_STYLE = "Wire";
    public static final String WIRE_BUILD_STYLE = "WireBuild";
    public static final String WIRE_ON_STYLE = "WireOn";
    public static final String WIRE_OFF_STYLE = "WireOff";
    private final Timeline timeline;
    private volatile WireStyle currentStyle;

    public WireObject() {
        //Set up the wire's CSS in build mode
        this.recolor(WireStyle.Build);
        timeline = new Timeline(new KeyFrame(Duration.millis(1000 / 60), event -> {
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
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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

    public void recolor(WireStyle newStyle) {
        currentStyle = newStyle;
    }

    public enum WireStyle {
        Build,
        On,
        Off
    }
}
