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

    public void createWire(Pin box){
        /*
        Do not allow connections in the following cases:
        if the linked pin is of the same type (input/output)
        if the linked pin is part of the same DraggableNode
        */
        if(!(box.getClass() == (DragContainer.getSource().getClass())
          || box.getParent() == DragContainer.getSource().getParent())){

            //Get the bounds & coordinates to allow a line to be drawn from this pin
            Bounds boundsOnCanvas = box.getParent().getBoundsInParent();
            Bounds boundsOnNode = box.getBoundsInParent();

            double sourceX = boundsOnCanvas.getMinX() + boundsOnNode.getMinX() + boundsOnNode.getWidth() / 2;
            double sourceY = boundsOnCanvas.getMinY() + boundsOnNode.getMinY() + boundsOnNode.getHeight() / 2;

            //Get the bounds & coordinates to allow a line to be drawn to the linked pin
            boundsOnCanvas = DragContainer.getSource().getParent().getBoundsInParent();
            boundsOnNode = DragContainer.getSource().getBoundsInParent();

            double destX = boundsOnCanvas.getMinX() + boundsOnNode.getMinX() + boundsOnNode.getWidth() / 2;
            double destY = boundsOnCanvas.getMinY() + boundsOnNode.getMinY() + boundsOnNode.getHeight() / 2;

            //draw the WireObject on the Canvas
            WireObject wireObject = new WireObject(sourceX, sourceY, destX, destY);
            anchorpane_canvas.getChildren().add(wireObject);

            //create a WireLogic object for this wire
            WireLogic wireLogic;
            InputPin input;
            OutputPin output;

            //If the source pin is an OutputPin
            //TODO SORT THIS CLASS COMPARISON, IT MAY BE BROKEN-----------------------------------------------------------------------------------
            if(box instanceof OutputPin){
                input = (InputPin) DragContainer.getSource();
                output = (OutputPin)  box;
                wireLogic = new WireLogic(input, output);
            }
            //the source pin is an InputPin
            else{
                input = (InputPin) box;
                output = (OutputPin) DragContainer.getSource();
                wireLogic = new WireLogic(input, output);
            }

        }
    }
}
