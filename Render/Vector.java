import java.lang.*;

public class Vector {
    private double x;
    private double y;
    private double z;

    /**
     * Constructs a vector with the given double components
     *
     * @param x the x component
     * @param y the y component
     * @param z the z component
     */
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }

    /**
     * Multiplies this Vector by another component wise, then sums those products
     *
     * @param other the other Vector being dotProduct multiplied by this
     * @return the resulting Scalar
     */
    public double dotProduct(Vector other) {
        return x * other.getX() + y * other.getY() + z * other.getZ();
    }

    /**
     * Generates the Vector normal to this and other
     *
     * @param other the other Vector being crossProduct
     * @return the resulting Vector
     */
    public Vector crossProduct(Vector other) {
        double newX = y * other.getZ() - z * other.getY();
        double newY = z * other.getX() - x * other.getZ();
        double newZ = x * other.getY() - y * other.getX();
        return new Vector(newX, newY, newZ);
    }

    /**
     * Multiplies the components of a Vector by a scalar
     *
     * @param other the scalar being multiplied
     * @return the resulting Vector
     */
    public Vector multiply(double other) {
        return new Vector(x * other, y * other, z * other);
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Adds two Vectors component wise
     *
     * @param other the other Vector being added with this
     * @return the resulting Vector
     */
    public Vector add(Vector other) {
        return new Vector(x + other.getX(), y + other.getY(), z + other.getZ());
    }

    public Vector minus(Vector other){
        return add(other.multiply(-1));
    }

    /**
     * Creates the unit Vector, a Vector with the same direction but a magnitude of 1
     *
     * @return the resulting unit Vector
     * @throws IllegalStateException if the magnitude of the Vector is 0
     */
    public Vector normalize() {
        double magnitude = getMagnitude();
        if (
                magnitude < 0.01) {
            throw new IllegalStateException("Magnitude is 0"); //effectively == 0 with doubles
        }


        return new Vector(x / magnitude, y / magnitude, z / magnitude);
    }

    /**
     * Calculates the angle between two Vectors
     *
     * @param other the other vector being related to this
     * @return the angle between the vectors as a double
     */
    public double angleBetween(Vector other) {
        return Math.acos(dotProduct(other) / (getMagnitude() * other.getMagnitude()));
        //arc cos [(a dot b) / (|a| * |b|) ]
    }

    /**
     * Compares this to another object to determine if they are both Vectors with
     * equivalent components (with a tolerance of 1/1000)
     * @param o the other object being considered
     * @return whether the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        Vector other = (Vector) o; //this will throw the correct error anyway, so casting is fine
        if (Math.abs(x - other.getX()) > .001
                || Math.abs(y - other.getY()) > .001
                || Math.abs(x - other.getX()) > .001
        ) return false;
        return true;
    }
}