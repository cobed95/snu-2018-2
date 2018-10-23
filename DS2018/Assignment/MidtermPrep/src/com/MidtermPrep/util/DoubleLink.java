package com.MidtermPrep.util;

public class DoubleLink<E> {
    private E element;
    private DoubleLink<E> prev;
    private DoubleLink<E> next;

    public DoubleLink(E element, DoubleLink<E> prev, DoubleLink<E> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }
    public DoubleLink(DoubleLink<E> prev, DoubleLink<E> next) {
        this.prev = prev;
        this.next = next;
    }

    public E element() {
        return element;
    }

    public E setElement(E element) {
        return this.element = element;
    }

    public DoubleLink<E> prev() {
        return prev;
    }

    public DoubleLink<E> setPrev(DoubleLink<E> prev) {
        return this.prev = prev;
    }

    public DoubleLink<E> next() {
        return next;
    }

    public DoubleLink<E> setNext(DoubleLink<E> next) {
        return this.next = next;
    }
}
