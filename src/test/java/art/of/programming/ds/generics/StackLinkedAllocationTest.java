package art.of.programming.ds.generics;

import static org.junit.Assert.*;
import org.junit.Test;

import art.of.programming.exception.StorageFullException;
import junit.framework.AssertionFailedError;

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
}
