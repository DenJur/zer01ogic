package app.components;

import app.models.ToolboxItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ToolboxListCell extends ListCell<ToolboxItem> {

    @FXML
    private VBox vbox_imageContainer;

    @FXML
    private Label label_toolboxItemName;

    @FXML
    private HBox hbox_toolboxCell;

    private ToolboxItem item;

    public ToolboxListCell(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/view/ToolboxCellView.fxml"));
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(ToolboxItem toolboxitem, boolean empty) {
        setGraphic(null);
        super.updateItem(toolboxitem, empty);

        //clear the cell
        if(!empty || toolboxitem != null) {
            item = toolboxitem;
            //Add icon image and set item name
            ObservableList<Node> children = vbox_imageContainer.getChildren();
            children.clear();
            children.add(toolboxitem.getIcon());
            label_toolboxItemName.setText(toolboxitem.getName());
            setGraphic(hbox_toolboxCell);
        }
        else {
            setGraphic(null);
        }
    }
}
