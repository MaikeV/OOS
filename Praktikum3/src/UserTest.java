import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class UserTest extends TestCase {
    private UserAdministrationAdmin admin;

    protected void setUp() throws Exception {
        super.setUp();

        admin = new UserAdministrationAdmin();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Testen der Equals Methode
     * @see User equals
     */
    @Test
    public void testEquals() {
        User u1 = new User("user", "123456".toCharArray());
        User u2 = new User("user", "123456".toCharArray());
        User u3 = new User("user2", "654321".toCharArray());

        assertEquals(u1, u2);
        assertNotEquals(u2, u3);
    }

    /**
     * Testen der AddUser Methode: Testen des Falles, wenn ein Benutzer bereits
     * existiert.
     * @see User addUser
     */
    @Test
    public void testUserAlreadyExists() {
        User u1 = new User("user", "123456".toCharArray());
        User u2 = new User("user", "123456".toCharArray());

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

    /**
     * Testen der AddUser Methode: Testen des Falles, wenn es bereits einen
     * User mit dem angegeben Benutzername gibt
     * @see User addUser
     */
    @Test
    public void testUsernameAlreadyInUse() {
        User u2 = new User("user", "654321".toCharArray());
        User u3 = new User("user", "987654".toCharArray());

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

    /**
     * Testen der addUser Methode: Testen des Falles, wenn keine UserID
     * angegeben wurde.
     * @see User addUser
     */
    @Test
    public void testUserIdEmpty() {
        User u4 = new User("", "654321".toCharArray());

        try {
            admin.addUser(u4);
            fail("userId of u4 must not be empty");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testen der addUser Methode: Testen des Falles, wenn kein Passwort
     * angegeben wurde.
     * @see User addUser
     */
    @Test
    public void testPasswordEmpty() {
        User u5 = new User("user5", "".toCharArray());

        try {
            admin.addUser(u5);
            fail("password of u5 must not be empty");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteExistingUser() {
        User u6 = new User("user", "123456".toCharArray());

        try {
            admin.addUser(u6);
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("u6 should be added without problems");
        }

        try {
            admin.deleteUser(u6);
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            fail("u6 should be deleted without problems");
        }
    }

    @Test
    public void testDeleteNotExisting() {
        User u7 = new User("user", "123456".toCharArray());

        try {
            admin.deleteUser(u7);
            fail("u7 is not in the list");
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserOK() {
        User u8 = new User("user", "123".toCharArray());

        try {
            admin.addUser(u8);
        } catch (IOException | UserException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }

        try {
            assertTrue(admin.userOK(u8));
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserNotOK() {
        User u9 = new User("", "123".toCharArray());

        try {
            assertFalse(admin.userOK(u9));
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitializeDB() {

    }

    @Test
    public void testUpdateDB() {

    }

    @Test
    public void testDeserialize() {

    }


}
