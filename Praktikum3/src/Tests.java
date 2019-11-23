import junit.framework.TestSuite;
import junit.framework.Test;

public class Tests {
    public static Test suite() {
        TestSuite suite =  new TestSuite("Tests Praktikum 3");
        suite.addTestSuite(UserTest.class);
        suite.addTestSuite(UserAdministrationAdminTest.class);

        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(Tests.suite());
    }
}
