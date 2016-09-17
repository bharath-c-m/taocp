package art.of.programming.ds;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class QueueTest {
	
	/**
	 * init
	 * front = rear = 0
	 * 
	 * offer
	 * rear = rear+1
	 * x[rear] = y
	 * 
	 * poll
	 * front = front+1
	 * y = x[front]
	 * f==r?f=r=0
	 * 
	 */
	
	Logger l = LoggerFactory.getLogger(QueueTest.class);

	
	@Test
	public void testOffer() throws Exception {
		Queue q = new Queue(5);
		q.offer(1);
		q.offer(2);
		assertEquals(2, q.rear);
		assertEquals(0, q.front);
	}

	@Test
	public void testPoll() throws Exception {
		Queue q = new Queue(5);
		q.x[1] = 1;
		q.x[2] = 2;
		q.rear = 2;
		assertEquals(1, q.poll());
		assertEquals(2, q.rear);
		assertEquals(1, q.front);
		
		assertEquals(2, q.poll());
		assertEquals(0, q.rear);
		assertEquals(0, q.front);
	}

	@Test
	public void testPeek() throws Exception {
		Queue q = new Queue(5);
		q.x[1] = 1;
		q.x[2] = 2;
		q.rear = 2;
		assertEquals(1, q.peek());
		assertEquals(0, q.front);
	}

	@Test
	public void testIsEmpty() throws Exception {
		Queue q = new Queue(5);
		assertTrue(q.isEmpty());
		q.rear = 1;
		assertFalse(q.isEmpty());
	}

	@Test
	public void testIsFull() throws Exception {
		Queue q = new Queue(5);
		assertFalse(q.isFull());
		q.rear = 4;
		assertTrue(q.isFull());
	}
	
	@Test
	public void addFull() {
		Queue q = new Queue(2);
		q.offer(10);
		try {
			q.offer(20);
			fail("Q must have been full");
		} catch(StorageFullException sf) {
			l.warn("test passed, msg: {}",sf.getMessage());		
		} catch(Exception e) {
			fail("Invalid exception");
		}
	}
	
	@Test
	public void removeEmpty() {
		Queue q = new Queue(5);
		try {
			q.poll();
			fail("Q must have been empty");
		} catch(StorageEmptyException se) {
			l.warn("test passed, msg: {}",se.getMessage());		
		} catch(Exception e) {
			fail("Invalid exception");
		}
	}

}
