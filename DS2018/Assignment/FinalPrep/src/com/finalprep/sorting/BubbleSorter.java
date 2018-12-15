package com.finalprep.sorting;

import com.finalprep.interfaces.Sorter;
import com.finalprep.util.ArrayManipulater;
import com.finalprep.util.Pair;

public class BubbleSorter<K extends Comparable<? super K>, V> implements Sorter<K, V> {

    public void sort(Pair<K, V>[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j].getKey().compareTo(array[j - 1].getKey()) < 0)
                    ArrayManipulater.swap(array, j, j - 1);
            }
        }
    }

    //public void sort(Pair<K, V>[] array) {
    //    for (int i = 0; i < array.length; i++)
    //        for (int j = array.length - 1; j > i; j--) {
    //            if (array[j].getKey().compareTo(array[j - 1].getKey()) < 0)
    //                ArrayManipulater.swap(array, j, j - 1);
    //        }
    //}
}
