import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserAdministrationAdminTest {
    private UserAdministrationAdmin admin;
    private User u1, u2, u3, u4, u5;

    @Before
    public void setUp() throws Exception {
        admin = new UserAdministrationAdmin();

        u1 = new User("user", "123".toCharArray());
        u2 = new User("user", "123".toCharArray());
        u3 = new User("user", "456".toCharArray());
        u4 = new User("", "123".toCharArray());
        u5 = new User("user1", "".toCharArray());
    }

    @After
    public void tearDown() throws Exception {}

    /*
     * Testen der AddUser Methode: Testen des Falles, wenn ein Benutzer bereits
     * existiert.
     */
    @Test
    public void testUserAlreadyExists() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u1);
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("u1 should be added without problems");
        }

        try {
            admin.addUser(u2);
            fail("u2 already exists");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der AddUser Methode: Testen des Falles, wenn es bereits einen
     * User mit dem angegeben Benutzername gibt
     */
    @Test
    public void testUsernameAlreadyInUse() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u2);
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("u2 should be added without problems");
        }

        try {
            admin.addUser(u3);
            fail("username of u3 already in use");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der addUser Methode: Testen des Falles, wenn keine UserID
     * angegeben wurde.
     */
    @Test
    public void testUserIdEmpty() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u4);
            fail("userId of u4 must not be empty");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der addUser Methode: Testen des Falles, wenn kein Passwort
     * angegeben wurde.
     */
    @Test
    public void testPasswordEmpty() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u5);
            fail("password of u5 must not be empty");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der addUser Methode, wenn die DB nicht initialisiert wurde
     */
    @Test
    public void testAddUserDBNotInit() {
        try {
            admin.addUser(u1);
            fail("DB not initialized");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der deleteUser Methode, fuer den Fall, dass der Benutzer existiert, aber die DB nicht initialisiert wurde
     */
    @Test
    public void testDeleteExistingUserDBNotInit() {
        try {
            admin.addUser(u1);
            fail("DB not initialized");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }

        try {
            admin.deleteUser(u1);
            fail("DB not initialized");
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der deleteUser Methode fuer den Fall, dass der User existiert
     */
    @Test
    public void testDeleteExistingUser() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u1);
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("u6 should be added without problems");
        }

        try {
            admin.deleteUser(u1);
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("u6 should be deleted without problems");
        }
    }

    /*
     * Testen der deleteUser Methode fuer den Fall, dass der User nicht existiert.
     */
    @Test
    public void testDeleteNotExistingDBInit() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
        }

        try {
            admin.deleteUser(u1);
            fail("u7 is not in the list");
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der deleteUser Methode fuer den Fall, dass der User nicht existiert und die DB nicht initialisiert wurde.
     */
    @Test
    public void testDeleteNotExistingDBNotInit() {
        try {
            admin.deleteUser(u1);
            fail("DB not initialized");
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der initializeDB Methode, fuer den Fall, dass ein altes DB file existiert.
     */
    @Test
    public void testInitializeDBOldFileExists() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without problems");
        }

        File tempFile = new File("UserDB.s");
        boolean exists = tempFile.exists();
        Assert.assertTrue(exists);
    }

    /*
     * Testen der initializeDB Methode fuer den Fall, dass kein altes DB file existiert.
     */
    @Test
    public void testInitializeDBOldFileDoesNotExists() {
        File tempFile = new File("UserDB.s");
        boolean exists = tempFile.exists();
        if (exists && tempFile.delete()) {
            try {
                admin.initializeDB();
            } catch (IOException | OldDBNotDeletedException e) {
                e.printStackTrace();
                fail("DB should be initialized without problems");
            }
        } else {
            fail("failed to delete old DB file");
        }

        exists = tempFile.exists();
        Assert.assertTrue(exists);
    }

    /*
     * Testen der deserialize Methde
     */
    @Test
    public void testDeserialize() {
        ArrayList<User> users1 = new ArrayList<User>();
        ArrayList<User> users2 = new ArrayList<User>();

        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without problems");
        }

        users1.add(users1.size(), u1);

        try {
            admin.updateDB(users1);
        } catch (IOException | DBNotInitializedException e) {
            e.printStackTrace();
        }

        try {
            users2 = admin.deserialize();
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("DB should be deserialized without problems");
        }

        assertEquals(users1, users2);
    }

    /*
     * Testen der deserialize Methode fuer den Fall, dass die DB nicht initialisiert wurde.
     */
    @Test
    public void testDeserializeDBNotInit() {
        try {
            admin.deserialize();
            fail("DB not initialized");
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der updateDB Methode
     */
    @Test
    public void testUpdateDB() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without problems");
        }

        ArrayList<User> users = new ArrayList<User>();
        users.add(users.size(), u1);

        try {
            admin.updateDB(users);
        } catch (IOException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("DB should be updated without problems");
        }
    }

    /*
     * Testen der updateDB Methode fuer den Fall, dass die DB nicht initialisiert wurde.
     */
    @Test
    public void testUpdateDBNotInit() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(users.size(), u1);

        try {
            admin.updateDB(users);
            fail("DB not initialized");
        } catch (IOException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der userOk Methode fuer den Fall, dass der User OK ist
     */
    @Test
    public void testUserOKDBInit() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without Problems");
        }

        try {
            admin.addUser(u1);
        } catch (IOException | UserException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("u1 should be added without problems");
        }

        try {
            Assert.assertTrue(admin.userOK(u1));
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("u1 should be OK");
        }
    }

    /*
     * Testen der userOK Methode fuer den Fall, dass die DB nicht initialisiert wurde, aber der User OK ist.
     */
    @Test
    public void testUserOKDBNotInit() {
        try {
            admin.addUser(u1);
            fail("DB not initialized");
        } catch (IOException | UserException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }

        try {
            Assert.assertTrue(admin.userOK(u1));
            fail("DB not initialized");
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der userOK Methode fuer den Fall, dass der User nicht OK ist.
     */
    @Test
    public void testUserNotOKDBInit() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            fail("DB should be initialized without Problems");
        }

        try {
            Assert.assertFalse(admin.userOK(u1));
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Testen der userOK Methode fuer den Fall, dass der User nicht OK ist und die DB nicht initialisiert wurde.
     */
    @Test
    public void testUserNotOKDBNotInit() {
        try {
            Assert.assertFalse(admin.userOK(u1));
            fail("DB not initialized");
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

}
