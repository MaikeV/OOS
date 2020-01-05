package prak5gemklassen;

/**
 * Eigene Exception, die geworfen wird, wenn versucht wird einen User mit einem
 * leeren Passwort oder einer leeren UserID zur User-Liste hinzuzufuegen.
 */
public class UserException extends Exception {
    public UserException(String output) {
        super(output);
    }
}

