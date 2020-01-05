package prak5server;

import prak5gemklassen.DBNotInitializedException;
import prak5gemklassen.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface zur UserAdministration, hier werden die Methoden definiert, die zur
 * Administration der Benuzter notwendig sind.
 */
public interface UserAdministration {
    /**
     * <p>Definition der Methode addUser. Wird in UserAdministrationAdmin implementiert.</p>
     *
     * @param user Benutzer, der hinzugefuegt werden soll.
     * @throws prak5gemklassen.UserException wird geworfen, wenn entweder der Wert fuer
     *                       UserID oder der Wert fuer Password leer ist.
     * @throws IOException
     */
    void addUser(User user) throws prak5gemklassen.UserException, IOException, ClassNotFoundException, DBNotInitializedException;

    /**
     * <p>Definition der Methode userOK. Wird in UserAdministrationAdmin implementiert.</p>
     *
     * @param user Benutzer, der ueberpruft werden soll.
     * @return true, wenn Benutzer in Liste vorhanden, sonst false.
     * @throws IOException
     * @throws ClassNotFoundException
     *
     */
    boolean userOK(User user) throws IOException, ClassNotFoundException, DBNotInitializedException;

    /**
     * <p>Legt eine neue leere Benutzerliste an, serialisiert diese und
     * legt sie im Dateisystem ab.</p>
     *
     * @see UserAdministrationAdmin initializeDB
     */
    //void initializeDB() throws IOException, OldDBNotDeletedException;

    /**
     * <p>Liest die Benutzerliste aus der DB aus und deserialisiert sie</p>
     *
     * @return Gibt die aus der Datei geladene Liste der Benutzer zurueck
     * @throws IOException
     * @throws ClassNotFoundException
     *
     */
    ArrayList<User> deserialize() throws IOException, ClassNotFoundException, DBNotInitializedException;

    /**
     * <p>Updatet das DB-File mit der neuen Liste</p>
     * @param userList die neue Benutzerliste, die in die Datenbank geschrieben
     *                werden soll
     * @throws IOException
     *
     */
    void updateDB(ArrayList<User> userList) throws IOException, DBNotInitializedException;
}