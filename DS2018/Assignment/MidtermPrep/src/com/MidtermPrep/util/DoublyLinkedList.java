package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.List;

public class DoublyLinkedList<E> implements List<E> {
    private DoubleLink<E> head;
    private DoubleLink<E> tail;
    private DoubleLink<E> curr;
    private int count;

    public DoublyLinkedList(int size) {
        this();
    }

    public DoublyLinkedList() {
        head = curr = new DoubleLink<E>(null, null);
        tail = new DoubleLink<E>(curr, null);
        curr.setNext(tail);
        count = 0;
    }

    public void clear() {
        head = curr = new DoubleLink<E>(null, null);
        tail = new DoubleLink<E>(curr, null);
        curr.setNext(tail);
        count = 0;
    }

    public void insert(E element) {
        curr.setNext(new DoubleLink<E>(element, curr, curr.next()));
        curr.next().next().setPrev(curr.next());
        count++;
    }

    public void append(E element) {
        tail.prev().setNext(new DoubleLink<E>(element, tail.prev(), tail));
        tail.setPrev(tail.prev().next());
        count++;
    }

    public E remove() {
        if (curr.next() == null) return null;
        E removed = curr.next().element();
        curr.next().next().setPrev(curr);
        curr.setNext(curr.next().next());
        count--;
        return removed;
    }

    public void moveToStart() {
        curr = head;
    }

    public void moveToEnd() {
        curr = tail.prev();
    }

    public void prev() {
        if (curr == head) return;
        curr = curr.prev();
    }

    public void next() {
        if (curr.next() == tail) return;
        curr = curr.next();
    }

    public int length() {
        return count;
    }

    public int currPos() {
        DoubleLink<E> temp = head;
        int i;
        for (i = 0; curr != temp; i++) temp = temp.next();
        return i;
    }

    public void moveToPos(int idx) {
        assert idx >= 0 && idx < count : "Illegal list position.";
        DoubleLink<E> temp = head;
        int tempIdx = 0;
        while (tempIdx < idx) {
            temp = temp.next();
            tempIdx++;
        }
        curr = temp;
    }

    public E getValue() {
        assert curr.next() != null : "Nothing to get.";
        return curr.next().element();
    }

    public String toString() {
        return toString(head, "");
    }

    private String toString(DoubleLink<E> node, String result) {
        if (node.next() == tail) return result;
        if (node == head) result += "[" + node.next().element().toString();
        else {
            result += ", " + node.next().element().toString();
            if (node.next().next() == tail) result += "]";
        }
        return toString(node.next(), result);
    }
}
