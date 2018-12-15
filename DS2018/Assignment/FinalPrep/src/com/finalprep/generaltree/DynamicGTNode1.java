package com.finalprep.generaltree;

import com.finalprep.interfaces.GTNode;

public class DynamicGTNode1<E> implements GTNode<E> {
    private E value;
    private int size;
    private DynamicGTNode1<E> parent;
    private DynamicGTNode1<E>[] children;

    public DynamicGTNode1(E value, int size, DynamicGTNode1<E> parent) {
        this(value, size, parent, (DynamicGTNode1<E>[]) new Object[size]);
    }

    public DynamicGTNode1(E value,
                          int size,
                          DynamicGTNode1<E> parent,
                          DynamicGTNode1<E>[] children) {
        this.value = value;
        this.size = size;
        this.parent = parent;
        this.children = children;
    }

    public E value() {
        return value;
    }

    public boolean isLeaf() {
        return size == 0;
    }

    public GTNode<E> parent() {
        return parent;
    }

    public GTNode<E> leftMostChild() {
        if (size == 0) return null;
        return children[0];
    }

    public GTNode<E> rightSibling() {

        DynamicGTNode1<E>[] array = parent.getChildren();

        int ptr = 0;
        while (ptr < array.length && array[ptr] != this)
            ptr++;

        if (ptr >= array.length - 1) return null;
        else return array[ptr + 1];
    }

    public DynamicGTNode1<E>[] getChildren() {
        return children;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setParent(GTNode<E> parent) {
        if (parent instanceof DynamicGTNode1)
            this.parent = (DynamicGTNode1<E>) parent;
    }

    public void insertFirst(GTNode<E> node) {

    }

    public void insertNext(GTNode<E> node) {

    }

    public void removeFirst() {

    }

    public void removeNext() {

    }
}
