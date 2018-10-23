package com.MidtermPrep.interfaces;

public interface Stack<E> {
    public void clear();
    public void push(E element);
    public E pop();
    public E peak();
    public int length();
}
