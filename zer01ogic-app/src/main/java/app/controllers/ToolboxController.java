package app.controllers;

import afester.javafx.svg.SvgLoader;
import app.components.ToolboxListCell;
import app.models.ToolboxItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ListView;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ToolboxController implements Initializable {

    @FXML
    private ListView<ToolboxItem> listview_logicGates;

    @FXML
    private ListView<ToolboxItem> listview_inputsOutputs;

    @FXML
    private ListView<ToolboxItem> listview_memory;

    @FXML
    private ListView<ToolboxItem> listview_arithmeticUnits;

    @FXML
    private ListView<ToolboxItem> listview_userCreated;

    private ObservableList<ToolboxItem> toolboxItemObservableListLogicGates;
    private ObservableList<ToolboxItem> toolboxItemObservableListInputsOutputs;
    private ObservableList<ToolboxItem> toolboxItemObservableListMemory;
    private ObservableList<ToolboxItem> toolboxItemObservableListArithmeticUnits;
    private ObservableList<ToolboxItem> toolboxItemObservableListUserCreated;


    public ToolboxController() {
        toolboxItemObservableListLogicGates = FXCollections.observableArrayList();
        toolboxItemObservableListInputsOutputs = FXCollections.observableArrayList();
        toolboxItemObservableListMemory = FXCollections.observableArrayList();
        toolboxItemObservableListArithmeticUnits = FXCollections.observableArrayList();
        toolboxItemObservableListUserCreated = FXCollections.observableArrayList();

        //Basic logic gates
        toolboxItemObservableListLogicGates.addAll(
                new ToolboxItem("AND",SVGLoader("Basic_Gates/AND.svg"),2,1),
                new ToolboxItem("OR",SVGLoader("Basic_Gates/OR.svg"),2,1),
                new ToolboxItem("NOT",SVGLoader("Basic_Gates/NOT.svg"),1,1),
                new ToolboxItem("XOR",SVGLoader("Basic_Gates/XOR.svg"),2,1),
                new ToolboxItem("NAND",SVGLoader("Basic_Gates/NAND.svg"),2,1),
                new ToolboxItem("NOR",SVGLoader("Basic_Gates/NOR.svg"),2,1),
                new ToolboxItem("XNOR",SVGLoader("Basic_Gates/XNOR.svg"),2,1)
        );

        //Inputs/Outputs
        //TODO Add Inputs/Outputs ----------------------------------------------------

        //Memory
        //TODO Add Memory units ------------------------------------------------------

        //Arithmetic Units
        //TODO Add Arithmetic Units --------------------------------------------------

        //User Created Circuits
        //TODO Load in user created circuits -----------------------------------------
    }

    private Group SVGLoader(String path) {
        System.out.println("Hello1");
        SvgLoader loader = new SvgLoader();
        InputStream svgFile = getClass().getResourceAsStream("/graphics/Toolbox_Icons/" + path);
        Group svgImage = loader.loadSvg(svgFile);
        System.out.println("Hello2");
        System.out.println("/graphics/Toolbox_Icons/" + path);
        return svgImage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listview_logicGates.setItems(toolboxItemObservableListLogicGates);
        listview_logicGates.setCellFactory(logicGatesListView -> new ToolboxListCell());
    }
}
