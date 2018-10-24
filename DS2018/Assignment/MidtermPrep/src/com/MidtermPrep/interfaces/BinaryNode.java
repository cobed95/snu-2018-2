package com.MidtermPrep.interfaces;

public interface BinaryNode<E> {
    public E element();
    public E setElement(E element);
    public BinaryNode<E> left();
    public BinaryNode<E> right();
    public boolean isLeaf();
}
