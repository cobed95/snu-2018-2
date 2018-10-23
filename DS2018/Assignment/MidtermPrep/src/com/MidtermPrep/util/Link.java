package com.MidtermPrep.util;

public class Link<E> {
    private E element;
    private Link<E> next;

    public Link(E element) {
        this(element, null);
    }
    public Link(E data, Link<E> next) {
        this.element = data;
        this.next = next;
    }

    public Link<E> next() {
        return next;
    }

    public Link<E> setNext(Link<E> next) {
        this.next = next;
        return this.next;
    }

    public E element() {
        return element;
    }

    public E setElement(E element) {
        return this.element = element;
    }

    public static Link freelist = null;

    public static <E> Link<E> get(E element, Link<E> next) {
        if (freelist == null)
            return new Link<E>(element, next);
        Link<E> temp = freelist;
        freelist = freelist.next();
        temp.setElement(element);
        temp.setNext(next);
        return temp;
    }

    public void release() {
        element = null;
        next = freelist;
        freelist = this;
    }
}
