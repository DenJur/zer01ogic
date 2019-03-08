package app.graphics.logicGates;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;

import static app.graphics.GraphicsHelper.centerX;
import static app.graphics.GraphicsHelper.centerY;

public class OrGateGraphic extends Group {
    public OrGateGraphic(){
        super();
        Path path=new Path();
        ObservableList<PathElement> elements = path.getElements();
        elements.add(new MoveTo(45.0,60.0));
        ArcTo arcTo = new ArcTo();
        arcTo.setX(85.0);
        arcTo.setY(30.0);
        arcTo.setRadiusX(50.0);
        arcTo.setRadiusY(50.0);
        elements.add(arcTo);

        arcTo = new ArcTo();
        arcTo.setX(45.0);
        arcTo.setY(0.0);
        arcTo.setRadiusX(50.0);
        arcTo.setRadiusY(50.0);
        elements.add(arcTo);
        elements.add(new LineTo(20.0,0.0));

        arcTo = new ArcTo();
        arcTo.setX(20.0);
        arcTo.setY(60.0);
        arcTo.setRadiusX(65.0);
        arcTo.setRadiusY(65.0);
        arcTo.setSweepFlag(true);
        elements.add(arcTo);
        elements.add(new ClosePath());

        elements.add(new MoveTo(85.0,30.0));
        elements.add(new LineTo(100.0,30.0));


        double centerx= centerX(20,0,20,60,65);
        double centery= centerY(20,0,20,60,65);
        double cos=Math.cos(Math.asin((50.0-centery)/65.0));

        elements.add(new MoveTo(0.0,50.0));
        elements.add(new LineTo(centerx+(65.0*cos),50.0));
        elements.add(new MoveTo(0.0,10.0));

        cos=Math.cos(Math.asin((10.0-centery)/65.0));
        elements.add(new LineTo(centerx+(65.0*cos),10.0));
        this.getStyleClass().add("LogicGate");
        this.getStyleClass().add("OR");
        this.getChildren().add(path);
    }
}
