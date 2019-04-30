package app.graphics.memory;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collection;


public class SRFlipFlopGraphic extends Group {
    public static final Collection<String> styles = Arrays.asList("LabelledNode", "Memory", "SRFlipFlopGraphic");

    public SRFlipFlopGraphic() {
        super();
        Path path = new Path();
        ObservableList<PathElement> elements = path.getElements();
        //first input
        elements.add(new MoveTo(0.0, 20.0));
        elements.add(new LineTo(20.0, 20.0));

        //second input
        elements.add(new MoveTo(0.0, 50.0));
        elements.add(new LineTo(20.0, 50.0));

        //clock input
        elements.add(new MoveTo(0.0, 80.0));
        elements.add(new LineTo(20.0, 80.0));

        //top line of box
        elements.add(new MoveTo(20,0));
        elements.add(new LineTo(100,0));

        //right line of box
        elements.add(new LineTo(100,100));

        //bottom line of box
        elements.add(new LineTo(20,100));

        //left line of box
        elements.add(new MoveTo(20,0));
        elements.add(new LineTo(20,100));

        //first output
        elements.add(new MoveTo(100, 20));
        elements.add(new LineTo(120, 20));

        //triangle clock
        elements.add(new MoveTo(20,70));
        elements.add(new LineTo(35, 80));
        elements.add(new LineTo(20,90));


        this.getStyleClass().addAll(styles);

        Group labelledImage = new Group();
        Text sInput = new Text("S");
        sInput.setTranslateX(25);
        sInput.setTranslateY(28);
        Text rInput = new Text("R");
        rInput.setTranslateX(25);
        rInput.setTranslateY(58);
        Text q = new Text("Q");
        q.setTranslateX(75);
        q.setTranslateY(28);
        labelledImage.getChildren().addAll(sInput,rInput,q,path);

        labelledImage.setScaleX(0.7);
        labelledImage.setScaleY(0.7);

        this.getChildren().add(labelledImage);
    }
}