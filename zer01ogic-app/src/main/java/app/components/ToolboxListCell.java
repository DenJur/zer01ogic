package app.components;

import app.models.ToolboxItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private FXMLLoader loader;

    @Override
    protected void updateItem(ToolboxItem toolboxitem, boolean empty) {
        super.updateItem(toolboxitem, empty);

        //clear the cell
        if(empty || toolboxitem == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/app/view/ToolboxCellView.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Add icon image and set item name
            anchorpane_toolboxItemIcon.getChildren().addAll(toolboxitem.getIcon());
            anchorpane_toolboxItemIcon.setScaleX(0.35);
            anchorpane_toolboxItemIcon.setScaleY(0.35);
            label_toolboxItemName.setText(toolboxitem.getName());

            setText(null);
            setGraphic(gridpane_toolboxCell);
        }
    }
}
