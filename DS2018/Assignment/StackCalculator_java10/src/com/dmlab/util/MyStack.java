package com.dmlab.util;

import java.util.EmptyStackException;
import com.dmlab.interfaces.Stack;

/**
 * Array-based Stack
 * The size of the internal array should be 128
 */
public class MyStack<E> implements Stack<E> {
	private E[] array;
	private int tail;
	
	public MyStack() {
	    this.array = (E[]) new Object[128];
        this.tail = 0;
	}

	@Override
	public void clear() {
		while (!this.empty()) this.pop();
	}

	@Override
	public void push(E item) throws RuntimeException {
		this.array[this.tail] = item;
		this.tail++;
	}

	@Override
	public E pop() throws EmptyStackException {
		this.tail--;
		return this.array[this.tail];
	}

	@Override
	public E peek() throws EmptyStackException {
	    if (!this.empty()) return this.array[this.tail - 1];
	    else return null;
	}

	@Override
	public boolean empty() {
	    if (this.tail == 0) return true;
		return false;
	}
}
