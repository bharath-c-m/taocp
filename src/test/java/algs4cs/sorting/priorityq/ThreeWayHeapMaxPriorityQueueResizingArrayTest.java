package algs4cs.sorting.priorityq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
public class ThreeWayHeapMaxPriorityQueueResizingArrayTest {


	@Test
	public void testThreeWayHeapMaxPriorityQueueResizingArray() throws Exception {
		ThreeWayHeapMaxPriorityQueueResizingArray<String> p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		assertNotNull(p.r);
		assertEquals(10, p.r.length());
		assertEquals(1, p.r.size()); //We add a null value at 0 upon the ds creation
		assertEquals(0, p.N);
	}

	@Test
	public void testInsert() throws Exception {
		ThreeWayHeapMaxPriorityQueueResizingArray<String> p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		p.insert("A");
		assertEquals("A",p.r.get(1));
		p.insert("B");
		assertEquals("A",p.r.get(2));
		assertEquals("B",p.r.get(1));
	}

	@Test
	public void testSwim() throws Exception {
		ThreeWayHeapMaxPriorityQueueResizingArray<String> p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		p.N=11;
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("Z");
		p.swim();
		assertEquals("Z",p.r.get(1));
		
		p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		p.N=11;
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("A");//4th Item in pq -- childs are 3k-1, 3k, 3k+1 == 11, 12, 13
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("B");//11th Item
		p.swim();
		assertEquals("X", p.r.get(1));
		assertEquals("B", p.r.get(4));
		assertEquals("A", p.r.get(11));
	}

	@Test
	public void testSink() throws Exception {
		ThreeWayHeapMaxPriorityQueueResizingArray<String> p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		p.N=11;
		p.r.add("A");
		p.r.add("X");
		p.r.add("X");
		p.r.add("Z");//4th Item in pq -- childs are 3k-1, 3k, 3k+1 == 11, 12, 13
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("X");
		p.r.add("B");//11th Item parent of this items is (k+1)/3 => 4
		
		p.sink();
		assertEquals("Z", p.r.get(1));
		assertEquals("B", p.r.get(4));
		assertEquals("A", p.r.get(11));
	}

	@Test
	public void testDelMax() throws Exception {
		ThreeWayHeapMaxPriorityQueueResizingArray<String> p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		p.N=5;
		p.r.add("Z");
		p.r.add("Y");
		p.r.add("X");
		p.r.add("W");
		p.r.add("V");
		
		assertEquals("Z",p.delMax());
		assertEquals(4, p.N);
		
//		assertEquals("Y", p.r.get(1));
//		assertEquals("V", p.r.get(2));
		
	}

	@Test
	public void testIsEmpty() throws Exception {
		ThreeWayHeapMaxPriorityQueueResizingArray<String> p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		assertTrue(p.isEmpty());
		p.N++;
		assertFalse(p.isEmpty());
	}

	@Test
	public void testSize() throws Exception {
		ThreeWayHeapMaxPriorityQueueResizingArray<String> p = new ThreeWayHeapMaxPriorityQueueResizingArray<>(10);
		assertEquals(0, p.size());
		p.N++;
		assertEquals(1, p.size());
	}

}
