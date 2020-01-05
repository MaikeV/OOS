package application;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    Button btnClose;

    @FXML
    public void handleButtonPush(Event event) {
        System.out.println("Exit Application because of button push");
        Platform.exit();
    }
}
