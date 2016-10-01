package art.of.programming.ds.generics;

import static org.junit.Assert.*;
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

}
