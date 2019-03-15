package app.components;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import java.util.Arrays;
import java.util.Collection;

public class WireObject extends Line{

    public WireObject() {
        //Set up the wire's CSS in build mode
        this.recolor("build");
    }

    /**
     * Takes 4 coordinates and redraws the wire when one if its connected nodes are moved
     */
    public void draw (double sourceX, double sourceY, double destX, double destY){
        this.setStartX(sourceX);
        this.setStartY(sourceY);
        this.setEndX(destX);
        this.setEndY(destY);
    }

    /**
     * Takes two coordinates (the new start point of the wire) and redraws the wire
     */
    public void redrawStartPoint(double xPosition, double yPosition){
        System.out.println("WireObject.redrawStartPoint was called");

        this.setStartX(xPosition);
        this.setStartY(yPosition);
    }

    /**
     * Takes two coordinates (the new end point of the wire) and redraws the wire
     */
    public void redrawEndPoint(double xPosition, double yPosition){
        System.out.println("WireObject.redrawEndPoint was called with coordinates: " + xPosition + ", " + yPosition);
        
        this.setEndX(xPosition);
        this.setEndY(yPosition);
    }

    public void recolor(String newStatus){
        //Clear all CSS, and re-add the base wire styling
        this.getStyleClass().removeAll();
        this.getStyleClass().add("Wire");

        //format the wire color based on the wire's status
        if(newStatus.equals("build")){
            this.getStyleClass().add("WireBuild");
        }
        else if(newStatus.equals("off")){
            this.getStyleClass().add("WireOff");
        }
        else{ //status must equal "on"
            this.getStyleClass().add("WireOn");
        }
    }
}
