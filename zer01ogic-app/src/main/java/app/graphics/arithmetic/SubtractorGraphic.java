package app.graphics.arithmetic;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collection;


public class SubtractorGraphic extends Group {
    public static final Collection<String> styles = Arrays.asList("LabelledNode", "Arithmetic", "SubtractorGraphic");

    public SubtractorGraphic() {
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
        elements.add(new LineTo(170,0));

        //right line of box
        elements.add(new LineTo(170,100));

        //bottom line of box
        elements.add(new LineTo(20,100));

        //left line of box
        elements.add(new MoveTo(20,0));
        elements.add(new LineTo(20,100));

        //first output
        elements.add(new MoveTo(170, 35));
        elements.add(new LineTo(190, 35));

        //second output
        elements.add(new MoveTo(170, 65));
        elements.add(new LineTo(190, 65));

        this.getStyleClass().addAll(styles);

        Group labelledImage = new Group();
        Text aInput = new Text("A");
        aInput.setTranslateX(25);
        aInput.setTranslateY(28);
        Text bInput = new Text("B");
        bInput.setTranslateX(25);
        bInput.setTranslateY(58);
        Text cInput = new Text("B IN");
        cInput.setTranslateX(25);
        cInput.setTranslateY(88);

        Text d = new Text("D");
        d.setTranslateX(113);
        d.setTranslateY(43);

        Text bOut = new Text("B OUT");
        bOut.setTranslateX(95);
        bOut.setTranslateY(73);
        labelledImage.getChildren().addAll(aInput,bInput, cInput, d, bOut, path);

        labelledImage.setScaleX(0.7);
        labelledImage.setScaleY(0.7);

        this.getChildren().add(labelledImage);
    }
}
