package com.finalprep.interfaces;

import com.finalprep.util.Pair;

public interface Searcher<K extends Comparable<? super K>, V> {
    public V search(Pair<K, V>[] array, K key);
}
