package app.graphics.io;

import app.enums.DrawStyle;
import app.graphics.GraphicsHelper;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.SVGPath;

public class LightbulbGraphic extends Group {

    public static final String LIGHTBULB_STYLE = "Lightbulb";
    public static final String LIGHTBULB_BUILD_STYLE = "LightbulbBuild";
    public static final String LIGHTBULB_ON_STYLE = "LightbulbOn";
    public static final String LIGHTBULB_OFF_STYLE = "LightbulbOff";

    private volatile DrawStyle currentStyle;

    public LightbulbGraphic() {
        super();
        SVGPath path = new SVGPath();
        path.setContent("M298.4,424.7v14.2c0,11.3-8.3,20.7-19.1,22.3l-3.5,12.9c-1.9,7-8.2,11.9-15.5,11.9h-34.7\n" +
                "\t\tc-7.3,0-13.6-4.9-15.5-11.9l-3.4-12.9c-10.9-1.7-19.2-11-19.2-22.4v-14.2c0-7.6,6.1-13.7,13.7-13.7h83.5\n" +
                "\t\tC292.3,411,298.4,417.1,298.4,424.7z M362.7,233.3c0,32.3-12.8,61.6-33.6,83.1c-15.8,16.4-26,37.3-29.4,59.6\n" +
                "\t\tc-1.5,9.6-9.8,16.7-19.6,16.7h-74.3c-9.7,0-18.1-7-19.5-16.6c-3.5-22.3-13.8-43.5-29.6-59.8c-20.4-21.2-33.1-50-33.4-81.7\n" +
                "\t\tc-0.7-66.6,52.3-120.5,118.9-121C308.7,113.1,362.7,166.9,362.7,233.3z M256.5,160.8c0-7.4-6-13.5-13.5-13.5\n" +
                "\t\tc-47.6,0-86.4,38.7-86.4,86.4c0,7.4,6,13.5,13.5,13.5c7.4,0,13.5-6,13.5-13.5c0-32.8,26.7-59.4,59.4-59.4\n" +
                "\t\tC250.5,174.3,256.5,168.3,256.5,160.8z M243,74.3c7.4,0,13.5-6,13.5-13.5V13.5c0-7.4-6-13.5-13.5-13.5s-13.5,6-13.5,13.5v47.3\n" +
                "\t\tC229.5,68.3,235.6,74.3,243,74.3z M84.1,233.2c0-7.4-6-13.5-13.5-13.5H23.3c-7.4,0-13.5,6-13.5,13.5c0,7.4,6,13.5,13.5,13.5h47.3\n" +
                "\t\tC78.1,246.7,84.1,240.7,84.1,233.2z M462.7,219.7h-47.3c-7.4,0-13.5,6-13.5,13.5c0,7.4,6,13.5,13.5,13.5h47.3\n" +
                "\t\tc7.4,0,13.5-6,13.5-13.5C476.2,225.8,470.2,219.7,462.7,219.7z M111.6,345.6l-33.5,33.5c-5.3,5.3-5.3,13.8,0,19.1\n" +
                "\t\tc2.6,2.6,6.1,3.9,9.5,3.9s6.9-1.3,9.5-3.9l33.5-33.5c5.3-5.3,5.3-13.8,0-19.1C125.4,340.3,116.8,340.3,111.6,345.6z M364.9,124.8\n" +
                "\t\tc3.4,0,6.9-1.3,9.5-3.9l33.5-33.5c5.3-5.3,5.3-13.8,0-19.1c-5.3-5.3-13.8-5.3-19.1,0l-33.5,33.5c-5.3,5.3-5.3,13.8,0,19.1\n" +
                "\t\tC358,123.5,361.4,124.8,364.9,124.8z M111.6,120.8c2.6,2.6,6.1,3.9,9.5,3.9s6.9-1.3,9.5-3.9c5.3-5.3,5.3-13.8,0-19.1L97.1,68.2\n" +
                "\t\tc-5.3-5.3-13.8-5.3-19.1,0c-5.3,5.3-5.3,13.8,0,19.1L111.6,120.8z M374.4,345.6c-5.3-5.3-13.8-5.3-19.1,0c-5.3,5.3-5.3,13.8,0,19.1\n" +
                "\t\tl33.5,33.5c2.6,2.6,6.1,3.9,9.5,3.9s6.9-1.3,9.5-3.9c5.3-5.3,5.3-13.8,0-19.1L374.4,345.6z");
        Group container = new Group();
        container.getChildren().add(path);
        GraphicsHelper.resize(container, 50, 50);
        this.getChildren().add(container);

        //Set up graphic in build mode
        setStyle(DrawStyle.Build);
    }


    public void updateStyle() {
        ObservableList<String> currentStyles = this.getStyleClass();
        currentStyles.clear();
        switch(currentStyle){
            case Build:
                this.getStyleClass().addAll(LIGHTBULB_STYLE, LIGHTBULB_BUILD_STYLE);
                break;
            case On:
                this.getStyleClass().addAll(LIGHTBULB_STYLE, LIGHTBULB_ON_STYLE);
                break;
            case Off:
                this.getStyleClass().addAll(LIGHTBULB_STYLE, LIGHTBULB_OFF_STYLE);
                break;
        }
    }
    
    public void setStyle(DrawStyle newStyle) {
        currentStyle = newStyle;
    }
}
