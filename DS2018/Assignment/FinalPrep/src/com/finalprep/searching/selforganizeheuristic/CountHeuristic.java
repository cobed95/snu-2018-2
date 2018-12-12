package com.finalprep.searching.selforganizeheuristic;

import com.finalprep.interfaces.SelfOrganizingList;

public class CountHeuristic<E> implements SelfOrganizingList<E> {
    private int[] count;
    private E[] array;

    public CountHeuristic(E[] array) {
        this.array = array;
        count = new int[array.length];
    }

    public int search(E value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                count[i]++;
                reorganize(i);
                return i;
            }
        }
        return -1;
    }

    public void reorganize(int i) {
        E key = array[i];
        int j = i - 1;
        while (j >= 0 && count[j] < count[i]) {
            array[j + 1] = array[j];
        }
        array[j + 1] = key;
    }
}
