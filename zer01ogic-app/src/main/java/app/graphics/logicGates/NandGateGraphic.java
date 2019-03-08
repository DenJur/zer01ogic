package app.graphics.logicGates;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;

import java.util.Arrays;
import java.util.Collection;

import static app.graphics.GraphicsHelper.buildCircleArc;

public class NandGateGraphic extends Group {
    public static final Collection<String> styles= Arrays.asList("LogicGate", "NAND");

    public NandGateGraphic(){
        super();
        Path path = new Path();
        ObservableList<PathElement> elements = path.getElements();
        elements.add(new MoveTo(15, 60.0));
        elements.add(new LineTo(45, 60.0));
        ArcTo arcTo = new ArcTo();
        arcTo.setX(45);
        arcTo.setY(0.0);
        arcTo.setRadiusX(30.0);
        arcTo.setRadiusY(30.0);
        elements.add(arcTo);
        elements.add(new LineTo(15, 0.0));
        elements.add(new ClosePath());
        elements.add(new MoveTo(15, 50.0));
        elements.add(new LineTo(0.0, 50.0));
        elements.add(new MoveTo(15, 10.0));
        elements.add(new LineTo(0.0, 10.0));

        buildCircleArc(85,30.0,5.0,5.0, elements);
        elements.add(new MoveTo(86, 30.0));
        elements.add(new LineTo(100.0, 30.0));
        this.getStyleClass().addAll(styles);
        this.getChildren().add(path);
    }
}
