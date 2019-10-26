package aufgabe3;

public class Rechteck implements Geometry{
    private Point pointA;
    private Point pointB;

    Rechteck() {
        this.pointA = new Point(0, 0);
        this.pointB = new Point(2, 1);
    }

    Rechteck(Point pointA, Point pointB) {
        this.pointA = new Point(pointA);
        this.pointB = new Point(pointB);
    }

    Rechteck(int xA, int xB, int yA, int yB) {
        this.pointA = new Point(xA, yA);
        this.pointB = new Point(xB, yB);
    }

    boolean equals(Rechteck r) {
        return (this.pointA.equals(r.pointA) && this.pointB.equals(r.pointB)) ||
                (this.pointA.equals(r.pointB) && this.pointB.equals(r.pointA));
    }

    public double area() {
        int sideA = this.pointB.x - this.pointA.x;
        int sideB = this.pointB.y - this.pointA.x;

        if(sideA < 0) {
            sideA *= -1;
        }

        if(sideB < 0) {
            sideB *= -1;
        }

        return sideA * sideB;
    }

    @Override
    public Rechteck clone() {
        return new Rechteck(this.pointA, this.pointB);
    }

    @Override
    public String toString() {
        return "Obere Linke Ecke: " + this.pointA.toString() +
                "Untere Rechte Ecke: " + this.pointB.toString() +
                "Flaecheninhalt: " + this.area();
    }
}
