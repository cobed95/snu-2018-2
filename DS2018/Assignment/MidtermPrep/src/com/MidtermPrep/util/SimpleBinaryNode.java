package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.BinaryNode;

public class SimpleBinaryNode<E> implements BinaryNode<E> {
    private E element;
    private SimpleBinaryNode<E> left;
    private SimpleBinaryNode<E> right;

    public SimpleBinaryNode() {
        this(null);
    }

    public SimpleBinaryNode(E element) {
        this(element, null, null);
    }

    public SimpleBinaryNode(E element, SimpleBinaryNode<E> left, SimpleBinaryNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public E element() {
        return element;
    }

    public E setElement(E element) {
        return this.element = element;
    }

    public SimpleBinaryNode<E> left() {
        return left;
    }

    public SimpleBinaryNode<E> right() {
        return right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public static <E> String preorder(BinaryNode<E> root) {
        return preorder(root, "");
    }

    private static <E> String preorder(BinaryNode<E> root, String result) {
        if (root == null) return result;
        result += root.element().toString();
        result = preorder(root.left(), result);
        result = preorder(root.right(), result);
        return result;
    }

    public static <E> String inorder(BinaryNode<E> root) {
        return inorder(root, "");
    }

    private static <E> String inorder(BinaryNode<E> root, String result) {
        if (root == null) return result;
        result = inorder(root.left(), result);
        result += root.element().toString();
        result = inorder(root.right(), result);
        return result;
    }

    public static <E> String postorder(BinaryNode<E> root) {
        return postorder(root, "");
    }

    public static <E> String postorder(BinaryNode<E> root, String result) {
        if (root == null) return result;
        result = postorder(root.left(), result);
        result = postorder(root.right(), result);
        result += root.element().toString();
        return result;
    }
}
