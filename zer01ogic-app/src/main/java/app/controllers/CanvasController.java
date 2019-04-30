package app.controllers;

import app.components.InputPin;
import app.components.OutputPin;
import app.components.Pin;
import app.components.WireObject;
import app.dragdrop.DragContainer;
import app.dragdrop.DraggableNode;
import app.enums.DrawStyle;
import app.interfaces.InputNode;
import app.interfaces.StatefulNode;
import app.models.WireLogic;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CanvasController implements Initializable {

    @FXML
    private AnchorPane anchorpane_canvas;

    private ArrayList<WireLogic> wires;
    private ArrayList<DraggableNode> nodes;
    private ArrayList<StatefulNode> statefulNodes;

    private String activeTool;
    private boolean buildModeEnabled;
    private final Timeline timeline;


    public CanvasController(MainSceneController mainSceneController) {
        wires = new ArrayList<>();
        nodes = new ArrayList<>();
        statefulNodes = new ArrayList<>();

        //The pointer is set as the active tool in build mode when starting the program
        setActiveTool("pointer");

        //the timeline will update the canvas once per 60 milliseconds to reflect any changes in node states
        timeline = new Timeline(new KeyFrame(Duration.millis(1000 / 60), event -> {
            //Update wires
            WireObject wireObject;
            for(WireLogic wireLogic : wires){
                wireObject = wireLogic.getWireObject();
                wireObject.updateStyle();
            }

            //Update StatefulNodes
            for(StatefulNode statefulNode : statefulNodes){
                statefulNode.updateStyle();
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


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
        //add the appropriate event handlers to the DraggableNode and its Pins depending on the active tool
        newNode.buildHandlersByActiveTool();

        //Add the new node to the Canvas
        anchorpane_canvas.getChildren().add(newNode);
        nodes.add(newNode);
        if(newNode instanceof StatefulNode){
            statefulNodes.add((StatefulNode) newNode);
        }

        newNode.relocateToPoint(
                new Point2D(cursorPoint.getX() - newNode.getWidth() / 2, cursorPoint.getY() - newNode.getHeight() / 2)
        );
    }

    public boolean tryCreateWire(Pin thisPin, WireLogic wireLogic) {
        /*
        thisPin is the source pin, DragContainer.getSource().getClass() is the destination pin

        Do not allow connections in the following cases:
        if the linked pin is of the same type (input/output)
        if the linked pin is part of the same DraggableNode
        if the input pin already has a connection
        */
        if (!(thisPin.getClass() == (DragContainer.getSource().getClass())
                || thisPin.getParent() == DragContainer.getSource().getParent())) {

            OutputPin output;
            InputPin input;

            //We want the source of the WireObject to always be the OutputPin, and the destination always be the InputPin
            //If the pin the WireObject was drawn from is an input pin, draw the WireObject in reverse, and connect the WireLogic in reverse
            if (thisPin instanceof OutputPin) {
                output = (OutputPin) thisPin;
                input = (InputPin) DragContainer.getSource();
            } else {
                output = (OutputPin) DragContainer.getSource();
                input = (InputPin) thisPin;
            }

            //Only create a connection if the input has no wire connected to it already
            WireLogic inputConnection = input.getConnectedWire();
            if (inputConnection == null) {
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
                wireLogic.getWireObject().draw(sourceX, sourceY, destX, destY);
                anchorpane_canvas.getChildren().add(wireLogic.getWireObject());
                wires.add(wireLogic);

                //connect the WireLogic
                wireLogic.createConnection(input, output);
                return true;
            }
        }
        return false;
    }

    public void setToBuildMode(){
        //Update wires
        WireObject wireObject;
        for(WireLogic wireLogic : wires){
            wireObject = wireLogic.getWireObject();
            wireObject.setWireStyle(DrawStyle.Build);
        }

        //Update nodes
        for(StatefulNode statefulNode : statefulNodes){
            statefulNode.setNodeStyle(DrawStyle.Build);
        }
    }

    public void setToSimulationMode(){
        //Set the style of input nodes to red (default to off)
        for(StatefulNode statefulNode : statefulNodes){
            if(statefulNode instanceof InputNode){
                statefulNode.setNodeStyle(DrawStyle.Off);
            }
        }
    }

    public void setActiveTool(String tool){
        //First destroy active drag handlers
        destroyHandlers();

        //Call the appropriate method to set up the new tool's handlers
        switch (tool) {
            case "selection":
                //TODO SET UP SELECTION METHOD
                break;
            case "wire":
                createWireToolHandlers();
                break;
            case "eraser":
                //TODO SET UP ERASER METHOD
                break;
            case "hand":
                //TODO SET UP HAND METHOD
                break;
            case "pointer":
                createPointerToolHandlers();
                break;
        }

    }

    //----------------------------------HANDLER CREATION/ DESTRUCTION---------------------------------------------------

    /**
     * Destroys the drag handlers for all active DraggableNodes and their Pins
     */
    private void destroyHandlers(){
        for(DraggableNode node : nodes){
            //destroy node dragging handlers from the DraggableNode
            node.destroyNodeDragHandlers();

            //destroy wire creation handlers from each Pin
            node.destroyPinDragHandlers();
        }
    }

    private void createWireToolHandlers() {
        activeTool = "wire";

        for(DraggableNode node : nodes){
            //build wire creation handlers for each Pin
            node.buildPinDragHandlers();
        }
    }

    private void createPointerToolHandlers() {
        activeTool = "pointer";

        for(DraggableNode node : nodes){
            //build drag handlers for each DraggableNode
            node.buildCanvasDragHandlers();
        }

    }

    //---------------------------------------GETTERS AND SETTERS--------------------------------------------------------

    public Iterable<DraggableNode> getNodes() {
        return nodes;
    }

    public String getActiveTool() {
        return activeTool;
    }
}
