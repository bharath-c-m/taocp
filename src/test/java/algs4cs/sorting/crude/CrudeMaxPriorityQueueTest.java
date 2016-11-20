package algs4cs.sorting.crude;

import org.junit.Test;

import algs4cs.sorting.crude.CrudeMaxPriorityQueue;
import art.of.programming.exception.StorageFullException;

import static org.junit.Assert.*;

public class CrudeMaxPriorityQueueTest {

	@Test
	public void testCrudeMaxPriorityQueue() throws Exception {
		CrudeMaxPriorityQueue<Integer> c = new CrudeMaxPriorityQueue<>(10);
		assertNotNull(c.t);
		assertEquals(10, c.t.length);
		assertEquals(0, c.index);
	}

	@Test
	public void testInsert() throws Exception {
		CrudeMaxPriorityQueue<Integer> c = new CrudeMaxPriorityQueue<>(10);
		c.insert(10);
		assertEquals(10, c.t[0]);
		assertEquals(1, c.index);
		c.insert(20);
		assertEquals(20, c.t[1]);
		assertEquals(2, c.index);
		c.index=9;
		try {
			c.insert(20);
		} catch(StorageFullException s) {
			//All is well
		}
	}

	@Test
	public void testDelMax() throws Exception {
		CrudeMaxPriorityQueue<Integer> c = new CrudeMaxPriorityQueue<>(10);
		c.t = new Integer[]{5, 4, 9, 3, 1, 7, 10, 6, 8, 2};
		c.index=10;
		assertEquals(10, c.delMax().intValue());
		assertEquals(9, c.index);
		assertEquals(10, c.t[9]);
	}

	@Test
	public void testIsEmpty() throws Exception {
		CrudeMaxPriorityQueue<Integer> c = new CrudeMaxPriorityQueue<>(10);
		assertTrue(c.isEmpty());
		c.index = 5;
		assertFalse(c.isEmpty());
		c.index = 0;
		assertTrue(c.isEmpty());
	}

	@Test
	public void testSize() throws Exception {
		CrudeMaxPriorityQueue<Integer> c = new CrudeMaxPriorityQueue<>(10);
		assertEquals(0, c.size());
		c.index = 5;
		assertEquals(5, c.size());
	}

}
