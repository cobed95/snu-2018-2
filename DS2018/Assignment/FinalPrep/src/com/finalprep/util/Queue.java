package com.finalprep.util;

import java.util.ArrayList;

public class Queue<E> {
    private int head;
    private int tail;
    private E[] list;

    public Queue(int n) {
        head = 0;
        tail = 0;
        list = (E[]) new Object[n];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (head == 0 && tail == list.length)
                || tail == head - 1;
    }

    public void enqueue(E data) {

        assert !this.isFull(): "Queue is full.";

        if (tail == list.length) tail = 0;

        list[tail] = data;
        tail++;
    }

    public E dequeue() {

        assert !this.isEmpty(): "Queue is empty.";

        if (head == list.length) head = 0;

        E dequeued = list[head];
        head++;

        return dequeued;
    }
}
