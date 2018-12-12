package com.finalprep.generaltree;

public class ListsOfChildrenNode<E> {
    private E value;
    private int parent;
    private ListsOfChildrenNode<E> next;

    public ListsOfChildrenNode() {
        this(null, -1, null);
    }

    public ListsOfChildrenNode(E value) {
        this(value, -1, null);
    }

    public ListsOfChildrenNode(E value, int parent) {
        this(value, parent, null);
    }

    public ListsOfChildrenNode(E value, int parent, ListsOfChildrenNode<E> next) {
        this.value = value;
        this.parent = parent;
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public ListsOfChildrenNode<E> getNext() {
        return next;
    }

    public void setNext(ListsOfChildrenNode<E> next) {
        this.next = next;
    }
}
