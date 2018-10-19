package com.dmlab;

import java.util.*;

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
		for (int i = 0; i < mID.length; i++) mID[i] = -1;
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
	    int size1 = groupSize(node1);
	    int size2 = groupSize(node2);
	    int root1 = find(node1);
	    int root2 = find(node2);

        if (root1 == root2 && root1 != -1) return;
        if (size1 < size2) mID[root1] = root2;
        else mID[root2] = root1;
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
        int depth1 = depth(node1);
        int depth2 = depth(node2);
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2 && root1 != -1) return;
        if (depth1 < depth2) mID[root1] = root2;
        else mID[root2] = root1;
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
	    if (mID[node] == -1) return node;
	    mID[node] = find(mID[node]);
		return mID[node];
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
		return depth(node, 0);
	}

	private int depth(int node, int count) {
	    if (mID[node] == -1) return count;
	    return depth(mID[node], count + 1);
    }
	
	/**
	 * the number of nodes inside the set where given node belong.
	 * @param node
	 * 			node number
	 * @return
	 * 			the number of nodes
	 */
	int groupSize(int node) {
	    int root = find(node);
	    for (int i = 0; i < mID.length; i++) if (mID[i] != root) find(i);

	    int count = 1;
	    for (int parent: mID) if (parent == root) count++;
	    return count;
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
		int root1 = find(node1);
		int root2 = find(node2);
	    return root1 == root2;
	}
	
	/**
	 * the number of groups in this union structure.
	 * @param
	 * @return
	 * 			the number of groups
	 */
	int numberOfGroups() {
	    int count = 0;
	    for (int element: mID) {
	        if (element == -1) count++;
        }
		return count;
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
        int root = find(0);
        for (int i = 1; i < mID.length; i++) {
            if (root == -1 || mID[i] != root) root = find(i);
        }

        boolean[] visited = new boolean[10];
        for (int j = 0; j < visited.length; j++) visited[j] = false;

        String result = "";

        int numberOfGroups = numberOfGroups();
        for (int i = 0; i < numberOfGroups; i++) {
            result += "(";

            int node = 0;
            while (node < 10 && visited[node]) node++;

            int groupRoot;
            switch (mID[node]) {
                case -1 :
                    groupRoot = node;
                    break;
                default :
                    groupRoot = mID[node];
            }
            result += Integer.toString(groupRoot);
            visited[groupRoot] = true;

            for (int j = 0; j < 10; j++) {
                if (visited[j]) continue;
                if (mID[j] == groupRoot) {
                    result += ",";
                    result += Integer.toString(j);
                    visited[j] = true;
                }
            }

            result += ")";
        }

        System.out.println(result);
	}

}
