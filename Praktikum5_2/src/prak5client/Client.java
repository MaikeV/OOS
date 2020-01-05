package prak5client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prak5gemklassen.User;
import prak5gemklassen.UserAdministrationAdmin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static javafx.application.Application.launch;

public class Client extends Application {

    private Stage primaryStage;
    private UserAdministrationAdmin admin;
    private String address = "127.0.0.1";
    private ClientOrb clientOrb;
    boolean local = true;

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        admin = new UserAdministrationAdmin("DBlocal");
        int initializeDB = -1;

        while (initializeDB != 0 || initializeDB != 1) {
            System.out.println("Datenbank initialisieren? - 0/1");
            BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
            initializeDB = Integer.parseInt(din.readLine());

            if (initializeDB == 1) {
                admin.initializeDB();
                break;
            }
        }

        setScene("login");
    }

    private void setScene(String scene) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scene + ".fxml"));
        Parent root;

        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        switch (scene) {
            case "login":
                ((LoginController)fxmlLoader.getController()).setMainApplication(this);
                local = true;
                primaryStage.setTitle("Login");
                break;
            case "registration":
                ((RegistrationController)fxmlLoader.getController()).setMainApplication(this);
                primaryStage.setTitle("Registrierung");
                break;
            case "application":
                primaryStage.setTitle("Anwendung");
                break;
        }

        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    public void newUserLocal(User user) {
        try {
            admin.addUser(user);
            setScene("login");
        } catch(Exception e) {
            System.err.print("Fehler beim Eintragen des Benutzers: " + e.getMessage());
            return;
        }

        setScene("login");
    }

    public void newUserRemote(User user) {
        try {
            clientOrb.addUser(user);
            setScene("login");
        } catch(Exception e) {
            System.err.print("Fehler beim Eintragen des Benutzers: " + e.getMessage());
            return;
        }

        setScene("login");
    }

    public void registerLocal() {
        setScene("registration");
    }
    public void registerRemote() {
        setScene("registration");
    }

    public boolean loginLocal(User user) {
        local = true;

        try {
            if (admin.userOK(user)) {
                setScene("application");
//                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean loginRemote(User user) {
        local = false;

        try {
            if(clientOrb.userOk(user)) {
                setScene("application");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

