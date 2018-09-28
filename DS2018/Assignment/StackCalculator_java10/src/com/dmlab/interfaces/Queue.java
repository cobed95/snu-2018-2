package com.dmlab.interfaces;

import com.dmlab.util.EmptyQueueException;

public interface Queue<E> {

	/**
	 * Removes all of items in this queue
	 */
	void clear();
	
	/**
	 * Inserts an item into this queue
	 * @param	item
	 * 			the item to be added
	 * @exception	RuntimeException
	 * 				exceed the limitation of the internal array space
	 */
	void add(E item) throws RuntimeException;
	
	/**
	 * Removes the item at the head of this queue and returns
	 * @return
	 * 			Retrieved item
	 * @exception	EmptyQueueException
	 * 				called when this queue is empty
	 */
	E poll() throws EmptyQueueException;
	
	/**
	 * Looks at the item at the head of this stack without removing it
	 * @return
	 * 				the item at the head
	 * @exception	EmptyQueueException
	 * 				called when this queue is empty
	 */
	E peek() throws EmptyQueueException;

	/**
	 * Test if this queue is empty
	 * @return
	 * 			true if and only if this queue contains no items; false otherwise
	 */
	boolean empty();
}
