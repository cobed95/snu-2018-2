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
	    this.array = new E[128];
	    this.head = 0;
	    this.tail = 0;
	}

	@Override
	public void clear() {
		while (!this.empty()) this.poll();
	}

	@Override
	public void add(E item) throws RuntimeException {
		
	}

	@Override
	public E poll() throws EmptyQueueException {
		return null;
	}

	@Override
	public E peek() throws EmptyQueueException {
		return null;
	}

	@Override
	public boolean empty() {
	    if (this.head == this.tail) return true;
		return false;
	}

}
