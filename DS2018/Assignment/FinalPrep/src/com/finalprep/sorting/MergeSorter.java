package com.finalprep.sorting;

import com.finalprep.util.ArrayManipulater;
import com.finalprep.util.Pair;

public class MergeSorter<K extends Comparable<? super K>, V> implements Sorter<K, V> {
    public void sort(Pair<K, V>[] array) {
        sort(array, 0, array.length - 1);
    }

    public void sort(Pair<K, V>[] array, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            sort(array, p, q);
            sort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }

    public void merge(Pair<K, V>[] array, int p, int q, int r) {
        Pair<K, V>[] temp = new Pair[r - p + 1];
        for (int i = p; i <= q; i++)
            temp[i - p] = array[i];
        for (int j = q + 1; j <= r; j++)
            temp[r + q + 1 - j - p] = array[j];

        int i = 0;
        int j = temp.length - 1;
        for (int k = p; k <= r; k++) {
            if (temp[i].getKey().compareTo(temp[j].getKey()) < 0)
                array[k] = temp[i++];
            else array[k] = temp[j--];
        }
    }
}
