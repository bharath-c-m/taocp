package art.of.programming.ds.generics;

import static org.junit.Assert.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import art.of.programming.exception.StorageFullException;

public class StackLinkedAllocationTest {

	@Test
	public void testStackLinkedAllocation() throws Exception {
		StackLinkedAllocation<String> s = new StackLinkedAllocation<>(5);
		assertNotNull(s.avail);
		assertNotNull(s.avail.next.next.next.next);
		assertNull(s.avail.next.next.next.next.next);
	}

	@Test
	public void testStackLinkedAllocationIntegrationTest() throws Exception {
		StackLinkedAllocation<String> s = new StackLinkedAllocation<>(5);
		assertNull(s.top);
		s.push("the");
		assertNotNull(s.top);
		assertEquals("the", s.pop());
		assertNull(s.top);
		assertTrue(s.isEmpty());
		assertFalse(s.isFull());
		s.push("art");
		s.push("of");
		s.push("algortihms");
		s.push("takes");
		s.push("you");
		assertNotNull(s.top);
		assertTrue(s.isFull());
		assertFalse(s.isEmpty());
		try {
			s.push("away");
			fail("Stack must have been full");
		} catch(StorageFullException e) {
			//All is well
		}
		assertEquals("you", s.pop());
		assertFalse(s.isFull());
		s.push("away");
		
	}

	@Test
	public void testIterator() throws Exception {
		StackLinkedAllocation<Integer> s = new StackLinkedAllocation<>(5);
		Iterator<Integer> it = s.iterator();
		assertFalse(it.hasNext());
		s.push(10);
		it = s.iterator();
		assertTrue(it.hasNext());
		assertEquals(10, it.next().intValue());
		assertFalse(it.hasNext());
		s.push(20);
		try {
			it.hasNext();
			fail("Expected ConcurrentModificationException");
		} catch (ConcurrentModificationException e) {
			//All is well
		}it = s.iterator();
		assertTrue(it.hasNext());
		s.push(30);
		try {
			it.next();
			fail("Expected ConcurrentModificationException");
		} catch (ConcurrentModificationException e) {
			//All is well
		}
		it = s.iterator();
		assertEquals(30, it.next().intValue());
		assertEquals(20, it.next().intValue());
		assertEquals(10, it.next().intValue());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("Expected NoSuchElementException");
		} catch (NoSuchElementException e) {
			//All is well
		}
	}
}
