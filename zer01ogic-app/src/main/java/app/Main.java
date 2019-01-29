package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import test.TextProvider;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextProvider.setHello("helloText");
        TextProvider.setGoodbye("goodbyeText");
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/Example.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
