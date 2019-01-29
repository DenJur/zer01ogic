package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import test.TextProvider;

import java.net.URL;
import java.util.ResourceBundle;

public class ExampleController implements Initializable {

    @FXML
    private Button helloWorldButton;
    @FXML private Button goodByeWorldButton;
    @FXML private Label label;

    //    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

    }

    @FXML
    private void displayHelloWorld() {
        label.setText(TextProvider.getHello());
        helloWorldButton.setVisible(false);
        if (!goodByeWorldButton.isVisible())
            goodByeWorldButton.setVisible(true);
    }

    /***
     * Goodbye teller
     */
    @FXML
    private void goodByeWorld() {
        label.setText(TextProvider.getGoodbye());
        goodByeWorldButton.setVisible(false);
        if (!helloWorldButton.isVisible())
            helloWorldButton.setVisible(true);
    }
}
