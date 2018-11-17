package com.finalprep.sorting;

import com.finalprep.util.ArrayManipulater;
import com.finalprep.util.Pair;

public class ShellSorter<K extends Comparable<? super K>, V> implements Sorter<K, V> {
    public void sort(Pair<K, V>[] array) {
        InsertionSorter<K, V> insertionSorter = new InsertionSorter<>();
        for (int step = array.length / 2; step >= 2; step /= 2) {
            for (int idx = 0; idx < step; idx++)
                insertionSorter.sort(array, idx, step);
        }
        insertionSorter.sort(array);
    }
}
