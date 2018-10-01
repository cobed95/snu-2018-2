package com.dmlab.util;

import com.dmlab.interfaces.Queue;

/**
 * Array-based Queue
 * The size of the internal array should be 128
 */
public class MyQueue<E> implements Queue<E>{
	private E[] array;
	private int head;
	private int tail;

	public MyQueue() {
	    this.array = (E[]) new Object[128];
	    this.head = 0;
	    this.tail = 0;
	}

	@Override
	public void clear() {
	    this.head = 0;
	    this.tail = 0;
	}

	@Override
	public void add(E item) throws RuntimeException {
	    this.array[this.tail] = item;
	    if (this.tail == 127) this.tail = 0;
	    else this.tail++;
	}

	@Override
	public E poll() throws EmptyQueueException {
	    E result = this.array[this.head];
	    if (this.head == 127) this.head = 0;
	    else this.head++;
	    return result;
	}

	@Override
	public E peek() throws EmptyQueueException {
        return this.array[this.head];
	}

	@Override
	public boolean empty() {
	    if (this.head == this.tail) return true;
		return false;
	}
}
