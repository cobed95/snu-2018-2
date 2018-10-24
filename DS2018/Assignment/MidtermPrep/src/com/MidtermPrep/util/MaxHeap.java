package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.Heap;

public class MaxHeap<E extends Comparable<? super E>> implements Heap<E> {
    private E[] array;
    private int size;
    private int n;

    public MaxHeap(E[] array, int max, int size) {
        this.array = array;
        this.size = max;
        this.n = size;
        buildHeap();
    }

    public void buildHeap() {
        for (int i = (n / 2) - 1; i >= 0; i--)
            siftDown(i);
    }

    private void siftDown(int i) {
        assert i >= 0 && i < n : "Illegal heap position.";
        while (!isLeaf(i)) {
            int largest;
            if (leftChild(i) < n && array[i].compareTo(array[leftChild(i)]) < 0)
                largest = leftChild(i);
            else largest = i;
            if (rightChild(i) < n && array[largest].compareTo(array[rightChild(i)]) < 0)
                largest = rightChild(i);

            if (largest != i) {
                swap(i, largest);
                i = largest;
            } else return;
        }
    }

    private void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void insert(E element) {
        assert n < size : "Heap is full.";
        int curr = n++;
        array[curr] = element;
        while (curr != 0 && array[curr].compareTo(array[parent(curr)]) > 0) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }

    public E remove() {
        return removeMax();
    }

    private E removeMax() {
        assert n > 0: "Removing from an empty heap.";
        swap(0, --n);
        if (n != 0) siftDown(0);
        return array[n];
    }

    public boolean isLeaf(int i) {
        return i < n && i >= n / 2;
    }

    public int leftChild(int i) {
        return 2 * i + 1;
    }

    public int rightChild(int i) {
        return 2 * i + 2;
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int heapsize() {
        return n;
    }

    public String toString() {
        String result = "";
        if (n > 0) result += "[" + array[0].toString();
        for (int i = 1; i < n; i++) {
            result += ", " + array[i].toString();
        }
        if (n > 0) result += "]";
        return result;
    }
}
