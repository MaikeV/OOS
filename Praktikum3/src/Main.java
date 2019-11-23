import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UserAdministrationAdmin admin = new UserAdministrationAdmin();
        User u1 = new User("user1", "123456".toCharArray());
        User u2 = new User("user2", "654321".toCharArray());
        User u3 = new User("user3", "987654".toCharArray());

//        try {
//            admin.initializeDB();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            admin.addUser(u1);
        } catch (UserException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            admin.addUser(u3);
        } catch (UserException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            admin.addUser(u2);
        } catch (UserException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            admin.deleteUser(u2);
        } catch (UserDoesNotExistException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if (admin.userOK(u2)) System.out.println("u1 OK");
            if (admin.userOK(u1)) System.out.println("u2 OK");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(admin.printDB());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
