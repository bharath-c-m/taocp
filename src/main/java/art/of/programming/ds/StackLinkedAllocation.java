package art.of.programming.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

/**
 * A stack that uses linked allocation
 * 
 * @author ChinnaBX
 *
 */
public class StackLinkedAllocation {

	Node top;
	
	Node avail;
	
	Logger l = LoggerFactory.getLogger(StackLinkedAllocation.class);
	
	public StackLinkedAllocation(int size) {
		for(int i = 0; i < size; i++) {
			Node n = new Node();
			n.next = avail;
			n.val = i;
			avail = n;
		}
		l.info("Node is {}",avail);
	}
	
	public void push(int y) {
		if(avail == null)
			throw new StorageFullException("Stack is full");
		Node p = avail;
		avail = avail.next;
		p.val = y;
		p.next = top;
		top = p;
	}
	
	public int pop() {
		if(top == null)
			throw new StorageEmptyException("Empty stack");
		Node p = top;
		top = p.next;
		int y = p.val;
		p.next = avail;
		avail = p;
		return y;
	}
	
	
}
