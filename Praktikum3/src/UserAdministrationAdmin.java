import java.io.*;
import java.util.ArrayList;

public class UserAdministrationAdmin implements UserAdministration {
    /**
     * Eine Liste zur Datenhaltung, in der die Benuzter gespeichert werden.
     */
    private ArrayList<User> userList;
    private boolean isInitialized = false;

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
     * @throws DBNotInitializedException wird geworfen, falls die DB nicht initialisiert ist
     */
    @Override
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
    @Override
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

        deserialize();

        if(!userList.contains(user)) {
            throw new UserDoesNotExistException("User does not exist!");
        }

        userList.remove(user);
        updateDB(userList);
    }

    /**
     * @see UserAdministration
     * @param userList die neue Benutzerliste, die in die Datenbank geschrieben
     *                werden soll
     * @throws IOException
     * @throws DBNotInitializedException wird geworfen, falls die DB nicht initialisiert ist
     */
    public void updateDB(ArrayList<User> userList) throws IOException, DBNotInitializedException {
        if (!isInitialized) {
            throw new DBNotInitializedException("Database is not initialized");
        }

        FileOutputStream fos = new FileOutputStream("UserDB.s");
        ObjectOutputStream os = new ObjectOutputStream(fos);

        os.writeObject(userList);

        os.close();
    }

    /**
     * @see UserAdministration
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DBNotInitializedException wird geworfen, falls die DB nicht initialisiert ist
     */
    public ArrayList<User> deserialize() throws IOException, ClassNotFoundException, DBNotInitializedException {
        if (!isInitialized) {
            throw new DBNotInitializedException("Database is not initialized");
        }

        FileInputStream fis = new FileInputStream("UserDB.s");
        ObjectInputStream is = new ObjectInputStream(fis);

        userList = (ArrayList<User>) is.readObject();
        is.close();

        return userList;
    }

    /**
     * @see UserAdministration
     * @throws IOException
     */
    @Override
    public void initializeDB() throws IOException {
        userList = new ArrayList<User>();

        FileOutputStream fos = new FileOutputStream("UserDB.s");
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

/**
 * Eigene Exception, die geworfen wird, wenn versucht wird einen User mit einem
 * leeren Passwort oder einer leeren UserID zur User-Liste hinzuzufuegen.
 */
class UserException extends Exception {
    UserException(String output) {
        super(output);
    }
}

/**
 * Eigene Exception, die geworfen wird, wenn versucht wird einen Benutzer zu loeschen,
 * der gar nicht in der Benutzer-Liste vorhanden ist.
 */
class UserDoesNotExistException extends Exception {
    UserDoesNotExistException(String output) {
        super(output);
    }
}

/**
 * Eigene Exception, die geworfen wird, wenn versucht wird auf eine nicht initialisierte
 * Datenbank zuzugreifen.
 */
class DBNotInitializedException extends Exception {
    DBNotInitializedException(String output) {
        super(output);
    }
}
