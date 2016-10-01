package art.of.programming.ds.generics;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class CircularList<T> {

	Node<T> avail; //Storage pool stack
	
	Node<T> PTR;
	
	public CircularList(int size) {
		while(size-->0) {
			Node<T> n = new Node<T>();
			n.next = avail;
			avail = n;
		}
	}
	
	//Insert left and set p to be ptr.next
	//Visualize it as a circle rather than a line
	public void insertRight(T item) {
		insertLeft(item);
		PTR = PTR.next;
	}
	
	public void insertLeft(T item) {
		Node<T> p = getNodeFromStoragePool(); //Will throw exception when overflow
		p.value = item;
		if(isEmpty()) {
			PTR = p;
		} else {
			p.next = PTR.next;
		}
		PTR.next = p;
	}
	
	public T removeFromLeft() {
		if(isEmpty()) {
			throw new StorageEmptyException("Circular list is empty");
		}
		
		Node<T> p = PTR.next;
		PTR.next = p.next;
		T y = p.value;
		returnNodeToStoragePool(p);
		return y;
	}
	
	public boolean isEmpty() {
		return PTR==null;
	}
	
	public boolean isFull() {
		return avail == null;
	}
	
//	P <= AVAIL {p = AVAIL; AVAIL=AVAIL.next}
	public Node<T> getNodeFromStoragePool() {
		if(avail == null)
			throw new StorageFullException("Circular List is full");
		
		Node<T> p = avail;
		avail = avail.next;
		
		p.next = null; //Cleaning up p
		p.value = null;//Cleaning up p
		return p;
	}
	
//	AVAIL <= P {P.NEXT = AVAIL; AVAIL=P}
	public void returnNodeToStoragePool(Node<T> p) {
		if(p != null) {
			p.next = avail;
			avail = p;
		}
	}
	
//	AVAIL <-> LINK(PTR) {AVAIL=p; AVAIL=LINK(PTR); LINK(PTR)=P}
	public void removeAll() {
		if(PTR != null) {
			Node<T> p = avail;
			avail = PTR.next;
			PTR.next = p;
			PTR = null;
		}
	}
}
