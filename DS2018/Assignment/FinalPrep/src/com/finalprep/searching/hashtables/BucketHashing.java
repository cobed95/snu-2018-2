package com.finalprep.searching.hashtables;

import com.finalprep.interfaces.HashTable;

public class BucketHashing implements HashTable {
    private Integer[][] array;
    private final int BUCKETSIZE = 10;
    private Integer[] overflow;


    public BucketHashing(int M) {
        array = new Integer[M][];
        for (int i = 0; i < M; i++)
            array[i] = new Integer[BUCKETSIZE];

        overflow = new Integer[100];
    }

    public int hash(int x) {
        return x % array.length;
    }

    public void insert(int x) {
        int h = hash(x);
        int ptr = 0;
        while (ptr < BUCKETSIZE && array[h][ptr] != null)
            ptr++;
        if (ptr == BUCKETSIZE) {
            ptr = 0;
            while (ptr < overflow.length && overflow[ptr] != null)
                ptr++;
            if (ptr == overflow.length) return;
            overflow[ptr] = x;
        } else {
            array[h][ptr] = x;
        }
    }

    public int search(int x) {
        int h = hash(x);
        int ptr = 0;
        while (ptr < BUCKETSIZE && array[h][ptr] != null)
            ptr++;
        if (ptr == BUCKETSIZE) {
            ptr = 0;
            while (ptr < overflow.length && overflow[ptr] != null)
                ptr++;
            if (ptr == overflow.length) return -1;
            return ptr * 1000;
        } else {
            return h;
        }
    }
}
