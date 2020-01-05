package prak5client;

import prak5gemklassen.User;
import prak5gemklassen.UserException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientOrb {
    private String address;
    private int port = 1200;
    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientOrb (String address) {
        this.address = address;
    }

    private void Connect() {
        try {
            socket = new Socket(address, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Disconnect() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean sendReceive(int task, Object value) {
        boolean result = false;
        try {
            Connect();

            out.write(task);
            out.writeObject(value);
            out.flush();

            result = in.readBoolean();
        } catch (Exception e) {
            return false;
        }

        Disconnect();

        return result;
    }

    public void addUser(User user) throws UserException {
        if (!sendReceive(2, user)) {
            throw new UserException("User already exists");
        }
    }

    public boolean userOk(User user) {
        return sendReceive(3, user);
    }
}
