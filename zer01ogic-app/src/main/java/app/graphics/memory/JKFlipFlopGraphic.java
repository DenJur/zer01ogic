package app.graphics.memory;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collection;


public class JKFlipFlopGraphic extends Group {
    public static final Collection<String> styles = Arrays.asList("Memory", "JKFlipFlopGraphic");

    public JKFlipFlopGraphic() {
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

        //left line line of box
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
        Text jInput = new Text("J");
        jInput.setStyle("-fx-font-weight: bold");
        jInput.setTranslateX(25);
        jInput.setTranslateY(25);
        Text kInput = new Text("K");
        kInput.setStyle("-fx-font-weight: bold");
        kInput.setTranslateX(25);
        kInput.setTranslateY(55);
        Text q = new Text("Q");
        q.setStyle("-fx-font-weight: bold");
        q.setTranslateX(85);
        q.setTranslateY(25);
        labelledImage.getChildren().addAll(jInput,kInput,q,path);
        this.getChildren().add(labelledImage);
    }
}
