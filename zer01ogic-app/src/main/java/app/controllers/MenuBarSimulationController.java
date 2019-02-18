package app.controllers;

import app.interfaces.MenuBarSwitchLambda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarSimulationController implements Initializable {
    private MenuBarSwitchLambda switchLambda;
    @FXML
    private Button button_menu_mode_build;


    public MenuBarSimulationController(MenuBarSwitchLambda switchLambda){
        this.switchLambda=switchLambda;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_menu_mode_build.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switchLambda.Switch();
            }
        });
    }
}