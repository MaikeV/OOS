package application;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML Button btnLogin;
    @FXML TextField tf_userId;
    @FXML TextField tf_password;
    @FXML CheckBox cb_new;

    public boolean isNew = false;

    @FXML
    public void login(Event event) {
        if (tf_userId.getText().equals("") || tf_password.getText().equals("")) {
            return;
        }

        User newUser = new User(tf_userId.getText(), tf_password.getText().toCharArray());

        System.out.println(newUser.toString());
        System.out.println("user is new = " + isNew);

        Platform.exit();
    }

    @FXML
    public void checkbox() {
        isNew = cb_new.isSelected();
        System.out.println(isNew);
    }
}
