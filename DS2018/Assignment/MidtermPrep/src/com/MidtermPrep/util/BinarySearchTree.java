package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.Dictionary;

public class BinarySearchTree<Key extends Comparable<? super Key>, E> implements Dictionary<Key, E> {
    private BSTNode<Key, E> root;
    private int nodeCount;

    public BinarySearchTree() {
        root = null; nodeCount = 0;
    }

    public void clear() {
        root = null; nodeCount = 0;
    }

    public void insert(Key k, E val) {
        root = insert(k, val, root);
        nodeCount++;
    }

    private BSTNode<Key, E> insert(Key k, E val, BSTNode<Key, E> root) {
        if (root == null) return new BSTNode<Key, E>(k, val);
        int compare = k.compareTo(root.key());
        if (compare < 0) root.setLeft(insert(k, val, root.left()));
        else if (compare == 0) root.setElement(val);
        else root.setRight(insert(k, val, root.right()));
        return root;
    }

    public E remove(Key k) {
        E temp = find(k);
        if (temp != null) {
            root = remove(k, root);
            nodeCount--;
        }
        return temp;
    }

    private BSTNode<Key, E> remove(Key k, BSTNode<Key, E> root) {
        if (root == null) return null;
        int compare = k.compareTo(root.key());
        if (compare < 0) root.setLeft(remove(k, root.left()));
        else if (compare > 0) root.setRight(remove(k, root.right()));
        else {
            if (root.left() == null) return root.right();
            else if (root.right() == null) return root.left();
            else {
                BSTNode<Key, E> temp = getMin(root.right());
                root.setElement(temp.element());
                root.setKey(temp.key());
                root.setRight(deleteMin(root.right()));
            }
        }
        return root;
    }

    private BSTNode<Key, E> getMin(BSTNode<Key, E> root) {
        if (root.left() == null) return root;
        return getMin(root.left());
    }

    private BSTNode<Key, E> deleteMin(BSTNode<Key, E> root) {
        if (root.left() == null) return root.right();
        root.setLeft(deleteMin(root.left()));
        return root;
    }

    public E removeAny() {
        if (root != null) {
            E temp = root.element();
            remove(root.key(), root);
            return temp;
        } else return null;
    }

    public E find(Key k) {
        return find(k, root);
    }

    private E find(Key k, BSTNode<Key, E> root) {
        if (root == null) return null;
        int compare = k.compareTo(root.key());
        if (compare < 0) return find(k, root.left());
        else if (compare == 0) return root.element();
        else return find(k, root.right());
    }

    public int size() {
        return nodeCount;
    }
}
