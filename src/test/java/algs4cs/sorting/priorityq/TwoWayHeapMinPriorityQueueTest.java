package algs4cs.sorting.priorityq;

import org.junit.Test;

import algs4cs.sorting.priorityq.TwoWayHeapMinPriorityQueue;

import static org.junit.Assert.*;
public class TwoWayHeapMinPriorityQueueTest {

	@Test
	public void testTwoWayHeapMinPriorityQueue() throws Exception {
		TwoWayHeapMinPriorityQueue<Integer> t = new TwoWayHeapMinPriorityQueue<>(new Integer[10]);
		assertEquals(10, t.t.length);
		assertNull(t.t[5]);
		t.t[5] = 10;
		assertEquals(10, t.t[5].intValue());
	}

}
