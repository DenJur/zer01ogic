package app.dragdrop;

import app.components.OutputPin;
import app.components.Pin;
import app.controllers.CanvasController;
import interfaces.circuits.ICircuitElementRegister;
import interfaces.elements.ILogicElementFrontEnd;
import interfaces.elements.IObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class DraggableNode extends AnchorPane implements ILogicElementFrontEnd {
    //Controller
    private CanvasController canvasController;

    //Drag and drop
    private EventHandler<DragEvent> mContextDragOver;
    private EventHandler<DragEvent> mContextDragDropped;
    private Point2D mDragOffset = new Point2D(0.0, 0.0);

    //data fields
    private double xPosition; //X coordinate of this node relative to the canvas
    private double yPosition; //Y coordinate of this node relative to the canvas
    protected ArrayList<Pin> pins;

    public DraggableNode() {
        pins = new ArrayList<Pin>();
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        buildNodeDragHandlers();
    }

    protected abstract void createPins(double lineWidth);

    /**
     * Called by the CanvasController after the DraggableNode has been added to the canvas
     * this will pass an instance of the CanvasController to each Pin object, so that it can
     * send requests for new wires to the CanvasController
     */
    public void addDraggableNodeToPins(){
        for (Pin pin:pins) {
            pin.connectDraggableNode(this);
        }
    }

    /**
     * Whenever the draggable node is moved, visually update any wires connected to its pins
     */
    public void redrawWires(){
        //Call the redraw method of every pin connected to this node
        //Passing the new X and Y coordinates of this node
        for (Pin pin:pins){
            pin.redrawWires(xPosition, yPosition);
        }
    }


    //DRAG AND DROP-----------------------------------------------------------------------------


    public void relocateToPoint(Point2D p) {

        //relocates the object to a point that has been converted to
        //scene coordinates
        Point2D localCoords = getParent().sceneToLocal(p);

        relocate(
                (int) (localCoords.getX() - mDragOffset.getX()),
                (int) (localCoords.getY() - mDragOffset.getY())
        );
    }

    public void buildNodeDragHandlers() {

        mContextDragOver = new EventHandler<DragEvent>() {

            //dragover to handle node dragging in the right pane view
            @Override
            public void handle(DragEvent event) {

                event.acceptTransferModes(TransferMode.ANY);
                relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));

                event.consume();
            }
        };

        //dragdrop for node dragging
        mContextDragDropped = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                //Update the X and Y coordinates of this node
                Point2D p = new Point2D(event.getSceneX(), event.getSceneY());
                Point2D localCoords = getParent().sceneToLocal(p);
                xPosition= (int) (localCoords.getX() - mDragOffset.getX());
                yPosition = (int) (localCoords.getY() - mDragOffset.getY());

                //Update the wires for any connected pins
                redrawWires();

                event.setDropCompleted(true);

                event.consume();
            }
        };

        //drag detection for node dragging
        this.setOnDragDetected(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                getParent().setOnDragDropped(null);

                getParent().setOnDragOver(mContextDragOver);
                getParent().setOnDragDropped(mContextDragDropped);

                //begin drag ops
                mDragOffset = new Point2D(event.getX(), event.getY());

                relocateToPoint(
                        new Point2D(event.getSceneX(), event.getSceneY())
                );

                ClipboardContent content = new ClipboardContent();
                DragContainer container = new DragContainer();

                content.put(DragContainer.DraggableNode, container);

                startDragAndDrop(TransferMode.ANY).setContent(content);

                event.consume();
            }

        });
    }

    //GETTERS AND SETTERS-----------------------------------------------------------------------------

    public CanvasController getCanvasController(){
        return canvasController;
    };

    public void setCanvasController(CanvasController canvasController){
        this.canvasController = canvasController;
    }

    public abstract IObservableValue getObservableValueForPin(OutputPin outputPin, ICircuitElementRegister register);
}
