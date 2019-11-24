import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    /**
     * Testen der AddUser Methode: Testen des Falles, wenn ein Benutzer bereits
     * existiert.
     * @see User addUser
     */
    @Test
    public void testUserAlreadyExists() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u1);
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            Assert.fail("u1 should be added without problems");
        }

        try {
            admin.addUser(u2);
            Assert.fail("u2 already exists");
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
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u2);
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            Assert.fail("u2 should be added without problems");
        }

        try {
            admin.addUser(u3);
            Assert.fail("username of u3 already in use");
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
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u4);
            Assert.fail("userId of u4 must not be empty");
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
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u5);
            Assert.fail("password of u5 must not be empty");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUserDBNotInit() {
        try {
            admin.addUser(u1);
            Assert.fail("DB not initialized");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteExistingUserDBNotInit() {
        try {
            admin.addUser(u1);
            Assert.fail("DB not initialized");
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }

        try {
            admin.deleteUser(u1);
            Assert.fail("DB not initialized");
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteExistingUser() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without problems");
        }

        try {
            admin.addUser(u1);
        } catch (UserException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            Assert.fail("u6 should be added without problems");
        }

        try {
            admin.deleteUser(u1);
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            Assert.fail("u6 should be deleted without problems");
        }
    }

    @Test
    public void testDeleteNotExistingDBInit() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
        }

        try {
            admin.deleteUser(u1);
            Assert.fail("u7 is not in the list");
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteNotExistingDBNotInit() {
        try {
            admin.deleteUser(u1);
            Assert.fail("DB not initialized");
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitializeDBOldFileExists() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without problems");
        }

        File tempFile = new File("UserDB.s");
        boolean exists = tempFile.exists();
        Assert.assertTrue(exists);
    }

    @Test
    public void testInitializeDBOldFileDoesNotExists() {
        File tempFile = new File("UserDB.s");
        boolean exists = tempFile.exists();
        if (exists && tempFile.delete()) {
            try {
                admin.initializeDB();
            } catch (IOException | OldDBNotDeletedException e) {
                e.printStackTrace();
                Assert.fail("DB should be initialized without problems");
            }
        } else {
            Assert.fail("failed to delete old DB file");
        }

        exists = tempFile.exists();
        Assert.assertTrue(exists);
    }

    @Test
    public void testDeserialize() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without problems");
        }


    }

    @Test
    public void testDeserializeDBNotInit() {

    }

    @Test
    public void testUpdateDB() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without problems");
        }

        ArrayList<User> users = new ArrayList<User>();
        users.add(users.size(), u1);

        try {
            admin.updateDB(users);
        } catch (IOException | DBNotInitializedException e) {
            e.printStackTrace();
            Assert.fail("DB should be updated without problems");
        }
    }

    @Test
    public void testUpdateDBNotInit() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(users.size(), u1);

        try {
            admin.updateDB(users);
            Assert.fail("DB not initialized");
        } catch (IOException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserOKDBInit() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without Problems");
        }

        try {
            admin.addUser(u1);
        } catch (IOException | UserException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            Assert.fail("u1 should be added without problems");
        }

        try {
            Assert.assertTrue(admin.userOK(u1));
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
            Assert.fail("u1 should be OK");
        }
    }

    @Test
    public void testUserOKDBNotInit() {
        try {
            admin.addUser(u1);
            Assert.fail("DB not initialized");
        } catch (IOException | UserException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }

        try {
            Assert.assertTrue(admin.userOK(u1));
            Assert.fail("DB not initialized");
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserNotOKDBInit() {
        try {
            admin.initializeDB();
        } catch (IOException | OldDBNotDeletedException e) {
            e.printStackTrace();
            Assert.fail("DB should be initialized without Problems");
        }

        try {
            Assert.assertFalse(admin.userOK(u1));
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserNotOKDBNotInit() {
        try {
            Assert.assertFalse(admin.userOK(u1));
            Assert.fail("DB not initialized");
        } catch (IOException | ClassNotFoundException | DBNotInitializedException e) {
            e.printStackTrace();
        }
    }

}
