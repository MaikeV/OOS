package prak5gemklassen;

/**
 * Eigene Exception, die geworfen wird, wenn versucht wird einen Benutzer zu loeschen,
 * der gar nicht in der Benutzer-Liste vorhanden ist.
 */
public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String output) {
        super(output);
    }
}

