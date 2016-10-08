package art.of.programming.ds.generics;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class CircularListTest {

	@Test
	public void testCircularList() throws Exception {
		CircularList<Integer> c = new CircularList<>(5);
		assertNotNull(c.avail);
		assertNotNull(c.avail.next.next.next.next);
		assertNull(c.avail.next.next.next.next.next);
	}

	@Test
	public void circularTestIntegrationTest() throws Exception {
		CircularList<Integer> c = new CircularList<>(5);
		assertNotNull(c.avail);
		assertNull(c.PTR);
		c.insertLeft(10);
		assertNotNull(c.PTR);
		assertNull(c.avail.next.next.next.next);
		assertSame(c.PTR, c.PTR.next);
		c.insertLeft(20);
		assertNotSame(c.PTR, c.PTR.next);
		assertEquals(20, c.PTR.next.value.intValue());
		assertEquals(10, c.PTR.value.intValue());
		
		c.insertRight(30);
		assertEquals(20, c.PTR.next.value.intValue());
		assertEquals(30, c.PTR.value.intValue());
		assertEquals(20, c.PTR.next.value.intValue());
		assertEquals(20,c.removeFromLeft().intValue());
		assertEquals(10, c.PTR.next.value.intValue());
		assertEquals(30, c.PTR.value.intValue());
	
		c.removeAll();
		assertNull(c.PTR);
		c.insertLeft(10);c.insertLeft(20);c.insertLeft(30);c.insertLeft(40);c.insertLeft(50);
		assertNull(c.avail);
		assertTrue(c.isFull());
		assertFalse(c.isEmpty());
		try {
			c.insertLeft(60);
			fail("Circular list must have been full");
		} catch (StorageFullException e) {
//			All is well
		}
		
		c.removeFromLeft();
		assertNotNull(c.avail);
		assertFalse(c.isFull());
		assertFalse(c.isEmpty());
		c.insertLeft(60);
		
		c.removeAll();
		assertNull(c.PTR);
		assertFalse(c.isFull());
		assertTrue(c.isEmpty());
		
		c.insertRight(10);c.insertRight(20);c.insertRight(30);c.insertRight(40);c.insertRight(50);
		assertNull(c.avail);
		assertTrue(c.isFull());
		assertFalse(c.isEmpty());
		try {
			c.insertRight(60);
			fail("Circular list must have been full");
		} catch (StorageFullException e) {
//			All is well
		}
		c.removeFromLeft();
		assertNotNull(c.avail);
		assertFalse(c.isFull());
		assertFalse(c.isEmpty());
		c.insertRight(60);
		
		c.removeAll();
		try {
			c.removeFromLeft();
			fail("Circular list must have been empty");
		} catch (StorageEmptyException e) {
//			All is well
		}
		
	}
	
	@Test
	public void circularListAsStack() {
		CircularList<Integer> c = new CircularList<>(5);
		c.insertLeft(10); //PUSH
		c.insertLeft(20);//PUSH
		assertEquals(20, c.removeFromLeft().intValue());//POP
		assertEquals(10, c.removeFromLeft().intValue());//POP
		try {
			c.removeFromLeft();
			fail("CircularList as Stack must have been empty");
		} catch(StorageEmptyException e) {
			//All is well
		}
		assertTrue(c.isEmpty());
		c.insertLeft(30);
		c.insertLeft(40);
		c.insertLeft(50);
		c.insertLeft(60);
		c.insertLeft(70);
		assertTrue(c.isFull());
		try {
			c.insertLeft(80);
			fail("CircularList as Stack must have been full");
		} catch(StorageFullException e) {
			//All is well
		}
		assertEquals(70, c.removeFromLeft().intValue());
		c.removeFromLeft();
		c.removeFromLeft();
		c.removeFromLeft();
		assertEquals(30, c.removeFromLeft().intValue());
		assertNull(c.PTR);
		assertTrue(c.isEmpty());
	}
	
	@Test
	public void circularListAsQueue() {
		CircularList<String> c = new CircularList<>(3);
		assertTrue(c.isEmpty());
		assertFalse(c.isFull());
		c.insertRight("A");
		c.insertRight("B");
		c.insertRight("C");
		assertTrue(c.isFull());
		assertFalse(c.isEmpty());
		try {
			c.insertRight("D");
			fail("CircularList as Queue must have been full");
		} catch (StorageFullException e) {
			//All is well
		}
		
		assertEquals("A", c.removeFromLeft());
		assertEquals("B", c.removeFromLeft());
		assertEquals("C", c.removeFromLeft());
		try {
			c.removeFromLeft();
			fail("CircularList as Queue must have been empty");
		} catch(StorageEmptyException e) {
			//All is well
		}
		
		assertTrue(c.isEmpty());
		assertFalse(c.isFull());
		
	}

	@Test
	public void testReverse() throws Exception {
		CircularList<Integer> c = new CircularList<>(5);
		c.insertLeft(1);c.insertLeft(2);c.insertLeft(3);c.insertLeft(4);c.insertLeft(5);
		Node<Integer> first = c.getFirst();
		assertEquals(1, first.value.intValue());
		assertEquals(5, first.next.value.intValue());
		assertEquals(4, first.next.next.value.intValue());
		assertEquals(3, first.next.next.next.value.intValue());
		assertEquals(2, first.next.next.next.next.value.intValue());
//		5-4-3-2-1->PTR
		
		c.reverse();
//		Expected
//		2-3-4-5-1-PTR
		
		first = c.getFirst();
		assertEquals(1, first.value.intValue());
		assertEquals(2, first.next.value.intValue());
		assertEquals(3, first.next.next.value.intValue());
		assertEquals(4, first.next.next.next.value.intValue());
		assertEquals(5, first.next.next.next.next.value.intValue());
	}
	
	@Test
	public void testPeek() throws Exception {
		CircularList<String> c = new CircularList<>(5);
		assertNull(c.peek());
		c.insertLeft("Hi");
		assertEquals("Hi", c.peek());
		c.insertLeft("Hello");
		assertEquals("Hi", c.peek());
		c.insertRight("How Are U!!");
		assertEquals("How Are U!!", c.peek());

	}

}
