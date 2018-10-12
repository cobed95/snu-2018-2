package com.dmlab;

public class BinaryTree<Key extends Comparable<? super Key>, E> {
	
	class Node {
		private Key mKey;
		private E mValue;
		
		private Node mLeft;
		private Node mRight;
		
		public Node(Key key, E value) {
			mKey = key;
			mValue = value;
			mLeft = null;
			mRight = null;
		}

		public Key getKey() {
			return mKey;
		}

		public E getValue() {
			return mValue;
		}
		
		/**
		 * Insert a node to the subtree, the root of which is this node.
		 * If the subtree already has node with the given key in the param,
		 * 		replace the value of the node in the subtree.
		 * Please compare keys using compareTo method.
		 * e.g. when "int compare = KEY0.compareTo(KEY1)"
		 * 		if compare == 0, this means KEY0 is equal to KEY1
		 * 		if compare > 0, KEY0 > KEY1
		 * 		if compare < 0, KEY0 < KEY1
		 * @param key
		 * @param value
		 * @return
		 * 			None
		 */
		public void insert(Key key, E value) {
			// TODO: Fill this function
            int compare = key.compareTo(this.mKey);
            if (compare == 0) this.mValue = value;
            else if (compare < 0 && this.mLeft != null) this.mLeft.insert(key, value);
            else if (compare < 0) this.mLeft = new Node(key, value);
            else if (this.mRight != null) this.mRight.insert(key, value);
            else this.mRight = new Node(key, value);
		}

		/**
		 * Find the value of item by the key in the subtree, the root of which is this node.
		 * @param key
		 * @return
		 * 			the value of item matched with the given key.
		 * 			null, if there is no matching node in this subtree.
		 */
		public E find(Key key) {
			// TODO: Fill this function
            int compare = key.compareTo(this.mKey);
            if (compare == 0) return this.mValue;
            else if (compare < 0 && this.mLeft != null) return this.mLeft.find(key);
            else if (compare > 0 && this.mRight != null) return this.mRight.find(key);
            else return null;
		}

		@Override
		public String toString() {
			return "[" + String.valueOf(mKey) + ":" + String.valueOf(mValue) + "]";
		}
		
		/**
		 * Traverse with the preorder in this subtree.
		 * @return
		 * 			The String to be printed-out which contains series of nodes as the preorder traversal.
		 */
		public String preorder() {
			// TODO: Fill this function
            return preorder("");
		}

        private String preorder(String result) {
            result += this.toString();
            if (this.mLeft != null) result = this.mLeft.preorder(result);
            if (this.mRight != null) result = this.mRight.preorder(result);
            return result;
        }
		
		/**
		 * Traverse with the inorder in this subtree.
		 * @return
		 * 			The String to be printed-out which contains series of nodes as the inorder traversal.
		 */
		public String inorder() {
			// TODO: Fill this function
            return inorder("");
		}

        private String inorder(String result) {
            if (this.mLeft != null) result = this.mLeft.inorder(result);
            result += this.toString();
            if (this.mRight != null) result = this.mRight.inorder(result);
            return result;
        }
		
		/**
		 * Traverse with the postorder in this subtree.
		 * @return
		 * 			The String to be printed-out which contains series of nodes as the postorder traversal.
		 */
		public String postorder() {
			// TODO: Fill this function
            return postorder("");
		}

        private String postorder(String result) {
            if (this.mLeft != null) result = this.mLeft.postorder(result);
            if (this.mRight != null) result = this.mRight.postorder(result);
            result += this.toString();
            return result;
        }

		/**
		 * Find the height of this subtree
		 * @return
		 * 			height
		 */
		public int height() {
			// TODO: Fill this function
            int leftHeight, rightHeight;
            if (this.mLeft != null && this.mRight != null) {
                leftHeight = this.mLeft.height();
                rightHeight = this.mRight.height();
            } else if (this.mLeft != null) {
                leftHeight = this.mLeft.height();
                rightHeight = 0;
            } else if (this.mRight != null) {
                leftHeight = 0;
                rightHeight = this.mRight.height();
            } else {
                leftHeight = 0;
                rightHeight = 0;
            }

            if (leftHeight >= rightHeight) return leftHeight + 1;
            else return rightHeight + 1;
		}
		
		public Node findMin() {
			if (mLeft == null)
				return this;
			else
				return mLeft.findMin();
		}
		
		/**
		 * Delete a node,the key of which match with the key given as param, from this subtree.
		 * You may need to define new method to find minimum of the subtree.
		 * @return
		 * 			the node of which parent needs to point after the deletion.
		 */
		public Node delete(Key key) {
			// TODO: Fill this function
            int compare = key.compareTo(this.mKey);
            if (compare == 0 && this.isLeaf()) return null;
            else if (compare == 0 && this.mLeft != null && this.mRight == null) {
                return this.mLeft;
            } else if (compare == 0 && this.mLeft == null && this.mRight != null) {
                return this.mRight;
            } else if (compare == 0) {
                Node min = this.mRight.getMin();
                Key newKey = min.getKey();
                E newValue = min.getValue();
                this.mRight = this.mRight.delete(newKey);
                this.mKey = newKey;
                this.mValue = newValue;
                return this;
            } else if (compare < 0 && this.mLeft != null) {
                this.mLeft = this.mLeft.delete(key);
                return this;
            } else if (compare > 0 && this.mRight != null) {
                this.mRight = this.mRight.delete(key);
                return this;
            } else {
                return null;
            }
		}

        private Node getMin() {
            if (this.mLeft == null) return this;
            else return this.mLeft.getMin();
        }

        public boolean isLeaf() {
            return this.mLeft == null && this.mRight == null;
        }
		
		/**
		 * Given a tree rt, key k1 and key k2, 
		 * find the lowest commen ancestor of k1 and k2 in tree rt.
		 * @param rt is the root of the tree
		 * @param k1 is the first key
		 * @param k2 is the second key
		 * @return the lowest commen ancestor node, if exists.
		 */
		public Node lowestCommonAncestor(Node rt, Key k1, Key k2) {
			// TODO: Fill this function
            if (k1.compareTo(rt.getKey()) < 0 && k2.compareTo(rt.getKey()) > 0
                    || k1.compareTo(rt.getKey()) > 0 && k2.compareTo(rt.getKey()) < 0)
                return rt;
            else if (k1.compareTo(rt.getKey()) < 0 && k2.compareTo(rt.getKey()) < 0)
                return lowestCommonAncestor(rt.mLeft, k1 , k2);
            else
                return lowestCommonAncestor(rt.mRight, k1, k2);
		}
		
	}
	
	private Node mRoot;
	public BinaryTree() {
		mRoot = null;
	}
	
	public void insert(Key key, E value) {
		if (mRoot == null) {
			mRoot = new Node(key, value);
		} else {
			mRoot.insert(key, value);
		}
	}
	
	public void delete(Key key) {
		if (mRoot == null)
			return;
		mRoot = mRoot.delete(key);
	}
	
	public E find(Key key) {
		if (mRoot == null)
			return null;
		return mRoot.find(key);
	}
	
	public void preorder() {
		System.out.print("preorder : ");
		if (mRoot == null) {
			System.out.println("None");
			return;
		}
		System.out.println(mRoot.preorder());
	}
	
	public void inorder() {
		System.out.print("inorder : ");
		if (mRoot == null) {
			System.out.println("None");
			return;
		}
		System.out.println(mRoot.inorder());
		
	}
	
	public void postorder() {
		System.out.print("postorder : ");
		if (mRoot == null) {
			System.out.println("None");
			return;
		}
		System.out.println(mRoot.postorder());
	}
	
	public int height() {
		if (mRoot == null)
			return 0;
		return mRoot.height();
	}
	
	public void lowestCommonAncestor(Key k1, Key k2) {
		if (mRoot == null)
			System.out.println("The tree is empty");
		else if (k1 == null || k2 == null) {
			System.out.println("Error, the key is null");
		} else if (mRoot.find(k1) != null && mRoot.find(k2) != null) {
			Node n = mRoot.lowestCommonAncestor(mRoot, k1, k2);
			System.out.println("LCA of "+k1+" and "+k2+" : ["+n.getKey()+":"+n.getValue()+"]");
		}
		else {
			System.out.println("The key doesnt exist");
		}
	}
}
