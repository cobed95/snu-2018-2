package com.finalprep.sorting;

import com.finalprep.interfaces.Sorter;
import com.finalprep.util.MaxHeap;
import com.finalprep.util.Pair;

public class HeapSorter<K extends Comparable<? super K>, V> implements Sorter<K, V> {
    public void sort(Pair<K, V>[] array) {
        MaxHeap<K, V> maxHeap = new MaxHeap<>(array, array.length);
        for (int i = 0; i < array.length; i++)
            maxHeap.removeMax();
    }
}
