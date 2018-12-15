package com.finalprep.graph;

import com.finalprep.interfaces.Graph;
import com.finalprep.util.MinHeap;
import com.finalprep.util.Pair;
import com.finalprep.util.Queue;

import java.util.ArrayList;

public class GraphTraverser {
    public final int VISITED = 1;
    public final int UNVISITED = 0;

    public void DFS(Graph graph, int u) {

        System.out.println("visiting: " + u);

        graph.setMark(u, VISITED);
        for (int v = graph.firstNeighbor(u);
             v < graph.getNumberOfVertices();
             v = graph.nextNeighbor(u, v)) {

            if (graph.getMark(v) == UNVISITED) DFS(graph, v);
        }
    }

    public void BFS(Graph graph, int start) {

        Queue<Integer> queue = new Queue<Integer>(graph.getNumberOfVertices());

        queue.enqueue(start);

        while (!queue.isEmpty()) {
            int u = queue.dequeue();

            graph.setMark(u, VISITED);

            for (int v = graph.firstNeighbor(u);
                 v < graph.getNumberOfVertices();
                 v = graph.nextNeighbor(u, v)) {

                if (graph.getMark(v) == UNVISITED) queue.enqueue(v);
            }
        }
    }

    public void topologicalSort(Graph graph) {
        for (int i = 0; i < graph.getNumberOfVertices(); i++)
            graph.setMark(i, UNVISITED);
        for (int i = 0; i < graph.getNumberOfVertices(); i++)
            if (graph.getMark(i) == UNVISITED)
                topologicalSortAUX(graph, i);
        System.out.println();
    }

    private void topologicalSortAUX(Graph graph, int u) {

        graph.setMark(u, VISITED);

        for (int v = graph.firstNeighbor(u);
             v < graph.getNumberOfVertices();
             v = graph.nextNeighbor(u, v)) {

            if (graph.getMark(v) == UNVISITED) topologicalSortAUX(graph, v);
        }
        System.out.print(u + " ");
    }

    public void Dijkstra(Graph graph, int start, int[] distance) {

        for (int i = 0; i < graph.getNumberOfVertices(); i++)
            distance[i] = Integer.MAX_VALUE;
        distance[start] = 0;

        for (int i = 0; i < graph.getNumberOfVertices(); i++) {

            int v = minVertex(graph, distance);
            graph.setMark(v, VISITED);

            for (int w = graph.firstNeighbor(v);
                 w < graph.getNumberOfVertices();
                 w = graph.nextNeighbor(v, w)) {

                if (distance[w] > distance[v] + graph.weight(v, w))
                    distance[w] = distance[v] + graph.weight(v, w);
            }
        }
    }

    public int minVertex(Graph graph, int[] distance) {
        int v = 0;
        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            if (graph.getMark(i) == UNVISITED) {
                v = i;
                break;
            }
        }

        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            if (graph.getMark(i) == UNVISITED && distance[i] < distance[v])
                v = i;
        }

        return v;
    }

    public void primMST(Graph graph,
                        int start,
                        ArrayList<Integer> vertices,
                        ArrayList<Integer> edges) {

        for (int i = 0; i < graph.getNumberOfVertices(); i++) {

            vertices.add(start);
        }
    }
}
