public class Plane {
    private Vector normal;
    //These make up the plane's normal vector, 1 instance of it gets the camera to the plane
    private double planeSum;

    public Plane(Vector vector, double sum){
        planeSum = sum;
    }

    public Plane(double x, double y, double z, Vertex point){
        normal = new Vector(x, y, z);
        planeSum = -point.getX() * x - point.getY() * y - point.getZ() * z;
    }

    public double getX() {
        return normal.getX();
    }

    public double getY() {
        return normal.getY();
    }

    public double getZ() {
        return normal.getZ();
    }

    public double getSum() {
        return planeSum;
    }

    public Vector getNormal() {
        return normal;
    }
}