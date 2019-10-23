public class Point {
    private int x;
    private int y;

    Point() {}

    Point(Point p) {

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
}
