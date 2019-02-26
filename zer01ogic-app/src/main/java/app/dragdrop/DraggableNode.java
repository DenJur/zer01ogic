package app.dragdrop;

import java.io.IOException;
import java.io.InputStream;

import afester.javafx.svg.SvgLoader;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

public class DraggableNode extends AnchorPane {

//		@FXML AnchorPane root_pane;

    private EventHandler <DragEvent> mContextDragOver;
    private EventHandler <DragEvent> mContextDragDropped;

    private Point2D mDragOffset = new Point2D (0.0, 0.0);

    @FXML private AnchorPane box;

//		@FXML private Label title_bar;
//		@FXML private Label close_button;

    private final DraggableNode self;

    public DraggableNode() {

        self = this;

        this.getChildren().add(SVGLoader("Basic_Gates/AND.svg"));



//			FXMLLoader fxmlLoader = new FXMLLoader(
//					getClass().getResource("graphics/Toolbox_Icons/Basic_Gates/AND.svg")
//					);
//
//			fxmlLoader.setRoot(this);
//			fxmlLoader.setController(this);
//			try {
//				fxmlLoader.load();
//
//			} catch (IOException exception) {
//			    throw new RuntimeException(exception);
//			}
    }

    @FXML
    private void initialize() {
        box.scaleXProperty().set(1);
        box.scaleYProperty().set(2);
        buildNodeDragHandlers();
    }

    public void relocateToPoint (Point2D p) {

        //relocates the object to a point that has been converted to
        //scene coordinates
        Point2D localCoords = getParent().sceneToLocal(p);

        relocate (
                (int) (localCoords.getX() - mDragOffset.getX()),
                (int) (localCoords.getY() - mDragOffset.getY())
        );
    }




    /*
    public DragIconType getType () { return mType; }

    public void setType (DragIconType type) {

        mType = type;

        getStyleClass().clear();
        getStyleClass().add("dragicon");

        switch (mType) {

        case blue:
            getStyleClass().add("icon-blue");
        break;

        case red:
            getStyleClass().add("icon-red");
        break;

        case green:
            getStyleClass().add("icon-green");
        break;

        case grey:
            getStyleClass().add("icon-grey");
        break;

        case purple:
            getStyleClass().add("icon-purple");
        break;

        case yellow:
            getStyleClass().add("icon-yellow");
        break;

        case black:
            getStyleClass().add("icon-black");
        break;

        default:
        break;
        }
    }
    */
    public void buildNodeDragHandlers() {

        mContextDragOver = new EventHandler <DragEvent>() {

            //dragover to handle node dragging in the right pane view
            @Override
            public void handle(DragEvent event) {

                event.acceptTransferModes(TransferMode.ANY);
                relocateToPoint(new Point2D( event.getSceneX(), event.getSceneY()));

                event.consume();
            }
        };

        //dragdrop for node dragging
        mContextDragDropped = new EventHandler <DragEvent> () {

            @Override
            public void handle(DragEvent event) {

                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                event.setDropCompleted(true);

                event.consume();
            }
        };
        //close button click
//			close_button.setOnMouseClicked( new EventHandler <MouseEvent> () {
//
//				@Override
//				public void handle(MouseEvent event) {
//					AnchorPane parent  = (AnchorPane) self.getParent();
//					parent.getChildren().remove(self);
//				}
//
//			});

        //drag detection for node dragging
        box.setOnDragDetected ( new EventHandler <MouseEvent> () {

            @Override
            public void handle(MouseEvent event) {

                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                getParent().setOnDragOver (mContextDragOver);
                getParent().setOnDragDropped (mContextDragDropped);

                //begin drag ops
                mDragOffset = new Point2D(event.getX(), event.getY());

                relocateToPoint(
                        new Point2D(event.getSceneX(), event.getSceneY())
                );

                ClipboardContent content = new ClipboardContent();
                DragContainer container = new DragContainer();

                content.put(DragContainer.AddNode, container);

                startDragAndDrop (TransferMode.ANY).setContent(content);

//	                box.scaleXProperty().set(box.scaleXProperty().get()-0.1);
//	                box.scaleYProperty().set(box.scaleYProperty().get()-0.1);
                box.rotateProperty().set(box.rotateProperty().getValue()+17);

                event.consume();
            }

        });
    }

    private Group SVGLoader(String path) {
        SvgLoader loader = new SvgLoader();
        InputStream svgFile = getClass().getResourceAsStream("/graphics/Toolbox_Icons/" + path);
        Group svgImage = loader.loadSvg(svgFile);

        return svgImage;
    }
}
