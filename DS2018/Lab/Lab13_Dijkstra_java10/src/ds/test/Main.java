package ds.test;

import ds.graph.Dijkstra;
import ds.graph.Graph;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Graph G = null;
        while (sc.hasNext()) {
            String command = sc.next();
            if ("n".equals(command)) {
                int n = sc.nextInt();
                G = new Graph(n);
            } else if ("edge".equals(command)) {
                int src = sc.nextInt();
                int dst = sc.nextInt();
                double w = sc.nextDouble();
                // Fill your code to add the given edge to graph G
                if (G != null) G.setEdge(src, dst, w);
            } else if ("shortestpath".equals(command)) {
                int src = sc.nextInt();
                Dijkstra dijkstra = new Dijkstra(G);
                // Fill your code to calculate a shortest path from (src) to (dst).
                dijkstra.calculateShortestPath(G, src);
                dijkstra.printAllPath(G, src);
            } else if ("quit".equals(command)) {
                return;
            }
        }
    }
}
