package app.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    public Pane pane_menu_buildmenu;
    Node menuBarBuild;
    Node menuBarRun;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            //load the menu bar with the build menu
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/app/view/buildMenu.fxml"));
            menuLoader.setController(new MenuBarBuildController(this::SwitchBars));
            menuBarBuild = menuLoader.load();

            //load the menu bar with the simulation menu (so it can be switched when needed)
            menuLoader = new FXMLLoader(getClass().getResource("/app/view/simulationMenu.fxml"));
            menuLoader.setController(new MenuBarSimulationController(this::SwitchBars));
            menuBarRun = menuLoader.load();

            //when the main scene is first created, load in the build menu bar
            pane_menu_buildmenu.getChildren().add(menuBarBuild);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switch the menu bars when requested
     */
    private void SwitchBars() {
        ObservableList<Node> children = pane_menu_buildmenu.getChildren();
        if (children.contains(menuBarBuild)) {
            children.clear();
            children.add(menuBarRun);
        } else {
            children.clear();
            children.add(menuBarBuild);
        }
    }
}
