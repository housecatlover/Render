
/**
 * Stores the data of a vertex in the object
 *
 * @Alex Schwartz
 * @j8 11/15/2022
 */
public class Vertex
{
    private double x;
    private double y;
    private double z;

    private Coordinate screenLocation;
    

    /**
     * Constructor for objects of class Vertex
     */
    public Vertex(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        screenLocation = new Coordinate(0,0);
    }

    public Vertex(Vertex source){
        this.x = source.getX();
        this.y = source.getY();
        this.z = source.getZ();
        screenLocation = new Coordinate(0,0);
    }

    /**
     * Rotates the location of vertex.
     *
     * @param  rotationMatrix the matrix used to rotate the location.
     */
    public void rotate(double[][] rotationMatrix)
    {
        x = x * rotationMatrix[0][0] + y * rotationMatrix[0][1] + z * rotationMatrix[0][2];
        y = x * rotationMatrix[1][0] + y * rotationMatrix[1][1] + z * rotationMatrix[1][2];
        z = x  * rotationMatrix[2][0] + y * rotationMatrix[2][1] + z * rotationMatrix[2][2];
    }

    /**
     * Displaces the location of the vertex
     * @param displacementVector the x, y, and z displacement (in that order) of the vertex
     */
    public void displace(Vector displacementVector){
        x += displacementVector.getX();;
        y += displacementVector.getY();
        z += displacementVector.getZ();
    }

    public Vertex simulateDisplacement(Vector displacementVector){
        Vertex copy = new Vertex(this);
        copy.displace(displacementVector);
        return copy;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ() {
        return z;
    }

    /**
     * Tells the vertex to generate its new coordinate location on the 2D screen
     * @param camera the location of the camera as a vertex
     */
    public void updateCoordinate(Vertex camera){
        double dispX = camera.getX() - x;
        double dispY = camera.getY() - y;
        double dispZ = camera.getZ() - z;
        double vertexFromScreen = (camera.getZ() - Renderer.FOCAL_LENGTH) - z; //cameraZ - focalLength only for a static camera
        double steps = vertexFromScreen / dispZ;
        // ^ the proportion of the distance the line travels to intersect the screen
        screenLocation.setX(dispX*(steps-1));//x + dispX*steps - camera.getX()
        screenLocation.setY(dispY*(steps-1));//y + dispY*steps - camera.getY()
        //how far the perspective offsets, - the movement of the camera so the plane moves with the camera
    }


    public void updateCoordinate(Camera camera){
        Vector disp = new Vector(camera.getLocation().getX() - x,
        camera.getLocation().getY() - y,
        camera.getLocation().getZ() - z);

        Vertex intersection = findIntersection(camera, disp);
        Vector h = camera.getHorizontal();
        Vector horizontalProjection = h.multiply(h.dotProduct(disp) / (h.getMagnitude()*disp.getMagnitude()) );
        double width = 1;
        double height = 1;
        screenLocation.setX(horizontalProjection.getX() * width); //TODO the proportion of how wide I want to view
        screenLocation.setY(disp.minus(horizontalProjection).getY() * height);
    }
    /*
     * @returns the point at which a line between this vertex and the camera intersects then camera's plane
     */
    private Vertex findIntersection(Camera camera, Vector disp){
        Plane plane = camera.getPlane();
        double sum = plane.getSum();
        sum -= plane.getNormal().dotProduct(new Vector(x, y, z));//see comment below for math theory
        double speed = plane.getX() * disp.getX() + plane.getY() * disp.getY() + plane.getZ() * disp.getZ();
        // ^ t coeffient
        double steps = sum / speed; //less than 1 by how we found it, but works all the same
        return simulateDisplacement(disp.multiply(steps));
        /*
         * using the vertex to camera as a vector form line, solve for the intersection between the line and
         * the camera's plane as follows:
         * plane Coefficient * (line origin + line coefficient * t) //for x y and z
         * = the plane's sum, distribute, subtract the constants, sum the coefficients of t, then divide for:
         * t = (sum - forEach{plane coefficient * line location} ) / forEach{plane coefficient * line coefficient}
         */
    }

    public Coordinate getScreenLocation(){
        return screenLocation;
    }

    public String toString(){
        return "x: 0." + (int) (x * 1000) + " y: 0." + (int) (y * 1000) + " z: 0." + (int) (z*1000);
    }
}