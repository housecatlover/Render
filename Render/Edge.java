
/*
 * Stores two connected Vertices.
 *
 * @Alex Schwartz
 * @j8 11/15/2022
 */
public class Edge
{
    private Vertex a;
    private Vertex b;

    /**
     * Constructor for objects of class Edge
     */
    public Edge(Vertex a, Vertex b)
    {
        this.a = a;
        this.b = b;
    }

    /**
     * @Return the first vertex.
     */
    public Vertex getA(){
        return a;
    }

    /**
     * @Return the second vertex.
     */
    public Vertex getB(){
        return b;
    }
}