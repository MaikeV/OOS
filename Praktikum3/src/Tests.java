import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserTest.class, UserAdministrationAdminTest.class})
public class Tests {
    public static void main(String[] args) {
        JUnitCore.runClasses(Tests.class);
    }
}
