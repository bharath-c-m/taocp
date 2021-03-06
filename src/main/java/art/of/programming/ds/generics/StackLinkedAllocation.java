package art.of.programming.ds.generics;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class StackLinkedAllocation<T> {

	Node<T> top;
	
	Node<T> avail;//Storage pool
	
	Logger l = LoggerFactory.getLogger(StackLinkedAllocation.class);
	
	public StackLinkedAllocation(int size) {
		while(size-->0) {
			Node<T> p = new Node<T>();
			p.next = avail;
			avail = p;
		}
	}
	
	public void push(T item) {
		Node<T> p = getNodeFromStorage(); //Overflow is handled in this call
		p.value = item;
		if(top == null) {
			top = p;
		} else {
			p.next = top;
			top = p;
		}
	}
	
	public T pop() {
		if(isEmpty()) {
			throw new StorageEmptyException("Stack is empty");
		}
		Node<T> p = top;
		T item = p.value;
		top = p.next;
		returnNodeToStorage(p);
		return item;
	}
	
	public boolean isFull() {
		return avail == null;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
//	P <= AVAIL {P=AVAIL; AVAIL=AVAIL.NEXT}
	public Node<T> getNodeFromStorage() {
		if(avail == null) {
			throw new StorageFullException("Stack is full ");
		}
		Node<T> p = avail;
		avail = avail.next;
		p.next = null; //Cleaning up 'p'
		return p;
	}

//	AVAIL <= P {P.NEXT = AVAIL; AVAIL=P}
	public void returnNodeToStorage(Node<T> p) {
		p.next = avail;
		avail = p;
	}
	
	public Iterator<T> iterator() {
		return new StackLinkedAllocationIterator();
	}
	
	private class StackLinkedAllocationIterator implements Iterator<T> {

		/**
		 * A simple iterator that keeps track of updates 
		 * and throws exception on iterators whose underlying data is updated
		 */
		Node<T> localTopAtTheTimeOfIteratorInstatiation = top;
		
		Node<T> curr = top; //Node for iteration
		
		@Override
		public boolean hasNext() {
			if(top!=localTopAtTheTimeOfIteratorInstatiation)
				throw new ConcurrentModificationException("Stack updated");
			return curr!=null;
		}

		@Override
		public T next() {
			if(top!=localTopAtTheTimeOfIteratorInstatiation)
				throw new ConcurrentModificationException("Stack updated");
			if(curr == null) {
				throw new NoSuchElementException("Stack is empty");
			}
			T t = curr.value;
			curr = curr.next;
			return t;
		}
		
	}
	
	

}
