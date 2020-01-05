package prak5server;

import prak5gemklassen.User;
import prak5gemklassen.UserAdministrationAdmin;
import prak5gemklassen.UserException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOrb {
    private ServerSocket serverSocket;
    private UserAdministrationAdmin admin;

    public ServerOrb(UserAdministrationAdmin admin) {
        this.admin = admin;

        try {
            serverSocket = new ServerSocket(1200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while(true) {
            try {
                Socket client = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                int task = in.read();

                switch (task) {
                    case 2:
                        System.out.println("Neuer Benutzer: ");
                        User user = (User) in.readObject();
                        System.out.println(user.toString());

                        boolean result = true;

                        try {
                            admin.addUser(user);
                        } catch (UserException e) {
                            result = false;
                        }

                        out.writeBoolean(result);
                        out.flush();
                        break;
                    case 3:
                        System.out.println("User checken: ");
                        User user2 = (User) in.readObject();

                        System.out.println(user2.toString());

                        out.writeBoolean(admin.userOK(user2));
                        out.flush();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
