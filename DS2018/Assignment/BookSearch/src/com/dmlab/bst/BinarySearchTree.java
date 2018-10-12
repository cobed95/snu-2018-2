package com.dmlab.bst;

public class BinarySearchTree<Key extends Comparable<? super Key>, E> {
	
	/** root of this tree */
	private BinaryNode<Key, E> root;
	private int nodecount = 0;
	// Declare more variables HERE

	/** 
	 * Constructor 
	 * Do not modify this function.
	 */
	public BinarySearchTree() {
		root = null;
		nodecount = 0;
	}

	/**
	 * This function returns the root of the BST.
	 * Do not modify this function.
	 * @return
	 */
	public BinaryNode<Key, E> getRoot() {
		return root;
	}

	/** 
	 * Reinitialize tree 
	 */
	public void clear() {
		root = null;
		nodecount = 0;
	}

	/** 
	 * Insert an item into the tree.
	 * @param key of the item
	 * @param value of the item
	 */
	public void insert(Key key, E v) {
		// TODO: Fill in this function
        if (this.root == null) {
            this.root = new BinaryNode<Key, E>(key, v);
            this.nodecount++;
        }
        else this.root = insertHelp(this.root, key, v);
	}

	/** 
	 * Remove an item from the tree. 
	 * @param key of the item to be removed.
	 * @return the value of the removed item. If no such item, return null.
	 */
	public E remove(Key key) {
		// TODO: Fill in this function
		E tmp = findHelp(root, key);
		this.root = removeHelp(this.root, key);
		return tmp;
	}

	/**
	 * Find the item with key=k.
	 * @param k
	 * @return the value if the item. If no such item, return null.
	 * Do not modify this function.
	 */
	public E find(Key k) {
		return findHelp(root, k);
	}

	/** 
	 * @return The number of nodes in the tree. 
	 */
	public int size() {
		return nodecount;
	}

	/**
	 * Find the item with key=i in the tree rt.
	 * @param rt is the root of the tree.
	 * @param k is the key that we want to find in tree rt.
	 * @return the value of the wanted item. If no such item, return null.
	 */
	private E findHelp(BinaryNode<Key, E> rt, Key k) {
		// TODO: Fill in this function
        if (rt == null) return null;
        int compare = k.compareTo(rt.getKey());
        if (compare == 0) return rt.getValue();
        else if (compare < 0) return findHelp(rt.getLeft(), k);
        else return findHelp(rt.getRight(), k);
	}

	/**
	 * This function print the tree of "root" in inorder.
	 * Do not modify this function.
	 */
	public void inorder() {
		inorderHelper(root);
	}

	
	/**
	 * Given a tree with root rt, print the keys of all its nodes in inorder.
	 * Each line for each node. 
	 * Output specification is "ORDER: key".
	 * @param rt is the root of the tree
	 */
	private void inorderHelper(BinaryNode<Key, E> rt) {
		// TODO: Fill in this function
		if (rt == null) return;
		inorderHelper(rt.getLeft());
		System.out.println("ORDER: " + rt.getKey());
		inorderHelper(rt.getRight());
	}

	/**
	 * Given range [from, to], count how many nodes are in this range.
	 * @param from is the lower bound of the range
	 * @param to is the upper bound of the range
	 * @return the number of nodes in this range
	 * Do not modify this function.
	 */
	public int rangeSearch(Key from, Key to) {
		return rangeSearchHelper(root, from, to);
	}

	/**
	 * Given range [from, to] and a tree rt, count how many nodes are in this range.
	 * @param rt is the root of the tree
	 * @param from is the lower bound of the range
	 * @param to is the upper bound of the range
	 * @return the number of nodes in this range
	 */
	private int rangeSearchHelper(BinaryNode<Key, E> rt, Key from, Key to) {
		// TODO: Fill in this function
        if (rt == null) return 0;
        if (to.compareTo(rt.getKey()) < 0) return rangeSearchHelper(rt.getLeft(), from, to);
        if (from.compareTo(rt.getKey()) > 0) return rangeSearchHelper(rt.getRight(), from, to);
        return rangeSearchHelper(rt.getLeft(), from, to) + rangeSearchHelper(rt.getRight(), from, to) + 1;
	}

	/**
	 * Insert the item <key,v> into the tree rt.
	 * @return the tree after insertion
	 */
	private BinaryNode<Key, E> insertHelp(BinaryNode<Key, E> rt, Key key, E v) {
		// TODO: Fill in this function
	    int compare = key.compareTo(rt.getKey());
	    if (compare < 0 && rt.getLeft() != null) rt.setLeft(insertHelp(rt.getLeft(), key, v));
	    else if (compare < 0) {
	        rt.setLeft(new BinaryNode<Key, E>(key, v));
	        this.nodecount++;
        }
	    else if (compare > 0 && rt.getRight() != null) rt.setRight(insertHelp(rt.getRight(), key, v));
	    else if (compare > 0) {
	        rt.setRight(new BinaryNode<Key, E>(key, v));
	        this.nodecount++;
        }
        return rt;
	}

	/**
	 * Remove a node with key value k from the tree rt.
	 * @return the tree after removing.
	 */
	private BinaryNode<Key, E> removeHelp(BinaryNode<Key, E> rt, Key k) {
		// TODO: Fill in this function
        int compare = k.compareTo(rt.getKey());
        if (compare == 0 && rt.isLeaf()) {
            this.nodecount--;
            return null;
        }
        else if (compare == 0 && rt.getLeft() != null && rt.getRight() == null) {
            this.nodecount--;
            return rt.getLeft();
        }
        else if (compare == 0 && rt.getLeft() == null && rt.getRight() != null) {
            this.nodecount--;
            return rt.getRight();
        }
        else if (compare == 0) {
            BinaryNode<Key, E> node = getMin(rt.getRight());
            rt.setKey(node.getKey());
            rt.setValue(node.getValue());
            deleteMin(rt.getRight());
            this.nodecount--;
        } else if (compare < 0 && rt.getLeft() != null) rt.setLeft(removeHelp(rt.getLeft(), k));
        else if (compare > 0 && rt.getRight() != null) rt.setRight(removeHelp(rt.getRight(), k));
        return rt;
	}

	/**
	 * Given a tree rt, get its smallest node. 
	 * The smallest node is the node with the minimum key.
	 * @param rt
	 * @return the smallest node. 
	 */
	private BinaryNode<Key, E> getMin(BinaryNode<Key, E> rt) {
		// TODO: Fill in this function
        if (rt.getLeft() == null) return rt;
        else return getMin(rt.getLeft());
	}

	/**
	 * Given a tree rt, delete the smallest node and return this tree.
	 * @param rt
	 * @return the tree after deletion.
	 */
	private BinaryNode<Key, E> deleteMin(BinaryNode<Key, E> rt) {
		// TODO: Fill in this function
        if (rt == null || rt.isLeaf()) return null;
        else if (rt.getLeft() == null && rt.getRight() != null) return rt.getRight();
        else if (rt.getLeft() == null && rt.getLeft().isLeaf()) rt.setLeft(null);
        else rt.setLeft(deleteMin(rt.getLeft()));
        return rt;
	}

	/**
	 * Get the key of the smallest node.
	 * Do not modify this function
	 * @return
	 */
	public Key getFirst() {
		return getFirst(root);
	}

	/**
	 * Given a tree rt, get the key of the smallest node.
	 * @param rt
	 * @return the key of the smallest node. If the tree is empty, return null.
	 */
	private Key getFirst(BinaryNode<Key, E> rt) {
		// TODO: Fill in this function
		return getMin(rt).getKey();
	}

	/**
	 * Get the key of the biggest node.
	 * The biggest node is the node with maximum key.
	 * Do not modify this function.
	 * @return
	 */
	public Key getLast() {
		return getLast(root);
	}

	/**
	 * Given a tree rt, get the key of the biggest node.
	 * @param rt
	 * @return the key of the biggest node. If the tree is empty, return null.
	 */
	private Key getLast(BinaryNode<Key, E> rt) {
		// TODO: Fill in this function
        if (rt.getRight() != null) return getLast(rt.getRight());
		return rt.getKey();
	}

	// Implement more functions HERE
}
