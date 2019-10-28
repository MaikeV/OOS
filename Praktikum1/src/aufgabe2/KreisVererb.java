package aufgabe2;

public class KreisVererb extends Point {
    private int radius;

    KreisVererb() {
        super();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    KreisVererb(int radius, int x, int y) {
        super(x, y);
        this.radius = radius;
    }

    boolean equals(KreisVererb k) {
        return this.getLocation().equals(k.getLocation()) && this.radius == k.radius;
    }

    @Override
    public String toString() {
        return super.toString() + " Radius: " + this.radius;
    }
}
