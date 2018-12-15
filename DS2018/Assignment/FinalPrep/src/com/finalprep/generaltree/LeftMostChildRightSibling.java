package com.finalprep.generaltree;

public class LeftMostChildRightSibling<E> {
    private LMCRSNode<E>[] array;

    public LeftMostChildRightSibling(LMCRSNode<E>[] array) {
        this.array = array;
    }

    public LMCRSNode<E> parent(int idx) {
        return array[array[idx].getParent()];
    }

    public LMCRSNode<E> leftMostChild(int idx) {
        int ptr = array[idx].getLeftMostChild();
        if (ptr == -1) return null;
        else return array[ptr];
    }

    public LMCRSNode<E> rightSibling(int idx) {
        int ptr = array[idx].getRightSibling();
        if (ptr == -1) return null;
        return array[ptr];
    }

    public int find(int idx) {
        if (array[idx].getParent() == -1) return idx;
        array[idx].setParent(find(array[idx].getParent()));
        return array[idx].getParent();
    }

    public int size(int idx) {

        int count = 0;

        int root = find(idx);
        if (root == -1) root = idx;

        for (int i = 0; i < array.length; i++)
            if (root == find(i) || i == root)
                count++;

        return count;
    }

    public void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);

        if (root1 == root2) return;

        if (size(root1) < size(root2)) {
            array[root1].setParent(root2);
            array[root1].setRightSibling(array[root2].getLeftMostChild());
            array[root2].setLeftMostChild(root1);
        } else {
            array[root2].setParent(root1);
            array[root2].setRightSibling(array[root1].getLeftMostChild());
            array[root1].setLeftMostChild(root2);
        }
    }
}
