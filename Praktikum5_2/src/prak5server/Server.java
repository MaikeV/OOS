package prak5server;

import prak5gemklassen.OldDBNotDeletedException;
import prak5gemklassen.UserAdministrationAdmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Server {
    UserAdministrationAdmin admin;
    ServerOrb serverOrb;

    Server() {
        admin = new UserAdministrationAdmin("remoteDB");


        try {
            admin.initializeDB();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OldDBNotDeletedException e) {
            e.printStackTrace();
        }

        serverOrb = new ServerOrb(admin);
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
