package aufgabe3;

public class Point {
    int x;
    int y;

    Point() {
        this.x = 1;
        this.y = 1;
    }

    Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point getLocation() {
        return this;
    }

    void setLocation(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void move(int dx, int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }

    boolean equals(Point p) {
        return this.x == p.x && this.y == p.y;
    }

    public String toString() {
        return "(" + this.x + "; " + this.y + ")";
    }
}
