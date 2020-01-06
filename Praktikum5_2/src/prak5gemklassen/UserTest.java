package prak5gemklassen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import prak5gemklassen.User;

public class UserTest {
    private User u1, u2, u3;

    @Before
    public void setUp() throws Exception {

        u1 = new User("user", "123456".toCharArray());
        u2 = new User("user", "123456".toCharArray());
        u3 = new User("user2", "654321".toCharArray());
    }

    @After
    public void tearDown() throws Exception {
    }

    /*
     * Testen der Equals Methode
     */
    @Test
    public void testEquals() {
        Assert.assertEquals(u1, u2);
        Assert.assertNotEquals(u2, u3);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals(u1.userId, "user");
        System.out.println("123456".toCharArray());
        Assert.assertArrayEquals(u1.password, "123456".toCharArray());
    }
}
