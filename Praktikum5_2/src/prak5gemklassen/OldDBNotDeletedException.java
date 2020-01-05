package prak5gemklassen;

/**
 * Eigene Exception, die geworfen wird, wenn das Loeschen der veralteten DB fehlgeschlagen ist.
 */
public class OldDBNotDeletedException extends Exception {
    public OldDBNotDeletedException(String output) {
        super(output);
    }
}
