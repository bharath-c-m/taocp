package art.of.programming.ds.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.ds.generics.StackLinkedAllocation;

/**
 * 
 * A simple parameterized binary tree implementation that exposes iterators
 * to traverse the tree in-order and pre-order.
 * 
 * @see BinaryTreeTest for examples on a simple binary tree
 * 
 * TODO Implement post-order
 * 
 */
public class BinaryTree<T> {

	Logger l = LoggerFactory.getLogger(BinaryTree.class);

	Node<T> T;

	public BinaryTree(Node<T> root) {
		this.T = root;
	}

	public Iterator<T> inOrderIterator() {
		return new InOrderIterator();
	}

	public Iterator<T> preOrderIterator() {
		return new PreOrderIterator();
	}
	
	public Iterator<T> postOrderIterator() {
		return new PostOrderIterator();
	}

	private class InOrderIterator implements Iterator<T> {

		// Assuming 100 nodes. Tree with more than 100 nodes will not work
		
		// This traversal doesnt take care of concurrent modifications, Although
		// it can be handled with little modifications
		
		StackLinkedAllocation<Node<T>> A = new StackLinkedAllocation<>(100);

		Node<T> P = T;

		@Override
		public boolean hasNext() {
			return !(A.isEmpty() && P == null);
		}

		@Override
		public T next() {
			T t = null;
			if (P == null) {
				if (A.isEmpty()) {
					throw new NoSuchElementException("No elements to traverse");
				} else {
					P = A.pop();
					t = P.visit();
					P = P.RLINK;
				}
			} else {
				A.push(P);
				P = P.LLINK;
				t = next();
			}
			return t;
		}

	}

	private class PreOrderIterator implements Iterator<T> {

		// Assuming 100 nodes. Tree with more than 100 nodes will not work
		
		// This traversal doesnt take care of concurrent modifications, Although
		// it can be handled with little modifications
		
		StackLinkedAllocation<Node<T>> A = new StackLinkedAllocation<>(100);

		Node<T> P = T;

		@Override
		public boolean hasNext() {
			return !(A.isEmpty() && P == null);
		}

		@Override
		public T next() {
			T t = null;
			if(P == null) {
				if(A.isEmpty()) {
					throw new NoSuchElementException("No elements to traverse");
				} else {
					P = A.pop();
					P = P.RLINK;
					t = next();
				}
			} else {
				t = P.visit();
				A.push(P);
				P = P.LLINK;
			}
		return t;
		}

	}
	
	private class PostOrderIterator implements Iterator<T> {

		@Override
		public boolean hasNext() {
			throw new RuntimeException("Implement");
		}

		@Override
		public T next() {
			throw new RuntimeException("Implement");
		}
		
	}
}
