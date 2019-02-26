package app.dragdrop;

import java.io.IOException;

import app.models.ToolboxItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class DragIcon extends AnchorPane{
	
	@FXML AnchorPane root_pane;

	private String type;

	public void relocateToPoint (Point2D p) {

		//relocates the object to a point that has been converted to
		//scene coordinates
		Point2D localCoords = getParent().sceneToLocal(p);
		
		relocate ( 
				(int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
				(int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
			);
	}

	public String getType () { return type; }
	
	public void setType (ToolboxItem toolboxItem) {
		type = toolboxItem.getName();

		//clear any contents of the current dragicon
		this.getChildren().clear();
		//set the contents to the new icon
		this.getChildren().add(toolboxItem.getIcon());
	}
}
