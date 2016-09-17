package art.of.programming.ds;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

/**
 * A simple queue that holds numbers.
 * 
 *  Uses sequential allocation bounded by the
 * size parameter passed into the consructor
 * 
 *
 */
public class Queue {

	int front = 0;
	int rear = 0;
	int x[];

	public Queue(int size) {
		x = new int[size];
	}

	public void offer(int y) {
		if (isFull()) {
			throw new StorageFullException("Queue is full");
		}
		rear += 1;
		x[rear] = y;
	}

	public int poll() {
		if (isEmpty()) {
			throw new StorageEmptyException("Queue is empty");
		}
		front += 1;
		int y = x[front];
		if (front == rear)
			front = rear = 0;
		return y;
	}

	public int peek() {
		return x[front + 1];
	}

	public boolean isEmpty() {
		return front == rear;
	}

	public boolean isFull() {
		return rear + 1 == x.length;
	}

}
