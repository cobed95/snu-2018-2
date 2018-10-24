package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.Stack;

public class LinkedStack<E> implements Stack<E> {
    private Link<E> top;
    private int count;

    public LinkedStack() {
        top = new Link<E>(null);
        count = 0;
    }

    public void clear() {
        top = new Link<E>(null);
        count = 0;
    }

    public void push(E element) {
        Link<E> temp = new Link<E>(element, top);
        top = temp;
        count++;
    }

    public E pop() {
        if (top.element() == null) return null;
        E popped = top.element();
        top = top.next();
        count--;
        return popped;
    }

    public E peak() {
        return top.element();
    }

    public int length() {
        return count;
    }

    public String toString() {
        String result = "";
        Link<E> temp = top;
        if (temp.element() != null) {
            result += "[" + temp.element().toString();
            temp = temp.next();
        }
        while (temp.element() != null) {
            result += ", " + temp.element().toString();
            if (temp.next().element() == null) result += "]";
            temp = temp.next();
        }
        return result;
    }
}
