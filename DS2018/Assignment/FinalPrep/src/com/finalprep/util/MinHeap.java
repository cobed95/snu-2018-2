package com.finalprep.util;

public class MinHeap<K extends Comparable<? super K>, V> {
    private Pair<K, V>[] heap;
    private int heapsize;

    public MinHeap(Pair<K, V>[] heap, int heapsize) {
        this.heap = heap;
        this.heapsize = heapsize;
        buildHeap();
    }

    private void buildHeap() {
        for (int i = (heapsize / 2) - 1; i >= 0; i--)
            siftDown(i);
    }

    public void siftDown(int i) {
        int smallest = i;
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        if (leftChild < heapsize &&
                heap[i].getKey().compareTo(heap[leftChild].getKey()) > 0)
            smallest = leftChild;
        if (rightChild < heapsize &&
                heap[smallest].getKey().compareTo(heap[rightChild].getKey()) > 0)
            smallest = rightChild;
        if (smallest != i) {
            ArrayManipulater.swap(heap, i, smallest);
            siftDown(smallest);
        }
    }

    private int parent(int i) {
        if (i == 0) return 0;
        else return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return (i * 2) + 1;
    }

    private int rightChild(int i) {
        return (i * 2) + 2;
    }

    public Pair<K, V> removeMin() {
        Pair<K, V> result = heap[0];
        ArrayManipulater.swap(heap, 0, heapsize - 1);
        heapsize--;
        siftDown(0);
        return result;
    }

    public Pair<K, V>[] getWholeArray() {
        return heap;
    }

    public void updateKey(int idx, K key) {
        heap[idx].setKey(key);
        siftDown(idx);
    }
}
