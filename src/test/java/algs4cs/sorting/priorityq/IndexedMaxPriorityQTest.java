package algs4cs.sorting.priorityq;

import org.junit.Test;

import art.of.programming.exception.StorageFullException;

import static org.junit.Assert.*;

public class IndexedMaxPriorityQTest {

	@Test
	public void testIndexedMaxPriorityQ() throws Exception {
		IndexedMaxPriorityQ<Integer> pq = new IndexedMaxPriorityQ<>(3);
		assertEquals(4, pq.keys.length);
		assertEquals(4, pq.pq.length);
	}

	@Test
	public void testInsert() throws Exception {
		IndexedMaxPriorityQ<Integer> pq = new IndexedMaxPriorityQ<>(3);
		pq.insert(1, 10);
		pq.insert(2, 20);
		pq.insert(3, 50);
		
		assertEquals(3, pq.pq[1]);
		assertEquals(1, pq.pq[2]);
		assertEquals(2, pq.pq[3]);
		
		try {
			pq.insert(3, 25);
			fail("Expected StorageFullException");
		} catch(StorageFullException e) {
			//All is well
		}
	}

	@Test
	public void testDelMax() throws Exception {
		IndexedMaxPriorityQ<Integer> pq = new IndexedMaxPriorityQ<>(3);
		
		pq.keys=new Integer[]{0, 10};
		pq.pq=new int[]{0, 1};
		pq.N=1;
		assertEquals(1, pq.delMax());
		pq.keys=new Integer[]{0, 10, 20, 30};
		pq.pq=new int[]{0, 3, 1, 2};
		pq.N=3;
		assertEquals(3, pq.delMax());
	}

	@Test
	public void testGetMaxIndex() throws Exception {
		IndexedMaxPriorityQ<Integer> pq = new IndexedMaxPriorityQ<>(3);
		
		pq.keys=new Integer[]{0, 10};
		pq.pq=new int[]{0, 1};
		pq.N=1;
		assertEquals(1, pq.getMaxIndex());
		
		pq.keys=new Integer[]{0, 30, 20};
		pq.pq=new int[]{0, 1, 2};
		pq.N=2;
		assertEquals(1, pq.getMaxIndex());
		
		pq.keys=new Integer[]{0, 10, 20, 30};
		pq.pq=new int[]{0, 3, 1, 2};
		pq.N=3;
		assertEquals(3, pq.getMaxIndex());
		
	}

	@Test
	public void testSwim() throws Exception {
		IndexedMaxPriorityQ<Integer> pq = new IndexedMaxPriorityQ<>(3);
		pq.keys=new Integer[]{0, 10, 20, 30};
		pq.pq=new int[]{0, 2, 1, 3};
		pq.N=3;
		pq.swim();
		assertEquals(3, pq.pq[1]);
		
		pq.swim();
		assertEquals(3, pq.pq[1]);
	}

	@Test
	public void testSink() throws Exception {
		IndexedMaxPriorityQ<Integer> pq = new IndexedMaxPriorityQ<>(3);
		pq.keys=new Integer[]{0, 10, 20};
		pq.pq=new int[]{0, 1, 2};
		pq.N=2;
		pq.sink();
		assertEquals(2, pq.pq[1]);
		assertEquals(1, pq.pq[2]);
	}

}
