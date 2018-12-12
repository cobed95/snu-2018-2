package com.finalprep.interfaces;

public interface Graph {
    public void Init(int n);
    public int getNumberOfVertices();
    public int getNumberOfEdges();
    public int firstNeighbor(int v);
    public int nextNeighbor(int v, int w);
    public void setEdge(int i, int j, int weight);
    public void delEdge(int i, int j);
    public boolean isEdge(int i, int j);
    public int weight(int i, int j);
    public void setMark(int v, int val);
    public int getMark(int v);
}
