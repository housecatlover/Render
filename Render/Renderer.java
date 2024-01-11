import java.nio.charset.CoderResult;
import java.awt.event.InputEvent.*;

/*
 * Renders a predefined shape as a wireframe.
 *
 * @Alex Schwartz
 * @j8 11/15/2022
 */
public class Renderer {
    public static final double FOCAL_LENGTH = 1;
    static private final double sensitivity = Math.PI/1024;

    public static void main() {
        Camera camera = new Camera(new Vertex(0, 0, 2.0));
        Locations locations = new Locations();

        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-.5, .5);

        //double theta = Math.PI/256;.0000755
        double pitchTheta = 0, yawTheta = 0, rollTheta = 0;


        boolean live = true;
        while (live) {
            StdDraw.clear(StdDraw.BLACK);
            if (StdDraw.isKeyPressed(82)) {//reset
                locations = new Locations();
                pitchTheta = 0; yawTheta = 0; rollTheta = 0;
            }
            //spin
            if (StdDraw.isKeyPressed(83)) pitchTheta += sensitivity;
            if (StdDraw.isKeyPressed(87)) pitchTheta -= sensitivity;
            if (StdDraw.isKeyPressed(81)) yawTheta += sensitivity;
            if (StdDraw.isKeyPressed(69)) yawTheta -= sensitivity;
            if (StdDraw.isKeyPressed(65)) rollTheta += sensitivity;
            if (StdDraw.isKeyPressed(68)) rollTheta -= sensitivity;
            //move
            if (StdDraw.isKeyPressed(76)) { camera.displace(new Vector(.01, 0, 0)); }
            if (StdDraw.isKeyPressed(74)) { camera.displace(new Vector(-.01, 0, 0)); }
            if (StdDraw.isKeyPressed(85)) { camera.displace(new Vector(0, .01, 0)); }
            if (StdDraw.isKeyPressed(79)) { camera.displace(new Vector(0, -.01, 0)); }
            if (StdDraw.isKeyPressed(75)) { camera.displace(new Vector(0, 0, .01)); }
            if (StdDraw.isKeyPressed(73)) { camera.displace(new Vector(0, 0, -.01)); }

            double cos = Math.cos(pitchTheta);
            double sin = Math.sin(pitchTheta);
            double[][] pitch = {{1, 0, 0}, {0, cos, -sin}, {0, sin, cos}};
            cos = Math.cos(yawTheta);
            sin = Math.sin(yawTheta);
            double[][] yaw = {{cos, 0,  sin}, {0, 1, 0}, {-sin, 0, cos}};
            cos = Math.cos(rollTheta);
            sin = Math.sin(rollTheta);
            double[][] roll = {{cos, -sin, 0}, {sin, cos, 0}, {0, 0, 1}};
            for (Vertex vertex : locations.getVerticies()){
                vertex.rotate(pitch);
                vertex.rotate(yaw);
                vertex.rotate(roll);
            }

            StdDraw.setPenColor(StdDraw.WHITE);
            // for (Vertex vertex : locations.getVerticies()){        
                // Coordinate loc = vertex.getScreenLocation();
                // StdDraw.text(loc.getX(), loc.getY(), vertex.toString());
            // }
            Vertex vertex = locations.getVerticies()[0];
            Coordinate loc = vertex.getScreenLocation();
            StdDraw.text(loc.getX(), loc.getY(), vertex.toString());
            StdDraw.text(-.2, -.45, vertex.toString());
            
            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);

            locations.updateAll(camera.getLocation());
            drawScreen(locations.getEdges());
            StdDraw.show();
            StdDraw.pause(16);

            //if (StdDraw.isKeyPressed('x')
            //live = false;
        }

    }
    private static void drawScreen(Edge[] edges) {
        for (Edge edge : edges) {
            Coordinate v1 = edge.getA().getScreenLocation();
            Coordinate v2 = edge.getB().getScreenLocation();
            StdDraw.line(v1.getX(), v1.getY(), v2.getX(), v2.getY());
        }
    }

    /*
     * Helper for everyone
     */
    static public double distance(double a, double b){
        return Math.sqrt(a*a + b*b);
    }

    static public double distance(double a, double b, double c){
        return Math.sqrt(a*a + b*b + c*c);
    }

}