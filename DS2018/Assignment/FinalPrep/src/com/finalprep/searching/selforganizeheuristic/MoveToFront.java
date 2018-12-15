package com.finalprep.searching.selforganizeheuristic;

import com.finalprep.interfaces.SelfOrganizingList;
import com.finalprep.util.Link;
import com.finalprep.util.SinglyLinkedList;

public class MoveToFront<E> implements SelfOrganizingList<E> {
    private E[] array;

    public MoveToFront(E[] array) {
        this.array = array;
    }

    public int search(E value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                reorganize(i);
                return i;
            }
        }
        return -1;
    }

    public void reorganize(int i) {
        E key = array[i];
        for (int j = 0; j < i; j++) {
            array[j + 1] = array[j];
        }
        array[0] = key;
    }
}
