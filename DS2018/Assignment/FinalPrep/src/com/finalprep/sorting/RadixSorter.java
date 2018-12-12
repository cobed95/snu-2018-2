package com.finalprep.sorting;

import com.finalprep.interfaces.Sorter;
import com.finalprep.util.Pair;

public class RadixSorter<V> implements Sorter<Integer, V> {

    public void sort(Pair<Integer, V>[] array) {
        sort(array, new Pair[array.length], 2, 10, new int[10]);
    }

    public void sort(Pair<Integer, V>[] array,
                     Pair<Integer, V>[] bin,
                     int k,
                     int r,
                     int[] count) {

        int i, j, rtok;

        for (i = 0, rtok = 1; i < k; i++, rtok *= r) {
            for (j = 0; j < r; j++) count[j] = 0;

            for (j = 0; j < array.length; j++)
                count[(array[j].getKey() / rtok) % r]++;

            for (j = 1; j < r; j++)
                count[j] = count[j - 1] + count[j];

            for (j = array.length - 1; j >= 0; j--)
                bin[--count[(array[j].getKey() / rtok) % r]] = array[j];

            for (j = 0; j < array.length; j++) array[j] = bin[j];
        }
    }
}
