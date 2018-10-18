package com.dmlab;

public class Union {
	
	int[] mID = new int[10];
	
	/**
	 * Constructor of this class
	 * If you have something to initialize, implement in here.
	 */
	Union() {
		/**
		 * Initialize mID field to be belonged in the different set.
		 */
	}
	
	/**
	 * Union two set where node1 and node2 is belong.
	 * Do nothing if node1 and node2 are in the same set.
	 * When union two set, you should follow the "weighted rule".
	 * @param node1, node2
	 * 			node numbers
	 * @return
	 */
	void unionWU(int node1, int node2) {
	}

	/**
	 * Union two set where node1 and node2 is belong.
	 * Do nothing if node1 and node2 are in the same set.
	 * When union two set, you should follow the "depth rule".
	 * @param node1, node2
	 * 			node numbers
	 * @return
	 */
	void unionDU(int node1, int node2) {
	}

	/**
	 * return the root of the given node.
	 * You should implement the path compression
	 * To do this, we recommend you implement it using recursive.
	 * @param node
	 * 			node number
	 * @return
	 * 			number of the root node where it belong.
	 */
	int find(int node) {
		return -1;
	}
	
	/**
	 * return the depth of the given node
	 * the depth of root is 0
	 * @param node
	 * 			node number
	 * @return
	 * 			depth
	 */
	int depth(int node) {
		return -1;
	}
	
	/**
	 * the number of nodes inside the set where given node belong.
	 * @param node
	 * 			node number
	 * @return
	 * 			the number of nodes
	 */
	int groupSize(int node) {
		return -1;
	}
	
	/**
	 * return true if node1 and node2 are in the same set
	 * return false otherwise
	 * @param node1, node2
	 * 			node numbers
	 * @return
	 * 			boolean value
	 */
	boolean inSameGroup(int node1, int node2) {
		return false;
	}
	
	/**
	 * the number of groups in this union structure.
	 * @param
	 * @return
	 * 			the number of groups
	 */
	int numberOfGroups() {
		return -1;
	}
	
	/**
	 * Print the elements of this union with following rules
	 * 
	 * When each node in the different sets,
	 * (0)(1)(2)(3)(4)(5)(6)(7)(8)(9)
	 * 
	 * Then if 1 and 2 are in the same set,
	 * (0)(1,2)(3)(4)(5)(6)(7)(8)(9)
	 * 
	 * We don't care about the order of set.
	 * Please just make sure that nodes in the same set represented by ()
	 * (0,4,5)(1,2,3)(6,7,8,9)
	 */
	void print() {

	}
}
