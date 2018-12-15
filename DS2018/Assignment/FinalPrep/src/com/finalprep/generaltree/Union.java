package com.finalprep.generaltree;

public class Union {
    private Integer[] parentPointer;

    public Union(Integer[] parentPointer) {
        this.parentPointer = parentPointer;
    }

    public Integer find(int curr) {
        if (parentPointer[curr] == null) return curr;
        parentPointer[curr] = find(parentPointer[curr]);
        return parentPointer[curr];
    }

    public boolean differ(int a, int b) {
        return find(a) != find(b);
    }

    public int size(int root) {
        int count = 0;
        for (int i = 0; i < parentPointer.length; i++)
            if (i == root || find(parentPointer[i]) == root)
                count++;
        return count;
    }

    public void union(int a, int b) {

        int root1 = find(a);
        int root2 = find(b);

        if (root1 == root2) return;

        if (size(root1) < size(root2))
            parentPointer[root1] = root2;
        else parentPointer[root2] = root1;
    }
}
