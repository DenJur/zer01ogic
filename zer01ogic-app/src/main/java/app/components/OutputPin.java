package app.components;

import app.models.WireLogic;

import java.util.ArrayList;

public class OutputPin extends Pin {
    private ArrayList<WireLogic> connectedWires;

    /**
     * @param xPosition horizontal position of the pin relative to the container class
     * @param yPosition vertical position of the pin relative to the container class
     */
    public OutputPin(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        this.getStyleClass().add("OutputPin");

        connectedWires = new ArrayList<WireLogic>();
    }

    @Override
    public void connectWire(WireLogic wire) {
        connectedWires.add(wire);
    }

    @Override
    public WireLogic[] getWires() {
        return (WireLogic[]) connectedWires.toArray();
    }
}
