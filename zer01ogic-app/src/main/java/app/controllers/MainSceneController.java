package app.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    public GridPane gridpane_toolboxProperties;
    public GridPane gridpane_menu;
    public GridPane gridpane_main;

    private Node menuBarBuild;
    private Node menuBarSimulation;
    private Node toolbox;

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
            gridpane_menu.add(menuBarBuild, 2, 0);
            gridpane_menu.add(menuBarSimulation, 2, 0);
            menuBarSimulation.setVisible(false);
            gridpane_toolboxProperties.add(toolbox, 0, 0);

            //TODO add the selected item properties ------------------------------------------------------------------------------------------------------------------------------------

        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
