package prak5server;

import prak5gemklassen.UserAdministrationAdmin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server {
    UserAdministrationAdmin admin;
    ServerOrb serverOrb;

    Server() {
        admin = new UserAdministrationAdmin("remoteDB");
        int initializeDB = -1;

        while(initializeDB != 0 || initializeDB != 1) {
            System.out.println("Datenbank initialisieren? - 0/1");

            BufferedReader din =  new BufferedReader(new InputStreamReader(System.in));

            try {
                initializeDB = Integer.parseInt(din.readLine());

                if (initializeDB == 1) {
                    admin.initializeDB();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        serverOrb = new ServerOrb(admin);
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
