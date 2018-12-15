package com.finalprep.util;

import com.finalprep.interfaces.List;

public class SinglyLinkedList<E> implements List<E> {
    private Link<E> head;
    private Link<E> tail;
    private Link<E> curr;
    private int count;

    public SinglyLinkedList(int size) {
        this();
    }

    public SinglyLinkedList() {
        head = curr = tail = new Link<E>(null);
        count = 0;
    }

    public void clear() {
        head = curr = tail = new Link<E>(null);
        count = 0;
    }

    public void insert(E element) {
        curr.setNext(Link.get(element, curr.next()));
        if (curr == tail) tail = curr.next();
        count++;
    }

    public void append(E element) {
        tail.setNext(Link.get(element, null));
        tail = tail.next();
        count++;
    }

    public E remove() {
        if (curr.next() == null) return null;
        E removed = curr.next().element();
        if (tail == curr.next()) tail = curr;
        Link<E> tempptr = curr.next();
        curr.setNext(curr.next().next());
        tempptr.release();
        count--;
        return removed;
    }

    public void moveToStart() {
        curr = head;
    }

    public void moveToEnd() {
        curr = tail;
    }

    public void prev() {
        if (curr == head) return;
        Link<E> temp = head;
        while (temp.next() != curr) temp = temp.next();
        curr = temp;
    }

    public void next() {
        if (curr == tail) return;
        curr = curr.next();
    }

    public int length() {
        return count;
    }

    public int currPos() {
        Link<E> temp = head;
        int i;
        for (i = 0; curr != temp; i++) temp = temp.next();
        return i;
    }

    public void moveToPos(int idx) {
        assert idx >= 0 && idx < count : "Illegal list position.";
        Link<E> temp = head;
        int tempIdx = 0;
        while (tempIdx < idx) {
            temp = temp.next();
            tempIdx++;
        }
        curr = temp;
    }

    public E getValue() {
        assert curr.next() != null : "Nothing to get.";
        if (curr.next() == null) return null;
        return curr.next().element();
    }

    public String toString() {
        return toString(head, "");
    }

    private String toString(Link<E> node, String result) {
        if (node == tail) return result;
        if (node == head) result += "[" + node.next().element().toString();
        else {
            result += ", " + node.next().element().toString();
            if (node.next() == tail) result += "]";
        }
        return toString(node.next(), result);
    }
}
