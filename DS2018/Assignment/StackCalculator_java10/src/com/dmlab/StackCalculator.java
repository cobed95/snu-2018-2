package com.dmlab;

import com.dmlab.util.EmptyQueueException;
import com.dmlab.util.MyQueue;
import com.dmlab.util.MyStack;

import java.util.Arrays;

public class StackCalculator {
    private MyStack<String> stack;
    private MyQueue<String> queue;

	public StackCalculator() {
	    this.stack = new MyStack<String>();
	    this.queue = new MyQueue<String>();
	}
	
	/**
	 * Solve the given infix form of equation
	 * @param infix
	 * 			the infix form of an equation
	 * 			e.g. 1 + 2 - ( 3 - 4 )
	 * @return
	 * 			the result from the calculation of given equation
	 */
	public int solve(String infix) throws EmptyQueueException {
	    makePostfixWith(infix);
	    if (!this.stack.empty()) this.stack.clear();
	    while (!this.queue.empty()) {
            String polled = this.queue.poll();
	        if (polled.equals("+") || polled.equals("-")) {
	            int x = Integer.parseInt(this.stack.pop());
	            int y = Integer.parseInt(this.stack.pop());
	            if (polled.equals("+")) this.stack.push(Integer.toString(x + y));
	            else this.stack.push(Integer.toString(y - x));
            } else {
                this.stack.push(polled);
            }
        }
		return Integer.parseInt(this.stack.pop());
	}

	private void makePostfixWith(String infix) {
        String[] split = infix.split(" ");
        for (String item : split) {
            if (item.equals("+") || item.equals("-")) {
                while (!this.stack.empty() && !this.stack.peek().equals("(")) {
                    this.queue.add(this.stack.pop());
                }
                this.stack.push(item);
            } else if (item.equals("(")) {
                this.stack.push(item);
            } else if (item.equals(")")) {
                while (!this.stack.empty() && !this.stack.peek().equals("(")) {
                    this.queue.add(this.stack.pop());
                }
                if (this.stack.peek().equals("(")) this.stack.pop();
            } else {
                this.queue.add(item);
            }
        }
        while (!this.stack.empty()) {
            this.queue.add(this.stack.pop());
        }
    }
}
