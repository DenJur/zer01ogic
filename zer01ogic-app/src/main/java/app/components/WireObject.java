package app.components;

import javafx.scene.shape.Line;

import java.util.Arrays;
import java.util.Collection;

public class WireObject extends Line{

    public WireObject(){
        this.getStyleClass().add("Wire");
        this.recolor("build");
    }

    public void recolor(String newStatus){
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
