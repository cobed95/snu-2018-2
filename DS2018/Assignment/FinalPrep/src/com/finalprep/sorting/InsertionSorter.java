package com.finalprep.sorting;

import com.finalprep.interfaces.Sorter;
import com.finalprep.util.Pair;

public class InsertionSorter<K extends Comparable<? super K>, V> implements Sorter<K, V> {
    public void sort(Pair<K, V>[] array) {
        sort(array, 0, 1);
    }

    public void sort(Pair<K, V>[] array, int start, int incr) {
        for (int i = start + incr; i < array.length; i += incr) {
            Pair<K, V> data = array[i];
            K key = data.getKey();
            int j = i - incr;
            while (j >= start && key.compareTo(array[j].getKey()) < 0) {
                array[j + incr] = array[j];
                j -= incr;
            }
            array[j + incr] = data;
        }
    }

    //public void sort(Pair<K, V>[] array, int start, int incr) {
    //    for (int i = start + incr; i < array.length; i += incr) {
    //        Pair<K, V> data = array[i];
    //        K key = data.getKey();
    //        int j = i - incr;
    //        while (j >= start && key.compareTo(array[j].getKey()) < 0) {
    //            array[j + incr] = array[j];
    //            j -= incr;
    //        }
    //        array[j + incr] = data;
    //    }
    //}
}
