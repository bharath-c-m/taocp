package art.of.programming.ds;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class Stack {

	// A simple stack implementation that holds numbsers
	// Uses sequential allocation with an upper bound passed in as size to the
	// constructor of stack

	int x[];
	int top;
	Logger logger = LoggerFactory.getLogger(Stack.class);

	public Stack(int size) {
		logger.debug("initializing array of length - {}", size);
		this.x = new int[size];
	}

	public int pop() {
		if (top == 0) {
			throw new StorageEmptyException("No items in stack");
		}
		int y = x[top];
		top -= 1;
		logger.debug("pop - {} ", y);
		return y;
	}

	public void push(int y) {
		if (top + 1 == x.length) {
			throw new StorageFullException("Stack is full");
		}
		logger.debug("push {}", y);
		top += 1;
		x[top] = y;
	}

	public String toString() {
		return Arrays.toString(x);
	}

	public boolean isEmpty() {
		return top == 0;
	}
}
