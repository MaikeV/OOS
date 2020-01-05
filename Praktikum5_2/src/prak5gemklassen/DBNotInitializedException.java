package prak5gemklassen;

/**
 * Eigene Exception, die geworfen wird, wenn versucht wird auf eine nicht initialisierte
 * Datenbank zuzugreifen.
 */
public class DBNotInitializedException extends Exception {
    public DBNotInitializedException(String output) {
        super(output);
    }
}

