package app.components;

import app.controllers.CanvasController;
import app.dragdrop.DragContainer;
import app.dragdrop.DraggableNode;
import app.models.WireLogic;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.shape.Rectangle;

public abstract class Pin extends Rectangle {
    //DraggableNode parent and CanvasController classes
    private DraggableNode draggableNode;
    private CanvasController canvasController;

    //Drag and drop
    private EventHandler <MouseEvent> mLinkHandleDragDetected;
    private EventHandler <DragEvent> mLinkHandleDragDropped;
    private EventHandler <DragEvent> mContextLinkDragOver;
    private EventHandler <DragEvent> mContextLinkDragDropped;

    //Coordinates relative to container node
    protected double xPosition;
    protected double yPosition;

    /**
     * @param xPosition horizontal position of the pin relative to the container node
     * @param yPosition vertical position of the pin relative to the container node
     */
    public Pin(double xPosition, double yPosition) {
        this.setHeight(10);
        this.setWidth(10);

        this.xPosition = xPosition;
        this.yPosition = yPosition;

        this.setTranslateX(xPosition);
        this.setTranslateY(yPosition);

        buildWireDragHandlers();
    }

    public void connectDraggableNode(DraggableNode draggableNode){
        this.draggableNode = draggableNode; //allowing for wire connection
        this.canvasController = draggableNode.getCanvasController(); //allows for new wires to be added to the Canvas by this pin
    }

    //WIRING--------------------------------------------------------------------------------------

    public abstract void connectWire(WireLogic wireLogic);

    public abstract void redrawWires(double xPosition, double yPosition);

    public abstract WireLogic[] getWiresLogic();


    //DRAG AND DROP-------------------------------------------------------------------------------
    private void buildWireDragHandlers(){
        //thisPin allows us to refer to this Pin object within the EventHandlers (as the 'this' operator refers to the handler)
        Pin thisPin = this;

        mLinkHandleDragDetected = new EventHandler <MouseEvent> () {

            @Override
            public void handle(MouseEvent event) {

                getParent().getParent().setOnDragOver(null);
                getParent().getParent().setOnDragDropped(null);
                getParent().getParent().setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        event.acceptTransferModes(TransferMode.ANY);
                        event.consume();
                    }
                });

                Dragboard bd = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                DragContainer container = new DragContainer();
                DragContainer.setSource(thisPin);
                content.put(DragContainer.DraggableLink, container);
                bd.setContent(content);
//                startDragAndDrop(TransferMode.ANY);
                event.consume();
            }
        };

        mLinkHandleDragDropped = new EventHandler <DragEvent> () {

            @Override
            public void handle(DragEvent event) {
                //setOnDragOver for the AnchorPane Canvas to null
                getParent().getParent().setOnDragOver(null);

                event.acceptTransferModes(TransferMode.ANY);

                //Create blank wire object/logic
                WireLogic wireLogic = new WireLogic();

                //Send a wire creation request to the CanvasController
                //this fills the data in for the wire object/logic
                canvasController.createWire(thisPin, wireLogic);

                //add the wire object/logic to this pin
                thisPin.connectWire(wireLogic);
                //add the wire object/logic to the other pin
                DragContainer.getSource().connectWire(wireLogic);

                event.setDropCompleted(true);
                event.consume();
            }
        };



//        mContextLinkDragOver = new EventHandler <DragEvent> () {
//
//            @Override
//            public void handle(DragEvent event) {
//                event.acceptTransferModes(TransferMode.ANY);
//                System.out.println("drag ovr");
//                event.consume();
//
//            }
//        };
//
//        mContextLinkDragDropped = new EventHandler <DragEvent> () {
//
//            @Override
//            public void handle(DragEvent event) {
//                System.out.println("context drop");
//
//
//                getParent().setOnDragOver(null);
//                getParent().setOnDragDropped(null);
//
//                event.setDropCompleted(true);
//                event.consume();
//            }
//
//        };


        this.setOnDragDropped(mLinkHandleDragDropped);
        this.setOnDragDetected(mLinkHandleDragDetected);
    }
}
