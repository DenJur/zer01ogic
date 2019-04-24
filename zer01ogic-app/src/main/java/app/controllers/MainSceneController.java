package app.controllers;

import app.dragdrop.DragContainer;
import app.dragdrop.DraggableNode;
import app.models.ToolboxItem;
import interfaces.circuits.ICircuitRunner;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import simulation.DefaultCircuitBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    //GUI Components
    @FXML
    public SplitPane splitpane_main;
    @FXML
    public AnchorPane anchorpane_main;
    @FXML
    public GridPane gridpane_toolboxProperties;
    @FXML
    public GridPane gridpane_menu;

    private Node menuBarBuild;
    private Node menuBarSimulation;
    private Node toolbox;
    private AnchorPane canvas;
    private CanvasController canvasController;

    //drag and drop
    private DraggableNode mDragOverIcon = null;

    private EventHandler<DragEvent> mIconDragOverMain = null;
    private EventHandler<DragEvent> mIconDragDropped = null;
    private EventHandler<DragEvent> mIconDragOverCanvas = null;

    private ICircuitRunner simulationRunner;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            //LOAD SCENES

            //load the canvas
            FXMLLoader canvasLoader = new FXMLLoader(getClass().getResource("/app/view/Canvas.fxml"));
            //As the MainSceneController communicates with the canvas, it needs to be a class field
            canvasController = new CanvasController(this);
            canvasLoader.setController(canvasController);
            canvas = canvasLoader.load();

            //load the menu bar with the build menu
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/app/view/BuildMenu.fxml"));
            menuLoader.setController(new MenuBarBuildController(this::SwitchBars, canvasController));
            menuBarBuild = menuLoader.load();

            //load the menu bar with the simulation menu (so it can be switched when needed)
            menuLoader = new FXMLLoader(getClass().getResource("/app/view/SimulationMenu.fxml"));
            menuLoader.setController(new MenuBarSimulationController(this::SwitchBars, canvasController));
            menuBarSimulation = menuLoader.load();

            //load the toolbox
            FXMLLoader toolboxLoader = new FXMLLoader(getClass().getResource("/app/view/Toolbox.fxml"));
            toolboxLoader.setController(new ToolboxController(this));
            toolbox = toolboxLoader.load();
            toolbox.setCursor(Cursor.HAND); //set the cursor to a hand when selecting
            //TODO When selecting an item using drag + drop, perhaps a closed hand? ----------------------------------------------------------------------------------------------------



            //TODO load the selected item properties -----------------------------------------------------------------------------------------------------------------------------------


            //ADD THE SCENES TO THE GUI SO THEY'RE VISIBLE

            //when the main scene is first created, set the build menu bar as the visible menu bar
            gridpane_menu.add(menuBarBuild, 2, 0);
            gridpane_menu.add(menuBarSimulation, 2, 0);
            menuBarSimulation.setVisible(false);

            //add the toolbox
            gridpane_toolboxProperties.add(toolbox, 0, 0);

            //add the canvas
            splitpane_main.getItems().add(canvas);

            //TODO add the selected item properties ------------------------------------------------------------------------------------------------------------------------------------

            buildDragHandlers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the user drags an item from the toolbox,
     * handles drag and drop features
     */
    public void toolboxDragDrop(MouseEvent event) {
        ClipboardContent content = new ClipboardContent();
        DragContainer container = new DragContainer();

        content.put(DragContainer.DraggableNode, container);

        mDragOverIcon.startDragAndDrop(TransferMode.ANY).setContent(content);
        mDragOverIcon.setVisible(true);
        mDragOverIcon.setMouseTransparent(true);
        event.consume();
    }

    public void toolboxClick(ToolboxItem toolboxItem, MouseEvent event) {
        // set drag event handlers on their respective objects
        anchorpane_main.setOnDragOver(mIconDragOverMain);
        canvas.setOnDragOver(mIconDragOverCanvas);
        canvas.setOnDragDropped(mIconDragDropped);

        //set the icon of the drag icon to the current toolbox item's icon
        mDragOverIcon = toolboxItem.createNewNode();
        mDragOverIcon.setVisible(false);
        anchorpane_main.getChildren().add(mDragOverIcon);
        mDragOverIcon.setOpacity(0.65);


        //begin drag ops
        mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX() - mDragOverIcon.getWidth() / 2, event.getSceneY() - mDragOverIcon.getHeight() / 2));
        event.consume();
    }

    private void buildDragHandlers() {

        //drag over transition to move widget form left pane to right pane
        mIconDragOverMain = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                Point2D p = canvas.sceneToLocal(event.getSceneX(), event.getSceneY());

                //turn on transfer mode and track in the right-pane's context 
                //if (and only if) the mouse cursor falls within the right pane's bounds.
                if (!canvas.boundsInLocalProperty().get().contains(p)) {

                    event.acceptTransferModes(TransferMode.ANY);
                    mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX() - mDragOverIcon.getWidth() / 2, event.getSceneY() - mDragOverIcon.getHeight() / 2));
                    return;
                }

                event.consume();
            }
        };

        mIconDragOverCanvas = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                event.acceptTransferModes(TransferMode.ANY);

                //convert the mouse coordinates to scene coordinates,
                //then convert back to coordinates that are relative to 
                //the parent of mDragIcon.  Since mDragIcon is a child of the root
                //pane, coodinates must be in the root pane's coordinate system to work
                //properly.
                mDragOverIcon.relocateToPoint(
                        new Point2D(event.getSceneX() - mDragOverIcon.getWidth() / 2, event.getSceneY() - mDragOverIcon.getHeight() / 2)
                );
                event.consume();
            }
        };

        mIconDragDropped = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                DragContainer container =
                        (DragContainer) event.getDragboard().getContent(DragContainer.DraggableNode);

                container.addPoint(new Point2D(event.getSceneX(), event.getSceneY()));

                ClipboardContent content = new ClipboardContent();
                content.put(DragContainer.DraggableNode, container);

                event.getDragboard().setContent(content);
                event.setDropCompleted(true);
            }
        };

        anchorpane_main.setOnDragDone(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                canvas.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverCanvas);
                canvas.removeEventHandler(DragEvent.DRAG_DROPPED, mIconDragDropped);
                anchorpane_main.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverMain);

                anchorpane_main.getChildren().remove(mDragOverIcon);
                DragContainer container = (DragContainer) event.getDragboard().getContent(DragContainer.DraggableNode);

                if (container != null) {
                    Point2D cursorPoint = container.getPoint();
                    container.addPoint(null);
                    if (cursorPoint != null) {
                        //Send a request to the CanvasController to add the new DraggableNode to the Canvas
                        canvasController.addDraggableNode(mDragOverIcon, cursorPoint);
                    }
                }

                event.consume();
            }
        });
    }

    public void clearDraggable(MouseEvent event) {
        anchorpane_main.getChildren().remove(mDragOverIcon);
    }

    /**
     * Switch the menu bars from build to simulation mode or vice versa
     */
    private void SwitchBars() {
        if (menuBarBuild.isVisible()) {

            //remove all action handlers on gui elements, except switch on/off

            menuBarBuild.setVisible(false);
            menuBarSimulation.setVisible(true);
            toolbox.setDisable(true);
            if(simulationRunner!=null){
                try {
                    simulationRunner.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            simulationRunner = new DefaultCircuitBuilder().usingSingleThreadRunner().addBusyScheduledExecutor()
                    .buildBusyCircuit().build(canvasController.getNodes());
            simulationRunner.startSimulation();
        } else {

            //recreate the action handlers on the gui elements, except switch on/off, which will be disabled

            menuBarBuild.setVisible(true);
            menuBarSimulation.setVisible(false);
            toolbox.setDisable(false);
            if(simulationRunner!=null){
                try {
                    simulationRunner.stop();
                    simulationRunner=null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //Update the wires and gui elements to represent their new state

    }
}
