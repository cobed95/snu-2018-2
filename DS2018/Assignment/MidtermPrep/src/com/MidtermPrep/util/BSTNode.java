package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.BinaryNode;

public class BSTNode<K, E> implements BinaryNode<E> {
    private K key;
    private E element;
    private BSTNode<K, E> left;
    private BSTNode<K, E> right;

    public BSTNode() {
        left = right = null;
    }

    public BSTNode(K k, E val) {
        left = right = null; key = k; element = val;
    }

    public BSTNode(K k, E val, BSTNode<K, E> l, BSTNode<K, E> r) {
        key = k; element = val; left = l; right = r;
    }

    public K key() {
        return key;
    }

    public K setKey(K k) {
        return key = k;
    }

    public E element() {
        return element;
    }

    public E setElement(E val) {
        return element = val;
    }

    public BSTNode<K, E> left() {
        return left;
    }

    public BSTNode<K, E> setLeft(BSTNode<K, E> l) {
        return left = l;
    }

    public BSTNode<K, E> right() {
        return right;
    }

    public BSTNode<K, E> setRight(BSTNode<K, E> r) {
        return right = r;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
