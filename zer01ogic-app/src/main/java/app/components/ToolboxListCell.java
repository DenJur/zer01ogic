package app.components;

import app.models.ToolboxItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ToolboxListCell extends ListCell<ToolboxItem> {

    @FXML
    private AnchorPane anchorpane_toolboxItemIcon;

    @FXML
    private Label label_toolboxItemName;

    @FXML
    private GridPane gridpane_toolboxCell;

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
            item=toolboxitem;
            //Add icon image and set item name
            ObservableList<Node> children = anchorpane_toolboxItemIcon.getChildren();
            children.clear();
            children.addAll(toolboxitem.getIcon());
            anchorpane_toolboxItemIcon.setScaleX(0.35);
            anchorpane_toolboxItemIcon.setScaleY(0.35);
            label_toolboxItemName.setText(toolboxitem.getName());
            setGraphic(gridpane_toolboxCell);
        }
    }
}
