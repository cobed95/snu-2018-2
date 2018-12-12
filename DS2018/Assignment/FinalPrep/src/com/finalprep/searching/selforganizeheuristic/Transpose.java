package com.finalprep.searching.selforganizeheuristic;

import com.finalprep.interfaces.SelfOrganizingList;
import com.finalprep.util.ArrayManipulater;

public class Transpose<E> implements SelfOrganizingList<E> {
    private E[] array;

    public Transpose(E[] array) {
        this.array = array;
    }

    public int search(E value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                reorganize(i);
                return i;
            }
        }
        return -1;
    }

    public void reorganize(int i) {
        if (i == 0) return;
        ArrayManipulater.swap(array, i, i - 1);
    }
}
