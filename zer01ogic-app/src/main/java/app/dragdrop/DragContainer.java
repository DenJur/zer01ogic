package app.dragdrop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.components.Pin;
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
	public static final DataFormat DraggableLink = new DataFormat("Link");

	public static Pin getSource() {
		return source;
	}

	public static void setSource(Pin source) {
		DragContainer.source = source;
	}

	private static Pin source;

	public Point2D getPoint() {
		return point;
	}

	public void addPoint(Point2D point2D) {
		point=point2D;
	}

}
