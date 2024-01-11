public class Camera {
    private Vertex location; //centered and in front
    private double angleX; //the horizontal rotation of the camera
    private double angleY; //the vertical rotation of the camera
    private Plane plane;

    private Vector horizontal;
    public Camera(Vertex location){
        this.location = location;
        rotate(0, 0);
    }

    public void displace(Vector displacementVector){
        location.displace(displacementVector);
    }

    public Vertex getLocation(){
        return location;
    }

    /*
        identify the new screen location, it's a plane with the normal vector defined by X & Y angles
        displaced renderer.FOCAL_LENGTH along that same vector
     */
    public void rotate(double x, double y){
        angleX = inside2Pi(angleX + x);
        angleY = inside2Pi(angleY + y);
        //assume Z to be 1, then * the vector by a scalar to have the length==focal length later
        double Xcoefficient = Math.tan(angleX); //how many units of z the x is displaced
        double Ycoefficient = Math.tan(angleY);
        double distance = Renderer.distance(Xcoefficient, Ycoefficient, 1);//Zcoefficient starts as 1
        double Zcoefficient = Renderer.FOCAL_LENGTH/distance; // (focal length / distance) * Zcoefficient
        Xcoefficient *= Zcoefficient; // (focal length / distance) * Xcoefficient
        Ycoefficient *= Zcoefficient; // (focal length / distance) * Ycoefficient

        Vector displacement = new Vector (Xcoefficient, Ycoefficient, Zcoefficient);

        plane = new Plane(Xcoefficient, Ycoefficient, Zcoefficient, location.simulateDisplacement(displacement));

        findHorizontal();
    }

    /*
     * generates a "horizontal" line across the screen in 3d space as a vector to be projected onto
     */
    private void findHorizontal(){
        horizontal = new Vector(1, 0, plane.getX()/plane.getZ() * -1);
    }

    public Vector getHorizontal() {
        return horizontal;
    }

    private double inside2Pi(double input){
        if (input > Math.PI*2){
            return input%(Math.PI*2);
        }
        else if (input < 0){
            return input+(Math.PI*2);
        }
        else return input;
    }

    public Plane getPlane(){
        return plane;
    }
}