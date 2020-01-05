package prak5gemklassen;

import prak5client.UserAdministration;
import prak5gemklassen.*;

import java.io.*;
import java.util.ArrayList;

public class UserAdministrationAdmin implements UserAdministration {
    /**
     * Eine Liste zur Datenhaltung, in der die Benuzter gespeichert werden.
     */
    private ArrayList<User> userList;
    private boolean isInitialized = false;
    private String dbName;

    public UserAdministrationAdmin(String name) {
        this.dbName = name;
    }

    /**
     * <p>Diese Methode ist dafuer zustaendig, dass neue Benutzer angelegt werden koennen.
     * Es wird ueberprueft, ob sowohl ID als auch Passwort gesetzt wurden.
     * Ist dies nicht der Fall, wird eine Exception geworfen. Wurden beide
     * Werte gesetzt, wird der neue Benutzer zur User-Liste hinzugefuegt.</p>
     * @param user Der Benutzer, der hinzugefuegt werden soll.
     * @throws UserException wird geworfen, wenn entweder die UserID
     * oder das Passwort nicht gesetzt wurde.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addUser(User user) throws UserException, IOException, ClassNotFoundException, DBNotInitializedException {
        if (!isInitialized) {
            throw new DBNotInitializedException("Database is not initialized");
        }

        deserialize();

        if(user.userId.equals("")) {
            throw new UserException("UserID cannot be empty");
        }

        if(user.password.length == 0) {
            throw new UserException("Password cannot be empty");
        }

        if(userList.contains(user)) {
            throw new UserException("User already exists");
        }

        for (User value : userList) {
            if (value.userId.equals(user.userId)) {
                throw new UserException("userId is already in use");
            }
        }

        User newUser = new User(user.userId, user.password);
        userList.add(userList.size(), newUser);

        updateDB(userList);
    }

    /**
     * Diese Methode ueberprueft, ob ein Benutzer in der Liste vorhanden ist.
     * @param user Benutzer nach dem in der Liste gesucht werden soll.
     * @return true, wenn Benutzer vorhanden, sonst false.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DBNotInitializedException wird geworfen, falls die DB nicht initialisiert ist
     */
    public boolean userOK(User user) throws IOException, ClassNotFoundException, DBNotInitializedException {
        if (!isInitialized) {
            throw new DBNotInitializedException("Database is not initialized");
        }

        deserialize();
        return userList.contains(user);
    }

    /**
     * <p>Diese Methode ist dafuer zustaendig, Benutzer zu loeschen.
     * Ist der angegebene User nicht in der Liste enthalten, wird eine Exception geworfen.</p>
     * @param user Benutzer der aus der Liste entfernt werden soll.
     * @throws UserDoesNotExistException wird geworfen, wenn der zu loeschende User nicht
     * in der Liste vorhanden ist.
     * @throws DBNotInitializedException wird geworfen, falls die DB nicht initialisiert ist
     */
    void deleteUser(User user) throws UserDoesNotExistException, IOException, ClassNotFoundException, DBNotInitializedException {
        if (!isInitialized) {
            throw new DBNotInitializedException("Database is not initialized");
        }

        userList = deserialize();

        if(!userList.contains(user)) {
            throw new UserDoesNotExistException("User does not exist!");
        }

        userList.remove(user);
        updateDB(userList);
    }

    /**
     * @param userList die neue Benutzerliste, die in die Datenbank geschrieben
     *                werden soll
     * @throws IOException
     * @throws DBNotInitializedException wird geworfen, falls die DB nicht initialisiert ist
     */
    public void updateDB(ArrayList<User> userList) throws IOException, DBNotInitializedException {
        if (!isInitialized) {
            throw new DBNotInitializedException("Database is not initialized");
        }

        FileOutputStream fos = new FileOutputStream(dbName + ".s");
        ObjectOutputStream os = new ObjectOutputStream(fos);

        os.writeObject(userList);

        os.close();
    }

    /**
     * @return gibt die Liste der in der DB gespeicherten Benutzer zurueck
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DBNotInitializedException wird geworfen, falls die DB nicht initialisiert ist
     */
    public ArrayList<User> deserialize() throws IOException, ClassNotFoundException, DBNotInitializedException {
        if (!isInitialized) {
            throw new DBNotInitializedException("Database is not initialized");
        }

        FileInputStream fis = new FileInputStream(dbName + ".s");
        ObjectInputStream is = new ObjectInputStream(fis);

        userList = (ArrayList<User>) is.readObject();
        is.close();

        return userList;
    }

    /**
     * @see UserAdministration
     * @throws IOException
     * @throws OldDBNotDeletedException wird geworfen, wenn die alte DB nicht geloescht werden konnte.
     */

    public void initializeDB() throws IOException, OldDBNotDeletedException {
        File file = new File(dbName + ".s");
        boolean exists = file.exists();
        System.out.println("AYOOO");
        if (exists && !file.delete()) {
            throw new OldDBNotDeletedException("failed to delete old DB");
        }

        userList = new ArrayList<User>();

        FileOutputStream fos = new FileOutputStream(dbName + ".s");
        ObjectOutputStream os = new ObjectOutputStream(fos);

        os.writeObject(userList);
        isInitialized = true;

        os.close();
    }

    /**
     * <p>Gibt den Inhalt der Datenbank als String zurueck</p>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DBNotInitializedException wird geworfen, falls die DB nicht initialisiert ist
     */
    String printDB() throws IOException, ClassNotFoundException, DBNotInitializedException {
        if (!isInitialized) {
            throw new DBNotInitializedException("Database is not initialized");
        }

        deserialize();
        StringBuilder output = new StringBuilder();

        for (User user: userList) {
            output.append(user.toString()).append(";\n");
        }

        return output.toString();
    }
}
