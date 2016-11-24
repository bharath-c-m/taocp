package algs4cs.sorting;

import org.junit.Test;

import art.of.programming.exception.StorageFullException;

import static org.junit.Assert.*;
public class TwoWayHeapMaxPriorityQueueTest {

	@Test
	public void testInsert() throws Exception {
		TwoWayHeapMaxPriorityQueue<Integer> p = new TwoWayHeapMaxPriorityQueue<>(10);
		assertNotNull(p.t);
		assertEquals(10,p.t.length);
		assertEquals(0, p.N);
		p.insert(10);
		assertEquals(null,p.t[0]);
		assertEquals(10,p.t[1]);
		assertEquals(1, p.N);
		p.N=9;
		try {
			p.insert(5);
		} catch(StorageFullException e) {
			//All is well
		}
		
		p.N=1;
		p.insert(9);
		assertEquals(2, p.N);
		assertEquals(9, p.t[2]);
		p.insert(7);
		assertEquals(3, p.N);
		assertEquals(7, p.t[3]);

		p.insert(8);
		assertEquals(4, p.N);
		assertEquals(8, p.t[4]);
		
		p.insert(6);
		assertEquals(5, p.N);
		assertEquals(6, p.t[5]);
		
		p.insert(8);
		assertEquals(6, p.N);
		assertEquals(7, p.t[6]);
		assertEquals(8, p.t[3]);
		
	}
	@Test
	public void testDelMax() throws Exception {
		TwoWayHeapMaxPriorityQueue<Integer> p = new TwoWayHeapMaxPriorityQueue<>(10);
		p.N = 5;
		p.t = new Object[]{0, 10, 9, 7, 5, 8, 0, 0, 0, 0, 0};
		assertEquals(10,p.delMax().intValue());
		assertEquals(4, p.N);
		assertEquals(9, p.t[1]);
		assertEquals(8, p.t[2]);
		assertEquals(7, p.t[3]);
		assertEquals(5, p.t[4]);
		
		assertEquals(9, p.delMax().intValue());
		assertEquals(3, p.N);
		assertEquals(8, p.t[1]);
		assertEquals(5, p.t[2]);
		assertEquals(7, p.t[3]);
		
	}
	@Test
	public void testIsEmpty() {
		TwoWayHeapMaxPriorityQueue<Integer> p = new TwoWayHeapMaxPriorityQueue<>(10);
		assertTrue(p.isEmpty());
		p.N=1;
		assertFalse(p.isEmpty());
	}
	@Test
	public void testSize() {
		TwoWayHeapMaxPriorityQueue<Integer> p = new TwoWayHeapMaxPriorityQueue<>(10);
		assertEquals(0, p.size());
		p.N=100;
		assertEquals(100, p.size());
	}
	@Test
	public void testSink() throws Exception {
		TwoWayHeapMaxPriorityQueue<Integer> p = new TwoWayHeapMaxPriorityQueue<>(10);
		p.N=3;
		p.t = new Object[]{0, 5, 9, 7, 0, 0, 0};
		p.sink();
		assertEquals(9, p.t[1]);
		assertEquals(5, p.t[2]);
		assertEquals(7, p.t[3]);
		p.N=4;
		p.t = new Object[]{0, 5, 9, 7, 6};
		p.sink();
	}
	@Test
	public void testSwim() throws Exception {
		TwoWayHeapMaxPriorityQueue<Integer> p = new TwoWayHeapMaxPriorityQueue<>(10);
		p.N=3;
		p.t = new Object[]{0, 9, 5, 11, 0, 0, 0};
		p.swim();
		assertEquals(11, p.t[1]);
		assertEquals(5, p.t[2]);
		assertEquals(9, p.t[3]);
	}
}
