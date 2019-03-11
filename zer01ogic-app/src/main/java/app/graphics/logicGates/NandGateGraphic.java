package app.graphics.logicGates;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;

import static app.graphics.GraphicsHelper.buildCircleArc;

public class NandGateGraphic extends Group {
    public NandGateGraphic(){
        super();
        Path path = new Path();
        ObservableList<PathElement> elements = path.getElements();
        elements.add(new MoveTo(20.0, 60.0));
        elements.add(new LineTo(50.0, 60.0));
        ArcTo arcTo = new ArcTo();
        arcTo.setX(50.0);
        arcTo.setY(0.0);
        arcTo.setRadiusX(30.0);
        arcTo.setRadiusY(30.0);
        elements.add(arcTo);
        elements.add(new LineTo(20.0, 0.0));
        elements.add(new ClosePath());
        elements.add(new MoveTo(20.0, 50.0));
        elements.add(new LineTo(0.0, 50.0));
        elements.add(new MoveTo(20.0, 10.0));
        elements.add(new LineTo(0.0, 10.0));

        buildCircleArc(90.0,30.0,5.0,5.0, elements);
        elements.add(new MoveTo(91.0, 30.0));
        elements.add(new LineTo(100.0, 30.0));
        this.getStyleClass().add("LogicGate");
        this.getStyleClass().add("NAND");
        this.getChildren().add(path);
    }
}
