package com.cryptmidterm.util;

public class Tuple<T, E> {
    private T first;
    private E second;

    public Tuple(T first, E second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }
}
