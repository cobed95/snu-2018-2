package com.finalprep.graph;

import java.util.ArrayList;
import com.finalprep.interfaces.Graph;

public class BasicGraph implements Graph {
    private ArrayList<ArrayList<Integer>> adjList;
    private ArrayList<Integer> marks;
    private int numOfEdges;

    public void Init(int n) {

        adjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<Integer>());

        marks = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            marks.add(0);

        numOfEdges = 0;
    }

    public int getNumberOfVertices() {
        return adjList.size();
    }

    public int getNumberOfEdges() {
        return numOfEdges;
    }

    public int firstNeighbor(int v) {
        if (adjList.get(v).size() == 0)
            return adjList.size();
        return adjList.get(v).get(0);
    }

    public int nextNeighbor(int v, int w) {
        int ptr = 0;
        while (ptr < adjList.get(v).size() && adjList.get(v).get(ptr) != w)
            ptr++;

        if (ptr >= adjList.get(v).size() - 1) return adjList.size() + 1;
        else return adjList.get(v).get(ptr + 1);
    }

    public void setEdge(int i, int j, int weight) {
        adjList.get(i).add(j);
        numOfEdges++;
    }

    public void delEdge(int i, int j) {
        adjList.get(i).remove(j);
        numOfEdges--;
    }

    public boolean isEdge(int i, int j) {
        return adjList.get(i).contains(j);
    }

    public int weight(int i, int j) {
        return 1;
    }

    public void setMark(int v, int val) {
        marks.set(v, val);
    }

    public int getMark(int v) {
        return marks.get(v);
    }
}
