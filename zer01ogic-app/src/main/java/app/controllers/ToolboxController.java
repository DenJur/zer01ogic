package app.controllers;

import app.componentFactories.io.ClockFactory;
import app.componentFactories.io.LightbulbFactory;
import app.componentFactories.io.SwitchFactory;
import app.componentFactories.logicGates.*;
import app.componentFactories.memory.DFlipFlopFactory;
import app.componentFactories.memory.JKFlipFlopFactory;
import app.componentFactories.memory.SRFlipFlopFactory;
import app.componentFactories.memory.TFlipFlopFactory;
import app.components.ToolboxListCell;
import app.graphics.io.ClockGraphic;
import app.graphics.io.LightbulbGraphic;
import app.graphics.io.SwitchGraphic;
import app.graphics.logicGates.*;
import app.graphics.memory.DFlipFlopGraphic;
import app.graphics.memory.JKFlipFlopGraphic;
import app.graphics.memory.SRFlipFlopGraphic;
import app.graphics.memory.TFlipFlopGraphic;
import app.models.ToolboxItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolboxController implements Initializable {

    @FXML private ListView<ToolboxItem> listview_logicGates;
    @FXML private ListView<ToolboxItem> listview_inputsOutputs;
    @FXML private ListView<ToolboxItem> listview_memory;
    @FXML private ListView<ToolboxItem> listview_arithmeticUnits;
    @FXML private ListView<ToolboxItem> listview_userCreated;

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
                new ToolboxItem("AND",new AndGateGraphic(),2,1, new AndFactory()),
                new ToolboxItem("OR",new OrGateGraphic(),2,1, new OrFactory()),
                new ToolboxItem("NOT",new NotGateGraphic(),1,1, new NotFactory()),
                new ToolboxItem("XOR",new XorGateGraphic(),2,1, new XorFactory()),
                new ToolboxItem("NAND", new NandGateGraphic(),2,1, new NandFactory()),
                new ToolboxItem("NOR",new NorGateGraphic(),2,1, new NorFactory()),
                new ToolboxItem("XNOR",new XnorGateGraphic(),2,1, new XnorFactory())
        );

        //Inputs/Outputs
        toolboxItemObservableListInputsOutputs.addAll(
                new ToolboxItem("Switch",new SwitchGraphic(),0,1, new SwitchFactory()),
                new ToolboxItem("Lightbulb",new LightbulbGraphic(),1,0, new LightbulbFactory()),
                new ToolboxItem("Clock",new ClockGraphic(),0,1,new ClockFactory())
        );

        //Memory
        //TODO Add Memory units ------------------------------------------------------
        toolboxItemObservableListMemory.addAll(
                new ToolboxItem("DFlipFlop",new DFlipFlopGraphic(),2,1, new DFlipFlopFactory()),
                new ToolboxItem("TFlipFlop", new TFlipFlopGraphic(), 2, 1, new TFlipFlopFactory()),
                new ToolboxItem("JKFlipFlop", new JKFlipFlopGraphic(), 3, 1, new JKFlipFlopFactory()),
                new ToolboxItem("SRFlipFlop", new SRFlipFlopGraphic(), 3, 1, new SRFlipFlopFactory())
        );

        //Arithmetic Units
        //TODO Add Arithmetic Units --------------------------------------------------

        //User Created Circuits
        //TODO Load in user created simulation -----------------------------------------
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listview_logicGates.setItems(toolboxItemObservableListLogicGates);
        listview_logicGates.setCellFactory(logicGatesListView -> new ToolboxListCell());

        listview_inputsOutputs.setItems(toolboxItemObservableListInputsOutputs);
        listview_inputsOutputs.setCellFactory(inputsOutputsListView -> new ToolboxListCell());

        listview_memory.setItems(toolboxItemObservableListMemory);
        listview_memory.setCellFactory(memoryListView -> new ToolboxListCell());

        //Add drag handler for listviews
        addDragDetection(listview_logicGates);
        addDragDetection(listview_inputsOutputs);
        addDragDetection(listview_memory);
        addDragDetection(listview_arithmeticUnits);
        addDragDetection(listview_userCreated);
    }

    private void addDragDetection(ListView list) {

        list.setOnDragDetected (new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //send a message to the main class saying that this list has been dragged
                //and send the details of the selected item so it can be handled correctly
                mainSceneController.toolboxDragDrop(event);
            }
        });

        list.setOnMousePressed (new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //find the currently selected list item
                ToolboxItem toolboxItem = (ToolboxItem) list.getSelectionModel().getSelectedItem();

                //send a message to the main class saying that this list has been dragged
                //and send the details of the selected item so it can be handled correctly
                mainSceneController.toolboxClick(toolboxItem, event);
            }
        });

        list.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                mainSceneController.clearDraggable(event);
            }
        });
    }
}
