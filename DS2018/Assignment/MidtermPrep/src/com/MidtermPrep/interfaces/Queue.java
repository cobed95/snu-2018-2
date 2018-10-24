package com.MidtermPrep.interfaces;

public interface Queue<E> {
    public void clear();
    public void enqueue(E element);
    public E dequeue();
    public E front();
    public E rear();
    public int length();
}
