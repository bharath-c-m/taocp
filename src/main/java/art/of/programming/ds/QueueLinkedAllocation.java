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
		//Oveflow is handled in getNodeFromStoragePool() api call.
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
	
	//Make the queue a deque
	//chapter 2.2.3 Exercise 5
	//Adding API offerFront(int y)
	public void offerFront(int y) {
		Node p = getNodeFromStoragePool();
		p.val = y;
		p.next = null; //Cleaning up p
		if(isEmpty()) {
			front = rear = p;
		} else {
			p.next = front;
			front = p;
		}
	}
	

	//Make the queue a deque
	//chapter 2.2.3 Exercise 5
	//Adding API pollRear()
	//This is terribly in-efficient with a (single)linked list. 
	// A double linked list will compromise on storage, but is an efficient way
	// in terms of performance
	public int pollRear() {
		if(isEmpty())
			throw new StorageEmptyException("Queue is empty");
		Node p = rear;
		int y = p.val;
		Node n = front;
		//starting to walk through the nodes to get the penultimate node
		//And set it to rear
		if(n.next == null) {
			//The queue has got only a single node
			front = rear = null;
		} else {
			while(n.next.next != null) {
				n = n.next;
			}
			rear = n;
			rear.next = null;
		}
		returnNodeToStoragePool(p);
		return y;
	}
	
}
