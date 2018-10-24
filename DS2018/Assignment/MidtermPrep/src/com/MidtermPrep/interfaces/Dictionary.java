package com.MidtermPrep.interfaces;

public interface Dictionary<Key, E> {
    public void clear();
    public void insert(Key k, E element);
    public E remove(Key k);
    public E removeAny();
    public E find(Key k);
    public int size();
}
