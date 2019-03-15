package app.graphics.logicGates;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;

import java.util.Arrays;
import java.util.Collection;

import static app.graphics.GraphicsHelper.*;

public class XnorGateGraphic extends Group {
    public static final Collection<String> styles=Arrays.asList("LogicGate", "XNOR");

    public XnorGateGraphic() {
        super();
        Path path = new Path();
        ObservableList<PathElement> elements = path.getElements();
        elements.add(new MoveTo(35, 60.0));
        ArcTo arcTo = new ArcTo();
        arcTo.setX(75);
        arcTo.setY(30.0);
        arcTo.setRadiusX(50.0);
        arcTo.setRadiusY(50.0);
        elements.add(arcTo);

        arcTo = new ArcTo();
        arcTo.setX(35);
        arcTo.setY(0.0);
        arcTo.setRadiusX(50.0);
        arcTo.setRadiusY(50.0);
        elements.add(arcTo);
        elements.add(new LineTo(15, 0.0));

        arcTo = new ArcTo();
        arcTo.setX(15);
        arcTo.setY(60.0);
        arcTo.setRadiusX(65.0);
        arcTo.setRadiusY(65.0);
        arcTo.setSweepFlag(true);
        elements.add(arcTo);
        elements.add(new ClosePath());

        buildCircleArc(85,30.0,5.0,5.0, elements);
        elements.add(new MoveTo(86, 30.0));
        elements.add(new LineTo(100.0, 30.0));

        elements.add(new MoveTo(10, 1.0));
        arcTo = new ArcTo();
        arcTo.setX(10);
        arcTo.setY(60.0);
        arcTo.setRadiusX(65.0);
        arcTo.setRadiusY(65.0);
        arcTo.setSweepFlag(true);
        elements.add(arcTo);
        arcTo = new ArcTo();
        arcTo.setX(10);
        arcTo.setY(1.0);
        arcTo.setRadiusX(65.0);
        arcTo.setRadiusY(65.0);
        arcTo.setSweepFlag(false);
        elements.add(arcTo);

        double centerx = centerX(10, 0, 10, 60, 65);
        double centery = centerY(10, 0, 10, 60, 65);
        double cos = Math.cos(Math.asin((50.0 - centery) / 65.0));

        double lineWidth=getPathStrokeWidth(styles)/2;

        elements.add(new MoveTo(0.0, 50.0));
        elements.add(new LineTo(centerx + (65.0 * cos)-lineWidth, 50.0));
        elements.add(new MoveTo(0.0, 10.0));

        cos = Math.cos(Math.asin((10.0 - centery) / 65.0));
        elements.add(new LineTo(centerx + (65.0 * cos)-lineWidth, 10.0));

        this.getStyleClass().addAll(styles);
        this.getChildren().add(path);
    }
}
