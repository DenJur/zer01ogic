package app.components;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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

    private void buildWireDragHandlers(){

        mLinkHandleDragDetected = new EventHandler <MouseEvent> () {

            @Override
            public void handle(MouseEvent event) {

                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                getParent().setOnDragOver(mContextLinkDragOver);
                getParent().setOnDragDropped(mLinkHandleDragDropped);

                event.consume();
            }
        };

        mLinkHandleDragDropped = new EventHandler <DragEvent> () {

            @Override
            public void handle(DragEvent event) {
                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                event.setDropCompleted(true);

                event.consume();
            }
        };

        mContextLinkDragOver = new EventHandler <DragEvent> () {

            @Override
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
                event.consume();

            }
        };

        mContextLinkDragDropped = new EventHandler <DragEvent> () {

            @Override
            public void handle(DragEvent event) {

                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                event.setDropCompleted(true);
                event.consume();
            }

        };
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
