package com.finalprep.generaltree;

public class ListsOfChildren<E> {
    private ListsOfChildrenNode<E>[] array;

    public ListsOfChildren(ListsOfChildrenNode<E>[] array) {
        this.array = array;
    }

    public ListsOfChildrenNode<E> parent(int idx) {
        return array[array[idx].getParent()];
    }

    public ListsOfChildrenNode<E> leftMostChild(int idx) {
        return array[idx].getNext();
    }

    public ListsOfChildrenNode<E> rightSibling(int idx) {

        ListsOfChildrenNode<E> self = array[idx];

        int ptr = self.getParent();

        ListsOfChildrenNode<E> temp = array[ptr];
        while (temp.getNext() != null && temp != self)
            temp = temp.getNext();

        if (temp == self) return temp.getNext();
        else return null;
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

        if (size(root1) < size(root2))
            array[root1].setParent(root2);
        else array[root2].setParent(root1);
    }
}
