package app.graphics;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathElement;

public class GraphicsHelper {

    public static void buildCircleArc(double x, double y, double radiusX, double radiusY, ObservableList<PathElement> elements){
        elements.add(new MoveTo(x - radiusX, y - radiusY));
        ArcTo arcTo = new ArcTo();
        arcTo.setX(x - radiusX + 1.0);
        arcTo.setY(y - radiusY);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setLargeArcFlag(true);
        arcTo.setXAxisRotation(0);
        elements.add(arcTo);
        elements.add(new ClosePath());
    }

    public static double centerY(double x1, double y1, double x2, double y2, double radius)
    {
        double radsq = radius * radius;
        double q = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
        double y3 = (y1 + y2) / 2;
        return y3 + Math.sqrt(radsq - ((q / 2) * (q / 2))) * ((x2-x1) / q);
    }

    public static double centerX(double x1, double y1, double x2, double y2, double radius)
    {
        double radsq = radius * radius;
        double q = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
        double x3 = (x1 + x2) / 2;
        return x3 + Math.sqrt(radsq - ((q / 2) * (q / 2))) * ((y1 - y2) / q);
    }

    public static void resize(Node node, double maxX, double maxY){
            VBox box = new VBox(node);
            Group group = new Group(box);
            //need to create a scene so that node sizes get calculated
            Scene scene = new Scene(group);
            group.applyCss();
            group.layout();
            double scaleX = maxX/box.getWidth();
            double scaleY = maxY/box.getHeight();
            //Set the scale to be the smallest dimension, and apply the scale to x and y
            double scale = scaleX < scaleY ? scaleX:scaleY;
            node.setScaleX(scale);
            node.setScaleY(scale);
    }
}
