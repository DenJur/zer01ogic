package app.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    public Pane pane_menu_buildmenu;
    public Pane pane_toolbox;

    Node menuBarBuild;
    Node menuBarSimulation;
    Node toolbox;


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
            toolboxLoader.setController(new ToolboxController());
            toolbox = toolboxLoader.load();

            //TODO load the selected item properties -----------------------------------------------------------------------------------------------------------------------------------


            //ADD THE SCENES TO THE GUI SO THEY'RE VISIBLE

            //when the main scene is first created, set the build menu bar as the visible menu bar
            pane_menu_buildmenu.getChildren().add(menuBarBuild);
            pane_toolbox.getChildren().add(toolbox);
            //TODO add the selected item properties ------------------------------------------------------------------------------------------------------------------------------------

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
            children.add(menuBarSimulation);
        } else {
            children.clear();
            children.add(menuBarBuild);
        }
    }
}
