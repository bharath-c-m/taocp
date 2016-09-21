package art.of.programming.ds;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class DequeLinkedAllocationTest {

	@Test
	public void testDeque() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(5);
		assertNotNull(d.avail);
	}

	@Test
	public void testOfferFront() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(5);
		d.offerFront(10);
		assertEquals(10, d.front.val);
		assertEquals(10, d.rear.val);
		d.offerFront(20);
		assertEquals(20, d.front.val);
		assertEquals(10, d.rear.val);
	}

	@Test
	public void testPollFront() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(5);
		d.front = new DoubleNode();
		d.front.val = 10; //This will mean the code will have one additional node in the storage pool
		// but it simplifies the test case 
		d.rear = d.front;
		int hashCode = d.front.hashCode();
		assertEquals(10, d.pollFront());
		assertNull(d.front);
		assertNull(d.rear);
		assertEquals(hashCode, d.avail.hashCode());
	}

	@Test
	public void testOfferRear() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(5);
		d.offerRear(10);
		assertEquals(10, d.front.val);
		assertEquals(10, d.rear.val);
		d.offerRear(20);
		assertEquals(10, d.front.val);
		assertEquals(20, d.rear.val);
	}

	@Test
	public void testPollRear() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(5);
		d.front = new DoubleNode();
		d.front.val = 10; //This will mean the code will have one additional node in the storage pool
		// but it simplifies the test case 
		d.rear = d.front;
		int hashCode = d.rear.hashCode();
		assertEquals(10, d.pollRear());
		assertNull(d.front);
		assertNull(d.rear);
		assertEquals(hashCode, d.avail.hashCode());
	}

	@Test
	public void testIsEmptyIntegrationTest() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(3);
		assertTrue(d.isEmpty());
		d.offerRear(10);
		assertFalse(d.isEmpty());
		d.pollFront();
		assertTrue(d.isEmpty());
		d.offerFront(20);
		assertFalse(d.isEmpty());
		d.pollRear();
		assertTrue(d.isEmpty());
	}

	@Test
	public void testIsFullIntegrationTest() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(3);
		d.offerFront(10);
		d.offerRear(20);
		d.offerFront(30);
		assertTrue(d.isFull());
		d.pollFront();
		assertFalse(d.isFull());
	}

	@Test
	public void testPeekFront() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(3);
		d.front = new DoubleNode();
		d.front.val = 10;
		d.rear = d.front;
		assertEquals(10, d.peekFront());
	}

	@Test
	public void testPeekRear() throws Exception {
		DequeLinkedAllocation d = new DequeLinkedAllocation(3);
		d.rear = new DoubleNode();
		d.rear.val = 10;
		d.front = d.rear;
		assertEquals(10, d.peekRear());
	}
	
	@Test
	public void testFullAddIT() {
		DequeLinkedAllocation d = new DequeLinkedAllocation(2);
		d.offerFront(10);
		d.offerRear(20);
		try {
			d.offerFront(30);
			fail("Deque must have been full");
		} catch (StorageFullException e) {
			//All is well
		}
		d.pollFront();
		d.offerFront(30);
	}
	
	@Test
	public void testEmptyRemoveIT() {
		DequeLinkedAllocation d = new DequeLinkedAllocation(2);
		try {
			d.pollRear();
			fail("Deque must have been empty");
		} catch (StorageEmptyException e) {
			//All is well
		}
		d.offerFront(10);
		d.pollRear();
	}

}
