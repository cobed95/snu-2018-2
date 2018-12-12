package com.finalprep.generaltree;

public class LMCRSNode<E> {
    private int leftMostChild;
    private E value;
    private int parent;
    private int rightSibling;

    public LMCRSNode() {
        this(-1, null, -1, -1);
    }

    public LMCRSNode(E value) {
        this(-1, value, -1, -1);
    }

    public LMCRSNode(E value, int parent) {
        this(-1, value, parent, -1);
    }

    public LMCRSNode(int leftMostChild,
                     E value,
                     int parent,
                     int rightSibling) {

        this.leftMostChild = leftMostChild;
        this.value = value;
        this.parent = parent;
        this.rightSibling = rightSibling;
    }

    public int getLeftMostChild() {
        return leftMostChild;
    }

    public void setLeftMostChild(int leftMostChild) {
        this.leftMostChild = leftMostChild;
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

    public int getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(int rightSibling) {
        this.rightSibling = rightSibling;
    }
}
