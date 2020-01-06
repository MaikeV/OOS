package prak5client;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import prak5gemklassen.User;

public class LoginController {
    @FXML Button btnLogin;
    @FXML TextField tf_userId;
    @FXML TextField tf_password;
    @FXML CheckBox cb_new;
    @FXML CheckBox cb_remote;
    @FXML Label lbl_error;

    private Client main;
    public boolean isNew = false;

    public void setMainApplication(Client main) {
        this.main = main;
    }

    @FXML
    public void login(Event event) {
        if ((tf_userId.getText().isEmpty() || tf_password.getText().isEmpty()) && !cb_new.isSelected()) {
            lbl_error.setText("User ID und Passwort duerfen nicht leer sein");
            return;
        }

        if(cb_new.isSelected()) {
            if (cb_remote.isSelected()) {
                this.main.registerLocal();
            } else {
                this.main.registerRemote();
            }
        } else {
            User newUser = new User(tf_userId.getText(), tf_password.getText().toCharArray());

            if (cb_remote.isSelected()) {
                if (!this.main.loginRemote(newUser)) {
                    lbl_error.setText("Benutzer existiert nicht");
                }
            } else {
                if (!this.main.loginLocal(newUser)) {
                    lbl_error.setText("Benutzer existiert nicht");
                }
            }
        }
    }

    @FXML
    public void checkbox() {
        isNew = cb_new.isSelected();
        System.out.println(isNew);
    }
}
