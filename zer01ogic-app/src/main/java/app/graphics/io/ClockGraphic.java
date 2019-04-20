package app.graphics.io;

import app.graphics.GraphicsHelper;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.SVGPath;

public class ClockGraphic extends Group {

    public static final String clockStyle = "Clock";
    public static final String clockOnStyle = "ClockOn";
    public static final String clockOffStyle = "ClockOff";

    public ClockGraphic() {
        super();
        SVGPath path = new SVGPath();
        path.setContent("M438.817,227.925c0-106.9-86.7-193.7-193.7-193.7s-193.6,86.7-193.6,193.7c0,66.9,33.8,126,85.6,161l-71.6,85.2\n" +
                "\t\tc-3.5,4.3-3.1,10.1,1.2,13.6c3.7,3.7,10.8,2.7,13.6-1.2l73.5-87.5c27.2,14.4,58.3,22.9,91.4,22.9s64.2-8.2,91.4-22.9l73.5,87.5\n" +
                "\t\tc4.9,4.9,10.7,4,13.6,1.2c4.3-3.5,4.7-9.7,1.2-13.6l-71.6-85.2C405.017,353.925,438.817,295.225,438.817,227.925z\n" +
                "\t\tM245.117,381.925c-85.2,0-154-68.8-154-154s68.8-154,154-154s154,68.8,154,154S330.317,382.925,245.117,382.925z"
        );
        Group container = new Group();
        container.getChildren().add(path);

        path=new SVGPath();
        path.setContent("M457.917,23.325c-31.5-31.1-82.4-31.1-113.6,0l113.6,113.6C489.017,105.825,489.017,54.825,457.917,23.325z");
        container.getChildren().add(path);

        path=new SVGPath();
        path.setContent("M32.417,23.325c-31.5,31.5-31.5,82.1,0,113.6l113.6-113.6C114.917-7.775,63.917-7.775,32.417,23.325z");
        container.getChildren().add(path);

        path=new SVGPath();
        path.setContent("M315.117,252.425l-60.3-30.3v-119c0-5.4-4.3-9.7-9.7-9.7s-9.7,4.3-9.7,9.7v124.8c0,3.5,1.9,7,5.4,8.6l65.7,33.1\n" +
                "\t\tc4.8,2.3,10.8,0.8,12.8-4.3C321.817,260.925,319.817,254.725,315.117,252.425z");
        container.getChildren().add(path);


        GraphicsHelper.resize(container, 50, 50);
        this.getChildren().add(container);

        //TODO setup default graphics
        this.getStyleClass().addAll(ClockGraphic.clockStyle, ClockGraphic.clockOffStyle);
    }

    public void setStyle(ClockStyle style) {
        ObservableList<String> currentStyles = this.getStyleClass();
        currentStyles.clear();
        if (style == ClockStyle.On) {
            currentStyles.addAll(ClockGraphic.clockStyle, ClockGraphic.clockOnStyle);
        } else {
            currentStyles.addAll(ClockGraphic.clockStyle, ClockGraphic.clockOffStyle);
        }
    }

    public enum ClockStyle {
        On,
        Off
    }
}
