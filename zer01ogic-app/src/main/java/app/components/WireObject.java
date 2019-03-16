package app.components;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class WireObject extends Line {
    public static final String wireStyle = "Wire";
    public static final String wireBuildStyle = "WireBuild";
    public static final String wireOnStyle = "WireOn";
    public static final String wireOffStyle = "WireOff";
    private final Timeline timeline;
    private volatile WireStyle currentStyle;

    public WireObject() {
        //Set up the wire's CSS in build mode
        this.recolor(WireStyle.Build);
        timeline = new Timeline(new KeyFrame(Duration.millis(1000 / 60), event -> {
            this.getStyleClass().clear();
            switch(currentStyle){
                case Build:
                    this.getStyleClass().addAll(WireObject.wireStyle, WireObject.wireBuildStyle);
                    break;
                case On:
                    this.getStyleClass().addAll(WireObject.wireStyle, WireObject.wireOnStyle);
                    break;
                case Off:
                    this.getStyleClass().addAll(WireObject.wireStyle, WireObject.wireOffStyle);
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
        System.out.println("WireObject.redrawStartPoint was called");

        this.setStartX(xPosition);
        this.setStartY(yPosition);
    }

    /**
     * Takes two coordinates (the new end point of the wire) and redraws the wire
     */
    public void redrawEndPoint(double xPosition, double yPosition) {
        System.out.println("WireObject.redrawEndPoint was called with coordinates: " + xPosition + ", " + yPosition);

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
