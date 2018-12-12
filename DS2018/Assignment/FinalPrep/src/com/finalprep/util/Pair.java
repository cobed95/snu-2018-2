package com.finalprep.util;

public class Pair<K extends Comparable<? super K>, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public String toString() {
        return "(" + key.toString() + ", " + value.toString() + ")";
    }

    public K getFirst() {
        return key;
    }

    public V getSecond() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }
}
