package app.dragdrop;

import app.components.Pin;
import app.controllers.CanvasController;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class DraggableNode extends AnchorPane {
    //Controller
    private CanvasController canvasController;

    //Drag and drop
    private EventHandler<DragEvent> mContextDragOver;
    private EventHandler<DragEvent> mContextDragDropped;
    private Point2D mDragOffset = new Point2D(0.0, 0.0);

    //data fields
    protected ArrayList<Pin> pins;

    public DraggableNode() {
        pins = new ArrayList<Pin>();
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        buildNodeDragHandlers();
    }

    public void setCanvasController(CanvasController canvasController){
        this.canvasController = canvasController;
    }

    public CanvasController getCanvasController(){
        return canvasController;
    };

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
    public void updateWires(){
        for (Pin pin:pins){
            //TODO ADD THE REST OF THIS---------------------------------------------------------------------------------------------------------------------
            pin.updateWires();
        }
    }

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
}
