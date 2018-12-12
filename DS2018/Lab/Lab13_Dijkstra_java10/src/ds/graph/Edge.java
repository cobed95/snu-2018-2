package ds.graph;

public class Edge {
    private int dest;
    private double wt;

    public Edge(int v, double w) // Constructor
    {
        dest = v;
        wt = w;
    }

    public int vertex() {
        return dest;
    }

    public double weight() {
        return wt;
    }
}
