package com.finalprep.searching.zeroknowledgesearch;

import com.finalprep.interfaces.Searcher;
import com.finalprep.util.Pair;

public class LinearSearcher<K extends Comparable<? super K>, V> implements Searcher<K, V> {
    public V search(Pair<K, V>[] array, K key) {
        return search(array, key, 0, array.length - 1);
    }

    public V search(Pair<K, V>[] array, K key, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (key.compareTo(array[i].getKey()) == 0)
                return array[i].getValue();
        }
        return null;
    }
}
