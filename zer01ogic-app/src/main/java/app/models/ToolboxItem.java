package app.models;

import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class ToolboxItem{

    private String name;
    private Node icon;
    private int numberOfInputs;
    private int numberOfOutputs;
    private IDraggableFactory factory;

    public ToolboxItem(String name, Node icon, int numberOfInputs, int numberOfOutputs, IDraggableFactory factory) {
        this.name = name;
        this.icon = icon;
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.factory=factory;
        rescaleIcon();
    }

    private void rescaleIcon(){
        VBox box = new VBox(icon);
        Group group = new Group(box);
        //need to create a scene so that node sizes get calculated
        Scene scene = new Scene(group);
        group.applyCss();
        group.layout();
        double scaleX = 40.0/box.getWidth();
        double scaleY = 40.0/box.getHeight();
        //Set the scale to be the smallest dimension, and apply the scale to x and y
        double scale = scaleX < scaleY ? scaleX:scaleY;
        icon.setScaleX(scale);
        icon.setScaleY(scale);
        icon=new Group(icon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getIcon() {
        return icon;
    }

    public void setIcon(Group icon) {
        this.icon = icon;
    }

    public int getNumberOfInputs() {
        return numberOfInputs;
    }

    public void setNumberOfInputs(int numberOfInputs) {
        this.numberOfInputs = numberOfInputs;
    }

    public int getNumberOfOutputs() {
        return numberOfOutputs;
    }

    public void setNumberOfOutputs(int numberOfOutputs) {
        this.numberOfOutputs = numberOfOutputs;
    }

    public DraggableNode createNewNode(){
        return factory.constructDraggable();
    }

    //TODO CREATE THE DRAGGABLE ITEM FROM HERE------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
