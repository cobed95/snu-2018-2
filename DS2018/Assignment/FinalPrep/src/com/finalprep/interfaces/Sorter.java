package com.finalprep.interfaces;

import com.finalprep.util.Pair;

public interface Sorter<K extends Comparable<? super K>, V> {
    public void sort(Pair<K, V>[] array);
}
