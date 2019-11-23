public class Main {
    public static void main(String[] args) {
        User u1 = new User("", "012345".toCharArray());
        User u2 = new User("00000", "543210".toCharArray());
        User u3 = new User("00001", "".toCharArray());
        User u4 = new User("00000", "543210".toCharArray());

        UserAdministrationAdmin admin = new UserAdministrationAdmin();

        if (u1.equals(u2)) {
            System.out.println("u1 equals u2.");
        } else {
            System.out.println("u1 does not equal u2");
        }

        try {
            admin.addUser(u1);

        } catch (EmptyUserIDorPasswordException e) {
            System.out.println("failed to add user: " + u1.toString());
            e.printStackTrace();
        }

        try {
            admin.addUser(u3);
        } catch (EmptyUserIDorPasswordException e) {
            System.out.println("failed to add user: " + u3.toString());
            e.printStackTrace();
        }

//        try {
//            admin.addUser(u2);
//        } catch (EmptyUserIDorPasswordException e) {
//            System.out.println("failed to add User: " + u2.toString());
//            e.printStackTrace();
//        }

        try {
            if(admin.userOK(u2)) {
                admin.deleteUser(u2);
            } else {
                System.out.println("user does not exist");
            }
        } catch (UserDoesNotExistException e) {
            System.out.println("failed to delete user: " + u2.toString());
            e.printStackTrace();
        }

        try {
            admin.deleteUser(u2);
        } catch (UserDoesNotExistException e) {
            System.out.println("failed to delete user: " + u2.toString());
            e.printStackTrace();
        }

    }
}
