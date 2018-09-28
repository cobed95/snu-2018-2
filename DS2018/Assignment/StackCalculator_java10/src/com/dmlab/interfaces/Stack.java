package com.dmlab.interfaces;

import java.util.EmptyStackException;

public interface Stack<E> {
	/**
	 * Removes all of the elements in this stack
	 */
	public void clear();
	
	/**
	 * Pushed an item onto the top of this stack
	 * @param	item
	 * 			the item to be pushed
	 * @exception	RuntimeException
	 * 				exceed the limitation of the internal array space
	 */
	public void push(E item) throws RuntimeException;
	
	/**
	 * Removes the item at the top of this stack and returns
	 * @return
	 * 			the value of the item be popped
	 * @exception	EmptyStackException
	 * 				called when stack is empty
	 */
	public E pop() throws EmptyStackException;
	
	/**
	 * Looks at the item at the top of this stack without removing it from the stack
	 * @return
	 * 			the value of the item at the top
	 * @exception	EmptyStackException
	 * 				called when stack is empty
	 */
	public E peek() throws EmptyStackException;
	
	/**
	 * Test if this stack is empty
	 * @return
	 * 			true if and only if this stack contains no items; false otherwise
	 */
	public boolean empty();
}
