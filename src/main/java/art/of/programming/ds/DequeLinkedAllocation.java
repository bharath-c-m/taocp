package art.of.programming.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class DequeLinkedAllocation {
	DoubleNode avail; //Storage pool
	
	DoubleNode front;
	DoubleNode rear;
	
	Logger l = LoggerFactory.getLogger(DequeLinkedAllocation.class);
	
	public DequeLinkedAllocation(int size) {
		//Provision storage pool on initialization
		for(int i=0; i<size; i++) {
			DoubleNode n = new DoubleNode();
			if(avail == null) {
				avail = n;
			} else {
				avail.prev = n;
				n.next = avail;
				avail = n;
			}
		}
	}
	
	public void offerFront(int y) {
		DoubleNode p = getObjectFromStoragePool();
		if(p == null)
			throw new StorageFullException("Deque is full");
		p.val = y;
		p.next = front;
		front = p;
		if(rear == null) {
			rear = front;
		} 
	}
	
	public int pollFront() {
		if(isEmpty()) {
			throw new StorageEmptyException("Deque is empty");
		}
		DoubleNode p = front;
		int y = p.val;
		front = p.next;
		p.next = avail;
		if(front == null) {
			rear = null;
		}
		returnObjectToStoragePool(p);
		return y;
	}
	
	public void offerRear(int y) {
		DoubleNode p = getObjectFromStoragePool();
		if(p == null)
			throw new StorageFullException("Deque is full");
		p.val = y;
		p.prev = rear;
		rear = p;
		if(front == null) {
			front = rear;
		}
	}
	
	public int pollRear() {
		if(isEmpty())
			throw new StorageEmptyException("Deque is empty");
		DoubleNode p = rear;
		int y = p.val;
		rear = p.prev;
		if(rear == null) {
			front = null;
		}
		returnObjectToStoragePool(p);
		return y;
	}
	
	public int peekFront() {
		if(!isEmpty()) {
			return front.val;
		} return -1;
	}
	
	public int peekRear() {
		if(!isEmpty()) {
			return rear.val;
		} return -1;
	}
	
	public boolean isEmpty() {
		return front == null && rear == null;
	}
	
	public boolean isFull() {
		return avail == null;
	}
	
	public void returnObjectToStoragePool(DoubleNode p) {
		//The below code always inserts a node at the start
		if(avail != null) {
			avail.prev = p;
		}
		p.next = avail;
		avail = p;
	}
	
	public DoubleNode getObjectFromStoragePool() {
		//The below code always returns the node at the top
		DoubleNode p = avail;
		if(p != null) {
			avail = p.next;
			p.next = p.prev = null;
		}
		return p;
	}
}
