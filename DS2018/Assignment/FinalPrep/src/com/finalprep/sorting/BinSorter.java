package com.finalprep.sorting;

import com.finalprep.interfaces.List;
import com.finalprep.interfaces.Sorter;
import com.finalprep.util.Pair;
import com.finalprep.util.SinglyLinkedList;

public class BinSorter<V> implements Sorter<Integer, V> {

    public void sort(Pair<Integer, V>[] array) {
        Integer maxKey = getMaxKey(array);
        List<Pair<Integer, V>>[] bin =
                (SinglyLinkedList<Pair<Integer, V>>[]) new SinglyLinkedList[maxKey + 1];

        for (int i = 0; i <= maxKey; i++)
            bin[i] = new SinglyLinkedList<Pair<Integer, V>>();

        for (int i = 0; i < array.length; i++)
            bin[array[i].getKey()].append(array[i]);

        Pair<Integer, V> item;
        int ptr = 0;
        for (int i = 0; i < maxKey + 1; i++) {
            for (bin[i].moveToStart();
                 (item = bin[i].getValue()) != null;
                 bin[i].next()) {
                array[ptr] = item;
                ptr++;
            }
        }
    }

    public Integer getMaxKey(Pair<Integer, V>[] array) {

        Integer maxKey = array[0].getKey();
        for (int i = 0; i < array.length; i++) {
            if (array[i].getKey().compareTo(maxKey) > 0)
                maxKey = array[i].getKey();
        }

        return maxKey;
    }
}
