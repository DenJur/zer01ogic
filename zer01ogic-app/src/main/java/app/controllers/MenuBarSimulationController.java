package app.controllers;

import app.interfaces.MenuBarSwitchLambda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarSimulationController implements Initializable {
    private MenuBarSwitchLambda switchLambda;

    private CanvasController canvasController;

    @FXML
    private Button button_menu_toolbar_selection;

    @FXML
    private Button button_menu_toolbar_pointer;

    @FXML
    private Button button_menu_simulation_play;

    @FXML
    private Button button_menu_simulation_stop;

    @FXML
    private Button button_menu_mode_simulation;

    @FXML
    private Button button_menu_mode_build;


    public MenuBarSimulationController(MenuBarSwitchLambda switchLambda, CanvasController canvasController){
        this.switchLambda=switchLambda;
        this.canvasController = canvasController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTooltips();
        button_menu_mode_build.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switchLambda.Switch();
            }
        });
    }

    private void addTooltips() {
        Tooltip.install(button_menu_toolbar_selection, new Tooltip("Multiple Selection Tool"));
        Tooltip.install(button_menu_toolbar_pointer, new Tooltip("Individual Selection Tool"));
        Tooltip.install(button_menu_simulation_play, new Tooltip("Play Simulation"));
        Tooltip.install(button_menu_simulation_stop, new Tooltip("Stop Simulation"));
        Tooltip.install(button_menu_mode_simulation, new Tooltip("Simulation Mode"));
        Tooltip.install(button_menu_mode_build, new Tooltip("Build Mode"));
    }
}