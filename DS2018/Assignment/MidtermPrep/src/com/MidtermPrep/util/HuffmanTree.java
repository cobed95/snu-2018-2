package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.HuffmanNode;

public class HuffmanTree<T> {
    private HuffmanNode<Integer, T> root;

    public HuffmanTree(KVpair<Integer, T>[] array) {
        sort(array);
        HuffmanNode<Integer, T>[] leaves = pairToLeaves(array);
        SinglyLinkedList<HuffmanNode<Integer, T>> linkedList = arrayToLinkedList(leaves);
        while (linkedList.length() > 1) {
            HuffmanNode<Integer, T> first = linkedList.remove();
            HuffmanNode<Integer, T> second = linkedList.remove();
            linkedList.moveToStart();
            linkedList.insert(new HuffmanInternal<Integer, T>(
                        first.getWeight() + second.getWeight(),
                            first,
                            second));
            place(linkedList);
        }
        linkedList.moveToStart();
        root = linkedList.getValue();
    }

    private HuffmanNode<Integer, T>[] pairToLeaves(KVpair<Integer, T>[] array) {
        HuffmanNode<Integer, T>[] leaves = (HuffmanNode<Integer, T>[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) leaves[i] = new HuffmanLeaf<Integer, T>(array[i].key(), array[i].value());
        return leaves;
    }

    public void sort(KVpair<Integer, T>[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(KVpair<Integer, T>[] array, int p, int r) {
        if (p < r) {
            System.out.println(p + ", " + r);
            int q = (p + r) / 2;
            sort(array, p, q);
            sort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }

    //TODO: Fix merge.
    private void merge(KVpair<Integer, T>[] array, int p, int q, int r) {
        KVpair<Integer, T>[] left = new KVpair<Integer, T>[q - p + 1];
        KVpair<Integer, T>[] right = (KVpair<Integer, T>[]) new Object[r - q];
        for (int i = p; i <= q; i++) left[i - p] = array[i];
        for (int j = q + 1; j <= r; j++) right[j - q - 1] = array[j];

        int i = 0;
        int j = 0;
        for (int k = p; k <= r; k++) {
            if (i < left.length && j >= right.length) {
                array[k] = left[i];
                i++;
            } else if (i >= left.length && j < right.length) {
                array[k] = right[j];
                j++;
            } else if (left[i].key().compareTo(right[j].key()) <= 0) {
                array[k] = left[i];
                i++;
            } else if (left[i].key().compareTo(right[j].key()) > 0) {
                array[k] = right[j];
                j++;
            }
        }
    }

    private SinglyLinkedList<HuffmanNode<Integer, T>> arrayToLinkedList(HuffmanNode<Integer, T>[] array) {
        SinglyLinkedList<HuffmanNode<Integer, T>> linkedList = new SinglyLinkedList<>();
        for (int i = 0; i < array.length; i++) linkedList.append(array[i]);
        return linkedList;
    }

    private void place(SinglyLinkedList<HuffmanNode<Integer, T>> linkedList) {
        linkedList.moveToStart();
        HuffmanNode<Integer, T> temp = linkedList.remove();
        while (temp.getWeight() > linkedList.getValue().getWeight()) {
            linkedList.next();
        }
        linkedList.insert(temp);
    }

    public DoublyLinkedList<KVpair<T, String>> encode() {
        DoublyLinkedList<KVpair<T, String>> result = new DoublyLinkedList<>();
        return encode(root, result, "");
    }

    private DoublyLinkedList<KVpair<T, String>> encode(HuffmanNode<Integer, T> root,
                                                       DoublyLinkedList<KVpair<T, String>> result,
                                                       String code) {
        if (root instanceof HuffmanLeaf)
            result.append(new KVpair<T, String>(((HuffmanLeaf<Integer, T>) root).getCharacter(), code));
        else {
            encode(((HuffmanInternal) root).getLeft(), result, code + 0);
            encode(((HuffmanInternal) root).getRight(), result, code + 1);
        }
        return result;
    }
}
