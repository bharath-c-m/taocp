package art.of.programming.ds;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class QueueLinkedAllocationTest {

	@Test
	public void qLinkedAllocationIntegrationTest() {
		QueueLinkedAllocation q = new QueueLinkedAllocation(5);
		//Assert initial state
		assertNotNull("avail must be not-null upon init", q.avail);
		assertNotNull("avail must have 5 non null nodes upon init",q.avail.next.next.next.next);
		assertNull("avail must have 6th node as null upon init", q.avail.next.next.next.next.next);
		assertNull("q.front should be null upon init", q.front);
		assertNull("q.rear should be null upon init", q.rear);
		
		
		assertTrue("q must be empty upon init", q.isEmpty());
		assertFalse("q cannot be full upon init", q.isFull());
		try {
			q.poll();
			Assert.fail("Queue must have been empty");
		} catch (StorageEmptyException e) {
			// All is well
		}
		try {
			q.peek();
			Assert.fail("Queue must have been empty");
		} catch (StorageEmptyException e) {
			// All is well
		}
		
		q.offer(10);
		assertNotNull(q.avail);
		assertNotNull(q.front);
		assertNotNull(q.rear);
		assertEquals(10,q.front.val);
		assertEquals(10,q.rear.val);
		assertNotNull(q.avail.next.next.next);
		assertNull(q.avail.next.next.next.next);
		
		q.offer(20);
		assertEquals(10,q.front.val);
		assertEquals(20,q.rear.val);
		assertNotNull(q.avail.next.next);
		assertNull(q.avail.next.next.next);
		
		int y = q.poll();
		assertEquals(10, y);
		assertEquals(20,q.front.val);
		assertEquals(20,q.rear.val);
		assertNotNull(q.avail.next.next.next);
		assertNull(q.avail.next.next.next.next);
		
		y = q.poll();
		assertEquals(20, y);
		assertNotNull(q.avail.next.next.next.next);
		assertNull(q.avail.next.next.next.next.next);
		assertNull(q.front);
		assertNull(q.rear);
		assertTrue(q.isEmpty());
		assertFalse(q.isFull());
		
		q.offer(10);
		q.offer(20);
		q.offer(30);
		q.offer(40);
		q.offer(50);
		assertFalse(q.isEmpty());
		assertTrue(q.isFull());
		assertEquals(10,q.front.val);
		assertEquals(50,q.rear.val);
		assertNull(q.avail);
		
		try {
			q.offer(60);
			fail("Q must have been full");
		} catch (StorageFullException e) {
			//All is well
		}
		q.poll();
		q.offer(60);
	}
}
