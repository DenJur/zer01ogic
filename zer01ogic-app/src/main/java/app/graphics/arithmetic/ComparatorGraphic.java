package app.graphics.arithmetic;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collection;


public class ComparatorGraphic extends Group {
    public static final Collection<String> styles = Arrays.asList("LabelledNode", "Arithmetic", "ComparatorGraphic");

    public ComparatorGraphic() {
        super();
        Path path = new Path();
        ObservableList<PathElement> elements = path.getElements();
        //first input
        elements.add(new MoveTo(0.0, 35));
        elements.add(new LineTo(20.0, 35));

        //second input
        elements.add(new MoveTo(0.0, 65));
        elements.add(new LineTo(20.0, 65));

        //top line of box
        elements.add(new MoveTo(20,0));
        elements.add(new LineTo(200,0));

        //right line of box
        elements.add(new LineTo(200,100));

        //bottom line of box
        elements.add(new LineTo(20,100));

        //left line of box
        elements.add(new MoveTo(20,0));
        elements.add(new LineTo(20,100));

        //first output
        elements.add(new MoveTo(200, 20));
        elements.add(new LineTo(220, 20));

        //second output
        elements.add(new MoveTo(200, 50));
        elements.add(new LineTo(220, 50));

        //third output
        elements.add(new MoveTo(200, 80.0));
        elements.add(new LineTo(220, 80.0));

        this.getStyleClass().addAll(styles);

        Group labelledImage = new Group();
        Text aInput = new Text("A");
        aInput.setTranslateX(25);
        aInput.setTranslateY(43);
        Text bInput = new Text("B");
        bInput.setTranslateX(25);
        bInput.setTranslateY(73);

        Text agb = new Text("A>B");
        agb.setTranslateX(143);
        agb.setTranslateY(28);

        Text aeb = new Text("A=B");
        aeb.setTranslateX(143);
        aeb.setTranslateY(58);

        Text alb = new Text("A<B");
        alb.setTranslateX(143);
        alb.setTranslateY(88);

        Text com = new Text("COM");
        com.setTranslateX(85);
        com.setTranslateY(58);

        labelledImage.getChildren().addAll(aInput,bInput, agb, aeb, alb, com, path);

        labelledImage.setScaleX(0.7);
        labelledImage.setScaleY(0.7);

        this.getChildren().add(labelledImage);
    }
}