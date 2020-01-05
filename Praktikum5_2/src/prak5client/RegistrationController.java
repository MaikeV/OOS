package prak5client;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import prak5gemklassen.User;

public class RegistrationController {
    @FXML Button btnRegister;
    @FXML TextField tf_userId;
    @FXML TextField tf_password;
    @FXML TextField tf_passwordConfirm;
    @FXML Label lbl_error;

    private Client main;

    public void setMainApplication(Client main) {
        this.main = main;
    }

    public void register(Event event) {
        if(!tf_password.getText().equals(tf_passwordConfirm.getText())) {
            lbl_error.setText("Die beiden Passwoerter stimmen nicht ueberein!");
            return;
        }

        if (tf_userId.getText().equals("") || tf_password.getText().equals("")) {
            lbl_error.setText("UserId und Passwort duerfen nicht leer sein!");
            return;
        }

        User newUser = new User(tf_userId.getText(), tf_password.getText().toCharArray());

        if (this.main.local) {
            this.main.newUserLocal(newUser);
        } else {
            this.main.newUserRemote(newUser);
        }
    }
}
