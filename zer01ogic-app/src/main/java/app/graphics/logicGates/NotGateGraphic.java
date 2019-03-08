package app.graphics.logicGates;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;

import static app.graphics.GraphicsHelper.buildCircleArc;

public class NotGateGraphic extends Group {
    public NotGateGraphic(){
        super();
        Path path=new Path();
        ObservableList<PathElement> elements = path.getElements();
        elements.add(new MoveTo(25.0,40.0));
        elements.add(new LineTo(70.0,20.0));
        elements.add(new LineTo(25.0,0.0));
        elements.add(new ClosePath());

        buildCircleArc(80.0,20.0,5.0,5.0, elements);
        elements.add(new MoveTo(81.0, 20.0));
        elements.add(new LineTo(100.0, 20.0));

        elements.add(new MoveTo(0.0,20.0));
        elements.add(new LineTo(25.0,20.0));

        this.getStyleClass().add("LogicGate");
        this.getStyleClass().add("NOT");
        this.getChildren().add(path);
    }
}
