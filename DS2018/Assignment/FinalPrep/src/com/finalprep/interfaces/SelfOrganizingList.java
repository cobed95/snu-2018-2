package com.finalprep.interfaces;

public interface SelfOrganizingList<E> {
    public int search(E value);
    public void reorganize(int i);
}
