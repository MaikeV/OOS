public class Fakultaet {
    public static void main(String[] args) {
        long j = 1;

        for (int i = 2; i <= 40; i++) {
            j = i * j;
        }

        System.out.println(j);
    }
}
