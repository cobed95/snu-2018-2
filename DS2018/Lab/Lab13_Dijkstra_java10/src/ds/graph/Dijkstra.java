package ds.graph;

import ds.queue.DistanceQueue;

public class Dijkstra {
    private DistanceQueue distQueue;
    private int[] prev;
    private double[] D;
    private Graph G;

    public Dijkstra(Graph G) {
        // Fill your code
        this.G = G;

        this.distQueue = new DistanceQueue(G.n());

        this.prev = new int[G.n()];
        for (int i = 0; i < G.n(); i++) this.prev[i] = 0;

        this.D = new double[G.n()];
    }

    public void calculateShortestPath(Graph G, int start) {
        // Fill your code
        for (int i = 0; i < G.n(); i++)
            D[i] = Integer.MAX_VALUE;
        D[start] = 0;

        for (int i = 0; i < G.n(); i++)
            distQueue.insert(i, D[i]);

        for (int i = 0; i < G.n(); i++) {
            int v = minVertex(G);
            if (D[v] == Integer.MAX_VALUE) return;

            for (int w = G.first(v); w < G.n(); w = G.next(v, w)) {
                if (D[w] > (D[v] + G.weight(v, w))) {
                    D[w] = D[v] + G.weight(v, w);
                    distQueue.decreaseDistance(w, D[w]);
                    prev[w] = v;
                }
            }
        }
    }

    private void printPathToEnd(Graph G, int end) {
        // Fill your code
        String result = "";
        int ptr = end;
        while (D[ptr] != 0) {
            result = ptr + " " + result;
            ptr = prev[ptr];
        }
        result = ptr + " " + result;

        System.out.print(end + ":");
        System.out.print(" " + D[end] + " ");
        System.out.println(result);
    }
    
    public void printAllPath(Graph G, int src) {
    	// Fill your code
        for (int i = 0; i < G.n(); i++) {
            if (i == src) continue;
            System.out.print("PATH " + src + " ");
            printPathToEnd(G, i);
        }
    }

    public int minVertex(Graph G) {
    	// Fill your code
		return distQueue.removeMin();
    }
}
