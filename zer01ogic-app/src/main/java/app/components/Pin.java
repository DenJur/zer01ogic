package app.components;

import app.dragdrop.DragContainer;
import app.dragdrop.DraggableNode;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public abstract class Pin extends Rectangle {

    private EventHandler <MouseEvent> mLinkHandleDragDetected;
    private EventHandler <DragEvent> mLinkHandleDragDropped;
    private EventHandler <DragEvent> mContextLinkDragOver;
    private EventHandler <DragEvent> mContextLinkDragDropped;

    /**
     * @param xPosition horizontal position of the pin relative to the container class
     * @param yPosition vertical position of the pin relative to the container class
     */
    public Pin(double xPosition, double yPosition) {
        this.setHeight(10);
        this.setWidth(10);
        this.setTranslateX(xPosition);
        this.setTranslateY(yPosition);

        buildWireDragHandlers();
    }

    public void updateWires(){

    }

    private void buildWireDragHandlers(){


        Pin box = this;
        mLinkHandleDragDropped = new EventHandler <DragEvent> () {

            @Override
            public void handle(DragEvent event) {
                //getParent().setOnDragOver(null);
                //getParent().setOnDragDropped(null);
                getParent().getParent().setOnDragOver(null);
                Bounds bounds = box.getBoundsInParent();
                Bounds screenBounds = box.localToScreen(bounds);

                int x2 = (int) screenBounds.getMinX();
                int y2 = (int) screenBounds.getMinY();

                Pin source=DragContainer.getSource();
                bounds = source.getBoundsInLocal();
                screenBounds = source.localToScreen(bounds);
                int x = (int) screenBounds.getMinX();
                int y = (int) screenBounds.getMinY();
                event.acceptTransferModes(TransferMode.ANY);




                if(box.getClass()==(DragContainer.getSource().getClass())
                 || box.getParent()==DragContainer.getSource().getParent()){
                    System.out.println("Same");


                }
                else{
                    bounds = box.getParent().getBoundsInParent();
                    Bounds bounds2 = box.getBoundsInParent();
                    double destX=bounds.getMinX()+bounds2.getMinX()+bounds2.getWidth()/2;
                    double destY=bounds.getMinY()+bounds2.getMinY()+bounds2.getHeight()/2;

                    bounds = DragContainer.getSource().getParent().getBoundsInParent();
                    bounds2 = DragContainer.getSource().getBoundsInParent();
                    double sourceX=bounds.getMinX()+bounds2.getMinX()+bounds2.getWidth()/2;
                    double sourceY=bounds.getMinY()+bounds2.getMinY()+bounds2.getHeight()/2;

                    Line l=new Line(sourceX,sourceY,destX,destY );
                    ((AnchorPane)getParent().getParent()).getChildren().add(l);
                }

//                event.setDropCompleted(true);
                System.out.println("link from "+x+" - "+y+ " to "+x2+" - "+y2);

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
                System.out.println("detected");
                Dragboard bd=startDragAndDrop(TransferMode.ANY);
                ClipboardContent content=new ClipboardContent();
                DragContainer container = new DragContainer();
                container.setSource(box);
                content.put(DragContainer.DraggableLink, container);
                bd.setContent(content);
//                startDragAndDrop(TransferMode.ANY);
                event.consume();
            }
        };

        this.setOnDragDropped(mLinkHandleDragDropped);

        this.setOnDragDetected(mLinkHandleDragDetected);
//        this.addEventHandler(MouseEvent.DRAG_DETECTED, mLinkHandleDragDetected);
//        this.setOnDragDropped(mLinkHandleDragDropped);
//        this.addEventHandler(DragEvent.DRAG_DONE, mLinkHandleDragDropped);
//        this.addEventHandler(DragEvent.DRAG_OVER, mLinkHandleDragDropped);
//        this.addEventHandler(DragEvent.DRAG_DROPPED, mLinkHandleDragDropped);
//        this.addEventHandler(DragEvent.DRAG_ENTERED, mLinkHandleDragDropped);
//        this.addEventHandler(DragEvent.ANY, mLinkHandleDragDropped);
        //this.addEventHandler(DragEvent.DRAG_DROPPED, mLinkHandleDragDropped);
//        this.addEventHandler(DragEvent.DRAG_OVER, mContextLinkDragOver);


    }


    public void onClick(Pin pin) {
        //is the pin clipboard empty?

        //if the pin clipboard is empty
        //Copy this pin to the pin clipboard
        //if the pin clipboard is not empty
        //is the pin on the clipboard of a different type to this pin?
        //if it is of a different type
        //get the X,Y coordinates of input and output pins
        //create a new wire from input pin to output pin with these coordinates
        //clear the pin clipboard
        //if it is of the same type
        //Exception, pins must be of different type to be connected
    }
}
