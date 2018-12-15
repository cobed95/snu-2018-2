package com.finalprep.interfaces;

public interface List<E> {
    public void clear();
    public void insert(E element);
    public void append(E element);
    public E remove();
    public void moveToStart();
    public void moveToEnd();
    public void prev();
    public void next();
    public int length();
    public int currPos();
    public void moveToPos(int idx);
    public E getValue();
}
