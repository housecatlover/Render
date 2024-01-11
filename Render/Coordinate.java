/*
 * Stores the location of a point in an x-y plane
 *
 * @Alex Schwartz
 * @j8 12/1/2022
 */
public class Coordinate {
    private double x;
    private double y;
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}