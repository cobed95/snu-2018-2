package com.MidtermPrep.util;

public class KVpair<Key, E> {
    private Key key;
    private E value;

    public KVpair(Key k, E e) {
        key = k;
        value = e;
    }

    public Key key() {
        return key;
    }

    public E value() {
        return value;
    }

    public String toString() {
        return "<" + key.toString() + ", " + value.toString() + ">";
    }
}
