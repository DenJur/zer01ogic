package app.controllers;

import app.interfaces.MenuBarSwitchLambda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

import javax.tools.Tool;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarBuildController implements Initializable {
    private MenuBarSwitchLambda switchLambda;

    private CanvasController canvasController;

    @FXML
    private Button button_menu_toolbar_selection;
    @FXML
    private Button button_menu_toolbar_wire;
    @FXML
    private Button button_menu_toolbar_eraser;
    @FXML
    private Button button_menu_toolbar_hand;
    @FXML
    private Button button_menu_toolbar_pointer;

    @FXML
    private Button button_menu_mode_simulation;

    @FXML
    private Button button_menu_mode_build;


    public MenuBarBuildController(MenuBarSwitchLambda switchLambda, CanvasController canvasController){
        this.switchLambda=switchLambda;
        this.canvasController = canvasController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set
        //button_menu_toolbar_pointer;

        //Initialise handlers for the toolbar buttons
        createToolbarHandlers();
        addTooltips();
        //Initialise the handler for the simulation mode button
        button_menu_mode_simulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switchLambda.Switch();
            }
        });
    }

    /**
     * Sets up action handlers for the tools on the tool bar
     */
    private void createToolbarHandlers() {
        //Selection tool
        button_menu_toolbar_selection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canvasController.setActiveTool("selection");
            }
        });

        //Wire tool
        button_menu_toolbar_wire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canvasController.setActiveTool("wire");
            }
        });

        //Eraser tool
        button_menu_toolbar_eraser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canvasController.setActiveTool("eraser");
            }
        });

        //hand tool
        button_menu_toolbar_hand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canvasController.setActiveTool("hand");
            }
        });

        //pointer tool
        button_menu_toolbar_pointer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canvasController.setActiveTool("pointer");
            }
        });
    }

    private void addTooltips() {
        Tooltip.install(button_menu_toolbar_selection, new Tooltip("Multiple Selection Tool"));
        Tooltip.install(button_menu_toolbar_wire, new Tooltip("Wire Tool"));
        Tooltip.install(button_menu_toolbar_eraser, new Tooltip("Eraser Tool"));
        Tooltip.install(button_menu_toolbar_hand, new Tooltip("Canvas Scroll Tool"));
        Tooltip.install(button_menu_toolbar_pointer, new Tooltip("Individual Selection Tool"));
        Tooltip.install(button_menu_mode_simulation, new Tooltip("Simulation Mode"));
        Tooltip.install(button_menu_mode_build, new Tooltip("Build Mode"));
    }
}
