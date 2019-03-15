package app.components;

import app.models.WireLogic;

public class InputPin extends Pin {
    private WireObject connectedWireObject;
    private WireLogic connectedWireLogic;

    /**
     * @param xPosition horizontal position of the pin relative to the container class
     * @param yPosition vertical position of the pin relative to the container class
     */
    public InputPin(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        this.getStyleClass().add("InputPin");
    }

    @Override
    public void connectWire(WireObject wireObject, WireLogic wireLogic) {
        this.connectedWireObject = wireObject;
        this.connectedWireLogic = wireLogic;
    }

    @Override
    public void redrawWires(double xPosition, double yPosition) {
        //If there is a wire connected to this pin
        if(connectedWireLogic != null){
            //create new coordinates for the nodes new position + the coordinates of the pin within the node
            double newXPosition = xPosition + super.xPosition + this.getWidth() / 2;
            double newYPosition = yPosition + super.yPosition + this.getHeight() / 2;


            System.out.println("New endpoint drawn with coordinates " + newXPosition + ", " + newYPosition);

            //As this is an InputPin, only the wire's end point changes when the node is moved
            //So redraw the wire, changing only its end point
            connectedWireObject.redrawEndPoint(newXPosition, newYPosition);
        }
    }

    @Override
    public WireObject[] getWiresObject() {
        WireObject[] wireObject = new WireObject[1];
        wireObject[0] = connectedWireObject;
        return wireObject;
    }

    @Override
    public WireLogic[] getWiresLogic() {
        WireLogic[] wireLogic = new WireLogic[1];
        wireLogic[0] = connectedWireLogic;
        return wireLogic;
    }
}
