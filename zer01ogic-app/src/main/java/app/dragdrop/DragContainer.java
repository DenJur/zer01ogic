package app.dragdrop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.models.ToolboxItem;
import javafx.geometry.Point2D;
import javafx.scene.input.DataFormat;
import javafx.util.Pair;

public class DragContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1890998765646621338L;

	private static Point2D point;
	public static final DataFormat DraggableNode = new DataFormat("Node");

	public Point2D getPoint() {
		return point;
	}

	public void addPoint(Point2D point2D) {
		point=point2D;
	}

}
