package com.finalprep.searching.zeroknowledgesearch;

import com.finalprep.interfaces.Searcher;
import com.finalprep.util.Pair;

public class JumpSearcher<K extends Comparable<? super K>, V> implements Searcher<K, V> {
    private LinearSearcher<K, V> linearSearcher = new LinearSearcher<>();

    public V search(Pair<K, V>[] array, K key) {
        int jump = (int) Math.sqrt(array.length);
        return search(array, key, jump);
    }

    public V search(Pair<K, V>[] array, K key, int jump) {
        return search(array, key, 0, jump);
    }

    public V search(Pair<K, V>[] array, K key, int start, int jump) {
        if (jump >= 0) return posSearch(array, key, start, jump);
        else return negSearch(array, key, start, jump);
    }

    public V posSearch(Pair<K, V>[] array, K key, int start, int jump) {
        for (int i = start; i < array.length; i += jump) {
            if (key.compareTo(array[i].getKey()) < 0)
                return linearSearcher.search(array, key, i - jump, i);
        }
        return null;
    }

    public V negSearch(Pair<K, V>[] array, K key, int start, int jump) {
        for (int i = start; i >= 0; i += jump) {
            if (key.compareTo(array[i].getKey()) > 0)
                return linearSearcher.search(array, key, i, i - jump);
        }
        return null;
    }

    public Pair<Integer, Integer> searchForSubarray(Pair<K, V>[] array, K key, int start, int end, int jump) {
        if (jump == 0) return new Pair<Integer, Integer>(start, start + 1);
        else if (jump > 0) return posSubarray(array, key, start, end, jump);
        else return negSubarray(array, key, start, end, jump);
    }

    public Pair<Integer, Integer> posSubarray(Pair<K, V>[] array, K key, int start, int end, int jump) {
        for (int i = start; i <= end; i += jump) {
            if (key.compareTo(array[i].getKey()) < 0)
                return new Pair<Integer, Integer>(i - jump, i);
        }
        return new Pair<Integer, Integer>(end - jump, end);
    }

    public Pair<Integer, Integer> negSubarray(Pair<K, V>[] array, K key, int start, int end, int jump) {
        for (int i = end; i >= start; i += jump) {
            if (key.compareTo(array[i].getKey()) > 0)
                return new Pair<Integer, Integer>(i, i - jump);
        }
        return new Pair<Integer, Integer>(start, start - jump);
    }
}
