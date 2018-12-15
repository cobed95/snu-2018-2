package com.finalprep.searching.hashtables;

import com.finalprep.interfaces.HashTable;

public class LinearProbing implements HashTable {
    private Integer[] array;

    public LinearProbing(int M) {
        array = new Integer[M];
    }

    public int hash(int x) {
        return x % array.length;
    }

    public void insert(int x) {
        int h = hash(x);
        if (array[h] == null) array[h] = x;
        else {
            int ptr = (h + 1) % array.length;
            while (ptr != h && array[ptr] != null)
                ptr = (ptr + 1) % array.length;
            if (ptr == h) return;
            array[ptr] = x;
        }
    }

    public int search(int x) {
        int h = hash(x);
        if (array[h] == x) return h;
        else {
            int ptr = (h + 1) % array.length;
            while (ptr != h && array[ptr] != null && array[ptr] != x)
                ptr++;
            if (ptr == h || array[ptr] == null) return -1;
            return h;
        }
    }
}
