package app.models;

import javafx.scene.Group;


public class ToolboxItem {

    private String name;
    private Group icon;
    private int numberOfInputs;
    private int numberOfOutputs;



    public ToolboxItem(String name, Group icon, int numberOfInputs, int numberOfOutputs) {
        this.name = name;
        this.icon = icon;
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getIcon() {
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
}
