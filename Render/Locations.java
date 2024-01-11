
/**
 * Stores the locations of the vertices and edges for a cube
 *
 * @Alex Schwartz
 * @j8 12/1/2022
 */
public class Locations {
    private final Vertex[] vertices;
    private final Edge[] edges;
    /**
     * Constructor for objects of class locations, loads preset vertices and edges
     */
    public Locations() {
        // initialise the vertices
        Vertex[] temp = {
                new Vertex(.1, .1, .1), //0 bottom back left
                new Vertex(.1, .1, .9), //1 bottom front left
                new Vertex(.1, .9, .9), //2 top front left
                new Vertex(.1, .9, .1), //3 top back left
                new Vertex(.9, .1, .1), //4 bottom back right
                new Vertex(.9, .1, .9), //5 bottom front right
                new Vertex(.9, .9, .9), //6 top front right
                new Vertex(.9, .9, .1)}; //7 top back right
        vertices = temp;
        Vector recenter = new Vector(-.5, -.5, -.5);
        for (Vertex vertex : vertices){
            vertex.displace(recenter);
        }

        Edge[] alsoTemp = {
                new Edge(vertices[0], vertices[1]),
                new Edge(vertices[0], vertices[4]),
                new Edge(vertices[0], vertices[3]),
                new Edge(vertices[1], vertices[5]),
                new Edge(vertices[1], vertices[2]),
                new Edge(vertices[2], vertices[6]),
                new Edge(vertices[2], vertices[3]),
                new Edge(vertices[3], vertices[7]),
                new Edge(vertices[4], vertices[5]),
                new Edge(vertices[4], vertices[7]),
                new Edge(vertices[5], vertices[6]),
                new Edge(vertices[6], vertices[7])
        };
        edges = alsoTemp;

    }

    public void transform(double[][] transformationMatrix){

    }

    public void updateAll(Vertex camera){
        for(Vertex vertex : vertices){
            vertex.updateCoordinate(camera);
        }
    }

    public void updateAll(Camera camera){
        for(Vertex vertex : vertices){
            vertex.updateCoordinate(camera);
        }
    }

    public Edge getEdge(int index){
        return edges[index];
    }

   public Vertex getVertex(int index){
        return vertices[index];
   }

   public Edge[] getEdges(){
        return edges;
   }

   public Vertex[] getVerticies(){
        return vertices;
    }
}