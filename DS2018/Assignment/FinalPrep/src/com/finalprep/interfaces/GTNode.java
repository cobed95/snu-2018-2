package com.finalprep.interfaces;

public interface GTNode<E> {
    public E value();
    public boolean isLeaf();
    public GTNode<E> parent();
    public GTNode<E> leftMostChild();
    public GTNode<E> rightSibling();
    public void setValue(E value);
    public void setParent(GTNode<E> parent);
    public void insertFirst(GTNode<E> node);
    public void insertNext(GTNode<E> node);
    public void removeFirst();
    public void removeNext();
}
