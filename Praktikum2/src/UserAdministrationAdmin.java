import java.util.ArrayList;

public class UserAdministrationAdmin implements UserAdministration {
    /**
     * Eine Liste zur Datenhaltung, in der die Benuzter gespeichert werden.
     */
    private ArrayList<User> users = new ArrayList<User>();

    /**
     * <p>Diese Methode ist dafuer zustaendig, dass neue Benutzer angelegt werden koennen.
     * Es wird ueberprueft, ob sowohl ID als auch Passwort gesetzt wurden.
     * Ist dies nicht der Fall, wird eine Exception geworfen. Wurden beide
     * Werte gesetzt, wird der neue Benutzer zur User-Liste hinzugefuegt.</p>
     * @param user Der Benutzer, der hinzugefuegt werden soll.
     * @throws EmptyUserIDorPasswordException wird geworfen, wenn entweder die UserID
     * oder das Passwort nicht gesetzt wurde.
     */
    @Override
    public void addUser(User user) throws EmptyUserIDorPasswordException {
        if(user.userId.equals("")) {
            throw new EmptyUserIDorPasswordException("UserID cannot be empty");
        }

        if(user.password.length == 0) {
            throw new EmptyUserIDorPasswordException("Password cannot be empty");
        }

        User newUser = new User(user.userId, user.password);
        users.add(users.size(), newUser);
    }

    /**
     * Diese Methode ueberprueft, ob ein Benutzer in der Liste vorhanden ist.
     * @param user Benutzer nach dem in der Liste gesucht werden soll.
     * @return true, wenn Benutzer vorhanden, sonst false.
     */
    @Override
    public boolean userOK(User user) {
        return users.contains(user);
    }

    /**
     * <p>Diese Methode ist dafuer zustaendig, Benutzer zu loeschen.
     * Ist der angegebene User nicht in der Liste enthalten, wird eine Exception geworfen.</p>
     * @param user Benutzer der aus der Liste entfernt werden soll.
     * @throws UserDoesNotExistException wird geworfen, wenn der zu loeschende User nicht
     * in der Liste vorhanden ist.
     */
    public void deleteUser(User user) throws UserDoesNotExistException {
        if(!users.contains(user)) {
            throw new UserDoesNotExistException("User does not exist!");
        }

        users.remove(user);
    }
}

/**
 * Eigene Exception, die geworfen wird, wenn versucht wird einen User mit einem
 * leeren Passwort oder einer leeren UserID zur User-Liste hinzuzufuegen.
 */
class EmptyUserIDorPasswordException extends Exception {
    EmptyUserIDorPasswordException(String output) {
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
