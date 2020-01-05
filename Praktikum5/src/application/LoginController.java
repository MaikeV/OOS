package application;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML Button btnLogin;
    @FXML TextField tf_userId;
    @FXML TextField tf_password;
    @FXML CheckBox cb_new;
    @FXML Label lbl_error;

    private Main main;
    public boolean isNew = false;

    public void setMainApplication(Main main) {
        this.main = main;
    }

    @FXML
    public void login(Event event) {
        if ((tf_userId.getText().isEmpty() || tf_password.getText().isEmpty()) && !cb_new.isSelected()) {
            lbl_error.setText("User ID und Passwort duerfen nicht leer sein");
            return;
        }

        if(cb_new.isSelected()) {
            this.main.register();
        } else {
            User newUser = new User(tf_userId.getText(), tf_password.getText().toCharArray());

            if (!this.main.login(newUser)) {
                lbl_error.setText("Benutzer existiert nicht");
            }
        }
    }

    @FXML
    public void checkbox() {
        isNew = cb_new.isSelected();
        System.out.println(isNew);
    }
}
