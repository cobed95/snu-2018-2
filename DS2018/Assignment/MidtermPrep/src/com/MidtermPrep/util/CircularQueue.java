package com.MidtermPrep.util;

import com.MidtermPrep.interfaces.Queue;

public class CircularQueue<E> implements Queue<E> {
    private E[] array;
    private int head;
    private int tail;
    private int count;

    public CircularQueue(int size) {
        array = (E[]) new Object[size];
        head = tail = 0;
        count = 0;
    }

    public void clear() {
        head = tail = 0;
        count = 0;
    }

    public void enqueue(E element) {
        assert count < array.length : "Queue is full.";
        array[tail] = element;
        if (tail == array.length - 1) tail = 0;
        else tail++;
        count++;
    }

    public E dequeue() {
        assert count > 0 : "Queue is empty.";
        E dequeued = array[head];
        if (head == array.length - 1) head = 0;
        else head++;
        count--;
        return dequeued;
    }

    public E front() {
        assert count > 0 : "Queue is empty.";
        return array[head];
    }

    public E rear() {
        assert count > 0: "Queue is empty.";
        return array[tail - 1];
    }

    public int length() {
        return count;
    }

    public String toString() {
        String result = "";
        int ptr = head;
        if (count != 0) {
            result += "[" + array[ptr].toString();
            if (ptr == array.length - 1) ptr = 0;
            else ptr++;
        }
        while (ptr != tail) {
            result += ", " + array[ptr].toString();
            if (ptr == array.length - 1) ptr = 0;
            else ptr++;
            if (ptr == tail) result += "]";
        }
        return result;
    }
}
