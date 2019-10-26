package aufgabe3;

public class KreisAgg implements Geometry{
    Point middle;
    int radius;

    KreisAgg() {
        this.middle = new Point(1, 1);
        this.radius = 1;
    }

    KreisAgg(Point middle, int radius) {
        this.middle = new Point(middle);
        this.radius = radius;
    }

    KreisAgg(int x, int y, int radius) {
        this.middle = new Point(x, y);
        this.radius = radius;
    }

    public double area() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    public boolean equals(KreisAgg k) {
        return this.middle.equals(k.middle) && this.radius == k.radius;
    }

    @Override
    public KreisAgg clone() {
        return new KreisAgg(this.middle, this.radius);
    }


    @Override
    public String toString() {
        return "Mittelpunkt: " + this.middle.toString()
                + "Radius: " + this.radius + "Flaecheninhalt: " + area();
    }
}
