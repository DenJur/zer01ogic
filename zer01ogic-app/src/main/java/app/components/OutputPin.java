package app.components;

import app.models.WireLogic;

import java.util.ArrayList;

public class OutputPin extends Pin {
    private ArrayList<WireObject> connectedWiresObject;
    private ArrayList<WireLogic> connectedWiresLogic;

    /**
     * @param xPosition horizontal position of the pin relative to the container class
     * @param yPosition vertical position of the pin relative to the container class
     */
    public OutputPin(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        this.getStyleClass().add("OutputPin");

        connectedWiresObject = new ArrayList<WireObject>();
        connectedWiresLogic = new ArrayList<WireLogic>();
    }

    @Override
    public void connectWire(WireObject wireObject, WireLogic wireLogic) {
        connectedWiresObject.add(wireObject);
        connectedWiresLogic.add(wireLogic);
    }

    @Override
    public void redrawWires(double xPosition, double yPosition) {
        //If there are any wires connected to this pin
        if(!connectedWiresObject.isEmpty()) {
            //create new coordinates for the nodes new position + the coordinates of the pin within the node
            double NewXPosition = xPosition + super.xPosition + this.getWidth() / 2;
            double NewYPosition = yPosition + super.yPosition + this.getHeight() / 2;

            //As this is an OutputPin, only the wire's start point changes when the node is moved
            //So redraw the wire, changing only its start point
            for (WireObject wireObject : connectedWiresObject) {
                wireObject.redrawStartPoint(NewXPosition, NewYPosition);
            }
        }
    }

    @Override
    public WireObject[] getWiresObject() {
        return (WireObject[]) connectedWiresObject.toArray();
    }

    @Override
    public WireLogic[] getWiresLogic() {
        return (WireLogic[]) connectedWiresLogic.toArray();
    }
}
