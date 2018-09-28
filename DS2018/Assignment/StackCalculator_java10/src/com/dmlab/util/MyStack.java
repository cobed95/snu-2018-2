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
	    this.array = new E[128];
        this.tail = 0;
	}

	@Override
	public void clear() {
		while (!this.empty()) this.pop();
	}

	@Override
	public void push(E item) throws RuntimeException {
	    try {
	        this.array[this.tail] = item;
        } catch (RuntimeException e) {
	        e.printStackTrace();
        }

	}

	@Override
	public E pop() throws EmptyStackException {
	    try {
	        this.tail--;
	        return this.array[this.tail];
        } catch (EmptyStackException e) {
	        e.printStackTrace();
	        return null;
        }
	}

	@Override
	public E peek() throws EmptyStackException {
	    try {
	        return this.array[this.tail--];
        } catch (EmptyStackException e) {
	        e.printStackTrace();
	        return null;
        }
	}

	@Override
	public boolean empty() {
	    if (this.tail == 0) return true;
		return false;
	}

}
