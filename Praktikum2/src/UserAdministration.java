/**
 * Interface zur UserAdministration, hier werden die Methoden definiert, die zur
 * Administration der Benuzter notwendig sind.
 */
public interface UserAdministration {
    /**
     * <p>Definition der Methode addUser. Wird in UserAdministrationAdmin implementiert.</p>
     * @see UserAdministrationAdmin addUser
     * @param user Benutzer, der hinzugefuegt werden soll.
     * @throws EmptyUserIDorPasswordException wird geworfen, wenn entweder der Wert fuer
     * UserID oder der Wert fuer Password leer ist.
     */
    void addUser(User user) throws EmptyUserIDorPasswordException;

    /**
     * <p>Definition der Methode userOK. Wird in UserAdministrationAdmin implementiert.</p>
     * @see UserAdministrationAdmin userOK
     * @param user Benutzer, der ueberpruft werden soll.
     * @return true, wenn Benutzer in Liste vorhanden, sonst false.
     */
    boolean userOK(User user);
}
