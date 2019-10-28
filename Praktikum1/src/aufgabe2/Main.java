package aufgabe2;

import aufgabe2.KreisVererb;

public class Main {
    public static void main(String[] args) {
        Point point = new Point(1,1);

        System.out.println(point.getLocation().toString());

        KreisVererb kreis = new KreisVererb(5, 1, 1);
        KreisVererb kreis2 = new KreisVererb(5, 1, 1);

        if(kreis.equals(kreis2)) {
            System.out.println("Die beiden Kreise sind gleich");
        } else {
            System.out.println("Die beiden Kreise sind nicht gleich");
        }

        System.out.println(kreis.getLocation());

        kreis.move(2, 3);

        System.out.println("Location nach move:" + kreis.getLocation());
    }
}
