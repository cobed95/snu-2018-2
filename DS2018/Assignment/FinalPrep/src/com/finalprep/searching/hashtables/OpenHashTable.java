package com.finalprep.searching.hashtables;

import com.finalprep.interfaces.HashTable;
import com.finalprep.util.SinglyLinkedList;

public class OpenHashTable implements HashTable {
    private SinglyLinkedList<Integer>[] array;

    public OpenHashTable(int M) {
        array = new SinglyLinkedList[M];
    }

    public int hash(int x) {
        return x % array.length;
    }

    public void insert(int x) {
        int h = hash(x);
        if (array[h] == null) {
            array[h] = new SinglyLinkedList<Integer>();
            array[h].append(x);
        } else array[h].append(x);
    }

    public int search(int x) {
        int h = hash(x);
        if (array[h] == null) {
            return -1;
        } else {
            array[h].moveToStart();
            while (array[h].getValue() != null && array[h].getValue() != x)
                array[h].next();
            if (array[h].getValue() == null) return -1;
            else return h;
        }
    }
}
