package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/MainScene.fxml"));
        root.getStylesheets().add(getClass().getResource("/graphics/default.css").toExternalForm());
        File customCss=new File("custom.css");
        if(customCss.exists())
            root.getStylesheets().add("file:///"+customCss.getAbsolutePath().replace("\\", "/"));
        primaryStage.setTitle("Zer0 1ogic");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("/graphics/LogoIcon.png"));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}
