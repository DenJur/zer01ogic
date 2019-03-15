package app.controllers;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.Pin;
import app.components.WireObject;
import app.dragdrop.DragContainer;
import app.dragdrop.DraggableNode;
import app.models.WireLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import jdk.internal.util.xml.impl.Input;

import java.net.URL;
import java.util.ResourceBundle;

public class CanvasController implements Initializable{

    @FXML private AnchorPane anchorpane_canvas;

    public CanvasController(MainSceneController mainSceneController){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addDraggableNode(DraggableNode newNode, Point2D cursorPoint) {
        //When dragging, the node opacity is set to 0.65, and the cursor is made invisible.
        //We want to undo these changes when the node is dropped on to the canvas.
        newNode.setOpacity(1.0);
        newNode.setMouseTransparent(false);

        //pass the canvas controller to the DraggableNode
        newNode.setCanvasController(this);
        //update the node's pins so they have access to their parent DraggableNode and the CanvasController
        newNode.addDraggableNodeToPins();

        //Add the new node to the Canvas
        anchorpane_canvas.getChildren().add(newNode);

        newNode.relocateToPoint(
                new Point2D(cursorPoint.getX() - newNode.getWidth() / 2, cursorPoint.getY() - newNode.getHeight() / 2)
        );
    }

    public void createWire(Pin thisPin, WireObject wireObject, WireLogic wireLogic){
        /*
        thisPin is the source pin, DragContainer.getSource().getClass() is the destination pin

        Do not allow connections in the following cases:
        if the linked pin is of the same type (input/output)
        if the linked pin is part of the same DraggableNode
        if the input pin already has a connection
        */
        if(!(thisPin.getClass() == (DragContainer.getSource().getClass())
          || thisPin.getParent() == DragContainer.getSource().getParent())){

            OutputPin output;
            InputPin input;

            //We want the source of the WireObject to always be the OutputPin, and the destination always be the InputPin
            //If the pin the WireObject was drawn from is an input pin, draw the WireObject in reverse, and connect the WireLogic in reverse
            if(thisPin instanceof OutputPin){
                output = (OutputPin) thisPin;
                input = (InputPin) DragContainer.getSource();
            }
            else{
                output = (OutputPin) DragContainer.getSource();
                input = (InputPin) thisPin;
            }

            //Only create a connection if the input has no wire connected to it already
            WireLogic[] inputConnection = input.getWiresLogic();
            if(inputConnection[0] == null){
                //the bounds of the Node on the Canvas, and the bounds of the Pin on the Node
                //these allow a line to be drawn from this pin
                Bounds boundsOnCanvas = output.getParent().getBoundsInParent();
                Bounds boundsOnNode = output.getBoundsInParent();

                //the start coordinates of the WireObject to be drawn
                double sourceX = boundsOnCanvas.getMinX() + boundsOnNode.getMinX() + boundsOnNode.getWidth() / 2;
                double sourceY = boundsOnCanvas.getMinY() + boundsOnNode.getMinY() + boundsOnNode.getHeight() / 2;

                //bounds of the input pin to connect it to
                boundsOnCanvas = input.getParent().getBoundsInParent();
                boundsOnNode = input.getBoundsInParent();

                //the end coordinates of the WireObject to be drawn
                double destX = boundsOnCanvas.getMinX() + boundsOnNode.getMinX() + boundsOnNode.getWidth() / 2;
                double destY = boundsOnCanvas.getMinY() + boundsOnNode.getMinY() + boundsOnNode.getHeight() / 2;

                //draw the WireObject on the Canvas
                wireObject.draw(sourceX, sourceY, destX, destY);
                anchorpane_canvas.getChildren().add(wireObject);

                //connect the WireLogic
                wireLogic.createConnection(input, output);
            }
        }
    }
}
