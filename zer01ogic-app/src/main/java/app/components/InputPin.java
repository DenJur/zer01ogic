package app.components;

import app.models.WireLogic;

public class InputPin extends Pin {
    private WireLogic connectedWire;

    /**
     * @param xPosition horizontal position of the pin relative to the container class
     * @param yPosition vertical position of the pin relative to the container class
     */
    public InputPin(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        this.getStyleClass().add("InputPin");
    }

    @Override
    public void connectWire(WireLogic wire) {
        this.connectedWire = wire;
    }

    @Override
    public WireLogic[] getWires() {
        WireLogic[] wire = new WireLogic[1];
        wire[0] = connectedWire;
        return wire;
    }
}
