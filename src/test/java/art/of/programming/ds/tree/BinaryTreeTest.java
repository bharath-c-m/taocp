package art.of.programming.ds.tree;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import art.of.programming.ds.tree.BinaryTree;

public class BinaryTreeTest {
	
	@Test
	public void testBinaryTree() {
		BinaryTree<String> tree = getTestTree();
		assertNotNull(tree.T);
		assertEquals("A", tree.T.t);
	}

	@Test
	public void testInOrderIterator() throws Exception {
		
		BinaryTree<String> tree = getTestTree();
		
		Iterator<String> inOrderIt = tree.inOrderIterator();
		
		assertNotNull(inOrderIt);
		
		assertTrue(inOrderIt.hasNext());
		assertEquals("D", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("B", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("A", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("E", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("G", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("C", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("H", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("F", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("J", inOrderIt.next());
		
		try {
			inOrderIt.next();
			fail("Expected NoSuchElementException");
		} catch (NoSuchElementException e) {
			//All is well
		}
		assertFalse(inOrderIt.hasNext());
	}
	
	
	@Test
	public void testInOrderIteratorSimple() throws Exception {
		
		BinaryTree<String> tree = getSimpleTestTree();
		Iterator<String> inOrderIt = tree.inOrderIterator();

		assertNotNull(inOrderIt);
		
		assertTrue(inOrderIt.hasNext());
		assertEquals("B", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("A", inOrderIt.next());
		assertTrue(inOrderIt.hasNext());
		assertEquals("C", inOrderIt.next());
		
		try {
			inOrderIt.next();
			fail("Expected NoSuchElementException");
		} catch (NoSuchElementException e) {
			//All is well
		}
		assertFalse(inOrderIt.hasNext());
	}
	
	private BinaryTree<String> getTestTree() {
		Node<String> root = getNode("A");
		Node<String> b = getNode("B");
		Node<String> c = getNode("C");
		Node<String> d = getNode("D");
		Node<String> e = getNode("E");
		Node<String> f = getNode("F");
		Node<String> g = getNode("G");
		Node<String> h = getNode("H");
		Node<String> j = getNode("J");
		
		root.LLINK = b; root.RLINK = c;
		
		b.LLINK = d;
		
		c.LLINK = e;
		c.RLINK = f;
		
		e.RLINK = g;
		
		f.LLINK = h;
		f.RLINK = j;
		
		BinaryTree<String> tree = new BinaryTree<>(root);
		return tree;
	}
	
	private BinaryTree<String> getSimpleTestTree() {
		
		/**
		 * 			   A
		 * 			  / \
		 * 			B	  C
		 */
		
		/**
		 * In-Order traversal iteration
		 *	B-A-C 
		 */
		
		/**
		 * Pre-Order traversal iteration
		 * A-B-C
		 */
		
		Node<String> root = getNode("A");
		Node<String> b = getNode("B");
		Node<String> c = getNode("C");
		
		root.LLINK = b; root.RLINK = c;
		
		BinaryTree<String> tree = new BinaryTree<>(root);
		return tree;
	}
	
	private Node<String> getNode(String s) {
		Node<String> x = new Node<>();
		x.t = s;
		return x;
		
	}

	@Test
	public void testPreOrderIteratorSimple() throws Exception {
		BinaryTree<String> tree = getSimpleTestTree();
		Iterator<String> preOrderIt = tree.preOrderIterator();

		assertNotNull(preOrderIt);
		
		assertTrue(preOrderIt.hasNext());
		assertEquals("A", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("B", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("C", preOrderIt.next());
		
		try {
			preOrderIt.next();
			fail("Expected NoSuchElementException");
		} catch (NoSuchElementException e) {
			//All is well
		}
		assertFalse(preOrderIt.hasNext());
	}
	
	@Test
	public void testPreOrderIterator() throws Exception {
		
		BinaryTree<String> tree = getTestTree();
		
		Iterator<String> preOrderIt = tree.preOrderIterator();
		
		assertNotNull(preOrderIt);
		
		assertTrue(preOrderIt.hasNext());
		assertEquals("A", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("B", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("D", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("C", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("E", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("G", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("F", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("H", preOrderIt.next());
		assertTrue(preOrderIt.hasNext());
		assertEquals("J", preOrderIt.next());
		
		try {
			preOrderIt.next();
			fail("Expected NoSuchElementException");
		} catch (NoSuchElementException e) {
			//All is well
		}
		assertFalse(preOrderIt.hasNext());
	}

}
