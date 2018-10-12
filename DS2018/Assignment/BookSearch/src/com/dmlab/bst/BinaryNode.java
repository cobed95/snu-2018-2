package com.dmlab.bst;

public class BinaryNode<Key, E> {

	private Key key;
	private E value;

	private BinaryNode<Key, E> left;
	private BinaryNode<Key, E> right;

	// Declare more variables HERE

	/** A constructor */
	public BinaryNode() {
		value = null;
		left = null;
		right = null;
	}

	public BinaryNode(Key k, E val) {
		left = right = null;
		key = k;
		value = val;
	}

	public void setKey(Key k) {
		key = k;
	}

	public void setValue(E v) {
		value = v;
	}

	// Implement more constructors HERE
	/** Returns the element */
	public Key getKey() {
		return key;
	}

	/** Returns the element */
	public E getValue() {
		return value;
	}

	/** Returns the left child */
	public BinaryNode<Key, E> getLeft() {
		return left;
	}

	/** Returns the right child */
	public BinaryNode<Key, E> getRight() {
		return right;
	}

	/** Set the left child as the given node l */
	public void setLeft(BinaryNode<Key, E> l) {
		left = l;
	}

	/** Set the right child as the given node r */
	public void setRight(BinaryNode<Key, E> r) {
		right = r;
	}

	/** Returns if this node is a leaf or not */
	public boolean isLeaf() {
		return left == null && right == null;
	}

	// Implement more functions HERE
}
