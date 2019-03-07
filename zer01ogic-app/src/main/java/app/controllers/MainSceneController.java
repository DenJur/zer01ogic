package app.controllers;

import app.dragdrop.DragContainer;
import app.dragdrop.DragIcon;
import app.dragdrop.DraggableNode;
import app.models.ToolboxItem;
import javafx.event.EventHandler;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    //GUI Components
    public SplitPane splitpane_main;
    public AnchorPane anchorpane_main;
    public GridPane gridpane_toolboxProperties;
    public GridPane gridpane_menu;
    public AnchorPane anchorpane_canvas;

    private Node menuBarBuild;
    private Node menuBarSimulation;
    private Node toolbox;

    //drag and drop
    private DragIcon mDragOverIcon = null;

    private EventHandler<DragEvent> mIconDragOverMain = null;
    private EventHandler<DragEvent> mIconDragDropped = null;
    private EventHandler<DragEvent> mIconDragOverCanvas = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            //LOAD SCENES

            //load the menu bar with the build menu
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/app/view/BuildMenu.fxml"));
            menuLoader.setController(new MenuBarBuildController(this::SwitchBars));
            menuBarBuild = menuLoader.load();

            //load the menu bar with the simulation menu (so it can be switched when needed)
            menuLoader = new FXMLLoader(getClass().getResource("/app/view/SimulationMenu.fxml"));
            menuLoader.setController(new MenuBarSimulationController(this::SwitchBars));
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
            gridpane_toolboxProperties.add(toolbox, 0, 0);

            //TODO add the selected item properties ------------------------------------------------------------------------------------------------------------------------------------


            //TODO add drag and drop functionality -------------------------------------------------------------------------------------------------------------------------------------
            //Add one icon that will be used for the drag-drop process
            //This is added as a child to the root anchorpane so it can be visible
            //on both sides of the split pane.
            mDragOverIcon = new DragIcon();

            mDragOverIcon.setVisible(false);
            mDragOverIcon.setOpacity(0.65);

            anchorpane_main.getChildren().add(mDragOverIcon);

            buildDragHandlers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the user drags an item from the toolbox,
     * handles drag and drop features
     * @param toolboxItem the toolbox item that was selected
     */
    public void toolboxDragDrop(ToolboxItem toolboxItem, MouseEvent event){
        // set drag event handlers on their respective objects
        anchorpane_main.setOnDragOver(mIconDragOverMain);
        anchorpane_canvas.setOnDragOver(mIconDragOverCanvas);
        anchorpane_canvas.setOnDragDropped(mIconDragDropped);

        //set the icon of the drag icon to the current toolbox item's icon
        mDragOverIcon.setType(toolboxItem);

        //begin drag ops
        mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));

        ClipboardContent content = new ClipboardContent();
        DragContainer container = new DragContainer();

        container.addData ("type", mDragOverIcon.getType());
        content.put(DragContainer.AddNode, container);

        mDragOverIcon.startDragAndDrop (TransferMode.ANY).setContent(content);
        mDragOverIcon.setVisible(true);
        mDragOverIcon.setMouseTransparent(true);
        event.consume();
    }

    /**
     * Switch the menu bars when requested
     */
    private void SwitchBars() {
        if (menuBarBuild.isVisible()) {
            menuBarBuild.setVisible(false);
            menuBarSimulation.setVisible(true);
            toolbox.setDisable(true);
        } else {
            menuBarBuild.setVisible(true);
            menuBarSimulation.setVisible(false);
            toolbox.setDisable(false);
        }
    }

















    private void buildDragHandlers() {

        //drag over transition to move widget form left pane to right pane
        mIconDragOverMain = new EventHandler <DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                Point2D p = anchorpane_canvas.sceneToLocal(event.getSceneX(), event.getSceneY());

                //turn on transfer mode and track in the right-pane's context 
                //if (and only if) the mouse cursor falls within the right pane's bounds.
                if (!anchorpane_canvas.boundsInLocalProperty().get().contains(p)) {

                    event.acceptTransferModes(TransferMode.ANY);
                    mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                    return;
                }

                event.consume();
            }
        };

        mIconDragOverCanvas = new EventHandler <DragEvent> () {

            @Override
            public void handle(DragEvent event) {

                event.acceptTransferModes(TransferMode.ANY);

                //convert the mouse coordinates to scene coordinates,
                //then convert back to coordinates that are relative to 
                //the parent of mDragIcon.  Since mDragIcon is a child of the root
                //pane, coodinates must be in the root pane's coordinate system to work
                //properly.
                mDragOverIcon.relocateToPoint(
                        new Point2D(event.getSceneX(), event.getSceneY())
                );
                event.consume();
            }
        };

        mIconDragDropped = new EventHandler <DragEvent> () {

            @Override
            public void handle(DragEvent event) {

                DragContainer container =
                        (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

                container.addData("scene_coords",
                        new Point2D(event.getSceneX(), event.getSceneY()));

                ClipboardContent content = new ClipboardContent();
                content.put(DragContainer.AddNode, container);

                event.getDragboard().setContent(content);
                event.setDropCompleted(true);
            }
        };

        //this.setOnDragDone (new EventHandler <DragEvent> (){ -----------------------------------------------------------------------------------------------------------------------DELETE ME LATER

        anchorpane_main.setOnDragDone (new EventHandler <DragEvent> (){

            @Override
            public void handle (DragEvent event) {

                anchorpane_canvas.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverCanvas);
                anchorpane_canvas.removeEventHandler(DragEvent.DRAG_DROPPED, mIconDragDropped);
                anchorpane_main.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverMain);

                mDragOverIcon.setVisible(false);

                DragContainer container = (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

                if (container != null) {
                    if (container.getValue("scene_coords") != null) {

                        DraggableNode node = new DraggableNode();

                        //node.setType(DragIconType.valueOf(container.getValue("type")));
                        anchorpane_canvas.getChildren().add(node);

                        Point2D cursorPoint = container.getValue("scene_coords");

                        node.relocateToPoint(
                                new Point2D(cursorPoint.getX() - 32, cursorPoint.getY() - 32)
                        );
                    }
                }

                container = (DragContainer) event.getDragboard().getContent(DragContainer.DragNode);

                if (container != null) {
                    if (container.getValue("type") != null)
                        System.out.println ("Moved node " + container.getValue("type"));
                }

                event.consume();
            }
        });
    }
}
