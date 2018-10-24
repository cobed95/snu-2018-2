package com.MidtermPrep.interfaces;

public interface Heap<E> {
    public void buildHeap();
    public void insert(E element);
    public E remove();
    public boolean isLeaf(int i);
    public int leftChild(int i);
    public int rightChild(int i);
    public int parent(int i);
    public int heapsize(int i);
}
