package app.controllers;

import afester.javafx.svg.SvgLoader;
import app.components.ToolboxListCell;
import app.dragdrop.DragContainer;
import app.dragdrop.DragIcon;
import app.models.ToolboxItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

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

    //The instance of MainSceneController
    private MainSceneController mainSceneController;

    public ToolboxController(MainSceneController mainSceneController) {
        this.mainSceneController = mainSceneController;

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
        toolboxItemObservableListInputsOutputs.addAll(
                new ToolboxItem("Switch",SVGLoader("Inputs_Outputs/Switch.svg"),0,1),
                new ToolboxItem("Lightbulb",SVGLoader("Inputs_Outputs/Lightbulb.svg"),1,0)
        );

        //Memory
        //TODO Add Memory units ------------------------------------------------------

        //Arithmetic Units
        //TODO Add Arithmetic Units --------------------------------------------------

        //User Created Circuits
        //TODO Load in user created circuits -----------------------------------------
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listview_logicGates.setItems(toolboxItemObservableListLogicGates);
        listview_logicGates.setCellFactory(logicGatesListView -> new ToolboxListCell());

        listview_inputsOutputs.setItems(toolboxItemObservableListInputsOutputs);
        listview_inputsOutputs.setCellFactory(inputsOutputsListView -> new ToolboxListCell());

        //Add drag handler for listviews
        addDragDetection(listview_logicGates);
        addDragDetection(listview_inputsOutputs);
        addDragDetection(listview_memory);
        addDragDetection(listview_arithmeticUnits);
        addDragDetection(listview_userCreated);
    }

    private Group SVGLoader(String path) {
        SvgLoader loader = new SvgLoader();
        InputStream svgFile = getClass().getResourceAsStream("/graphics/Toolbox_Icons/" + path);
        Group svgImage = loader.loadSvg(svgFile);

        return svgImage;
    }

    private void addDragDetection(ListView list) {

        list.setOnDragDetected (new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //find the currently selected list item
                ToolboxItem toolboxItem = (ToolboxItem) list.getSelectionModel().getSelectedItem();

                //send a message to the main class saying that this list has been dragged
                //and send the details of the selected item so it can be handled correctly
                mainSceneController.toolboxDragDrop(toolboxItem, event);
            }
        });
    }
}
