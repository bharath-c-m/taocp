package art.of.programming.ds;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class QueueLinkedAllocation {

	Node avail; //Storage pool
	
	Node front;
	
	Node rear;
	
	public QueueLinkedAllocation(int size) {
		for(int i=0; i<size; i++) {
			Node p = new Node();
			p.next = avail;
			avail = p;
		}
	}
	
	public void offer(int y) {
		//Oveflow is handled in this api call.
		Node p = getNodeFromStoragePool(); 
		p.val = y;
		p.next = null; //Cleaning up p
		if(rear != null) {
			rear.next = p;
			rear = p;
		}
		else {
			rear = front = p;
		}
	}

	public int poll() {
		if(front == null)
			throw new StorageEmptyException("Queue is empty");
		
		Node p = front;
		int y = p.val;
		front = p.next;
		if(front == null) {
			rear = null;
		}
		returnNodeToStoragePool(p);
		return y;
	}

	public int peek() {
		if(isEmpty())
			throw new StorageEmptyException("Queue is empty");
		return front.val;
	}

	public boolean isEmpty() {
		return front==null;
	}

	public boolean isFull() {
		return avail == null;
	}
	
	//P <= AVAIL
	public Node getNodeFromStoragePool() {
		if(avail == null)
			throw new StorageFullException("Queue is full");
		
		Node p = avail;
		avail = avail.next;
		return p;
	}
	
//	AVAIL <== P
	public void returnNodeToStoragePool(Node p) {
		if(p!=null) {
			p.next = avail;
			avail = p;
		}
	}
}
