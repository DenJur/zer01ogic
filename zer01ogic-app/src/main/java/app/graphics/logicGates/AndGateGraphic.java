package app.graphics.logicGates;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;

public class AndGateGraphic extends Group {
    public AndGateGraphic(){
        super();
        Path path=new Path();
        ObservableList<PathElement> elements = path.getElements();
        elements.add(new MoveTo(20.0,60.0));
        elements.add(new LineTo(50.0,60.0));
        ArcTo arcTo = new ArcTo();
        arcTo.setX(50.0);
        arcTo.setY(0.0);
        arcTo.setRadiusX(30.0);
        arcTo.setRadiusY(30.0);
        elements.add(arcTo);
        elements.add(new LineTo(20.0,0.0));
        elements.add(new ClosePath());
        elements.add(new MoveTo(80.0,30.0));
        elements.add(new LineTo(100.0,30.0));
        elements.add(new MoveTo(0.0,50.0));
        elements.add(new LineTo(20.0,50.0));
        elements.add(new MoveTo(0.0,10.0));
        elements.add(new LineTo(20.0,10.0));

        this.getStyleClass().add("LogicGate");
        this.getStyleClass().add("AND");
        this.getChildren().add(path);
    }
}
