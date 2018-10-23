package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.Stack;

public class ArrayStack<E> implements Stack<E> {
    private int maxSize;
    private int top;
    private E[] listArray;

    public ArrayStack(int size) {
        maxSize = size;
        top = 0;
        listArray = (E[]) new Object[size];
    }

    public void clear() {
        top = 0;
    }

    public void push(E element) {
        assert top < maxSize : "Stack is full.";
        listArray[top] = element;
        top++;
    }

    public E pop() {
        assert top > 0 : "Stack is empty.";
        E popped = listArray[top - 1];
        top--;
        return popped;
    }

    public E peak() {
        assert top > 0 : "Stack is empty.";
        return listArray[top - 1];
    }

    public int length() {
        return top;
    }

    public String toString() {
        String result = "";
        if (top > 0) {
            result += "[" + listArray[top - 1];
            for (int i = top - 2; i >= 0; i--) {
                result += "," + listArray[i].toString();
            }
            result += "]";
        }
        return result;
    }
}
