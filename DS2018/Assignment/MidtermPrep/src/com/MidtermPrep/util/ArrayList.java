package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.List;

public class ArrayList<E> implements List<E> {
    private static final int defaultSize = 10;

    private int maxSize;
    private int listSize;
    private int curr;
    private E[] listArray;

    public ArrayList() {
        this(defaultSize);
    }

    public ArrayList(int size) {
        maxSize = size;
        listSize = curr = 0;
        listArray = (E[]) new Object[size];
    }

    public void clear() {
        listSize = curr = 0;
    }

    public void insert(E element) {
        assert listSize < maxSize : "ArrayList is full.";
        for (int i = listSize; i > curr; i--) {
            listArray[i] = listArray[i - 1];
        }
        listArray[curr] = element;
        listSize++;
    }

    public void append(E element) {
        assert listSize < maxSize : "ArrayList is full.";
        listArray[listSize] = element;
        listSize++;
    }

    public E remove() {
        assert listSize > 0 : "ArrayList is empty.";
        if (curr == listSize) return null;
        E removed = listArray[curr];
        for (int i = curr; i < listSize - 1; i++) {
            listArray[i] = listArray[i + 1];
        }
        listSize--;
        return removed;
    }

    public void moveToStart() {
        curr = 0;
    }

    public void moveToEnd() {
        curr = listSize;
    }

    public void prev() {
        if (curr == 0) return;
        curr--;
    }

    public void next() {
        if (curr == listSize) return;
        curr++;
    }

    public int length() {
        return listSize;
    }

    public int currPos() {
        return curr;
    }

    public void moveToPos(int idx) {
        assert idx >= 0 && idx <= listSize : "Illegal list position.";
        curr = idx;
    }

    public E getValue() {
        assert curr < listSize : "Nothing to get.";
        return listArray[curr];
    }

    public String toString() {
        return toString(0, "");
    }

    private String toString(int ptr, String result) {
        if (ptr == listSize) return result;
        if (ptr == 0) result += "[" + listArray[ptr];
        else {
            result += ", " + listArray[ptr].toString();
            if (ptr == listSize - 1) result += "]";
        }
        return toString(ptr + 1, result);
    }
}
