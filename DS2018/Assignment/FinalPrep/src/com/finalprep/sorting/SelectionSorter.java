package com.finalprep.sorting;

import com.finalprep.util.ArrayManipulater;
import com.finalprep.util.Pair;

public class SelectionSorter<K extends Comparable<? super K>, V> implements Sorter<K, V> {
    public void sort(Pair<K, V>[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[minIdx].getKey().compareTo(array[j].getKey()) > 0)
                    minIdx = j;
            ArrayManipulater.swap(array, minIdx, i);
        }
    }
}
