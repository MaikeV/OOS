package aufgabe3;

public class Main {

    public static void main(String[] args) {
        Geometry geo[];
        geo = new Geometry[4];
        KreisAgg k1 = new KreisAgg(1, 1, 1);
        KreisAgg k2 = new KreisAgg(0, 0, 2);
        Rechteck r1 = new Rechteck(0, 0, 2, 1);
        Rechteck r2 = new Rechteck(1, 1, 2, 2);

        geo[0] = k1;
        geo[1] = k2;
        geo[2] = r1;
        geo[3] = r2;

        double area = 0;

        for(int i = 0; i < 4; i++) {
            area += geo[i].area();
        }

        System.out.println("Gesamtflaecheninhalt: " + area);
    }
}
