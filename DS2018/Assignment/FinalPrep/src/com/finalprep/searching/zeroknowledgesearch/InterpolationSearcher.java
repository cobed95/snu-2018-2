package com.finalprep.searching.zeroknowledgesearch;

import com.finalprep.interfaces.Searcher;
import com.finalprep.util.Pair;

public class InterpolationSearcher<V> implements Searcher<Integer, V> {

    public V search(Pair<Integer, V>[] array, Integer key) {
        return search(array, key, 0, array.length - 1);
    }

    public V search(Pair<Integer, V>[] array, Integer key, int start, int end) {

        double p = getP(key, array[start].getKey(), array[end].getKey());
        int idx = (int) ((end - start + 1) * p);

        if (key.compareTo(array[idx].getKey()) == 0)
            return array[idx].getValue();
        else if (key.compareTo(array[idx].getKey()) < 0)
            return search(array, key, start, idx);
        else
            return search(array, key, idx, end);
    }

    public double getP(Integer key, int start, int end) {
        return ((double) key - start) / (end - start);
    }
}
