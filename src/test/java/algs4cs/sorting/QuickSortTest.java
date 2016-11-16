package algs4cs.sorting;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuickSortTest {

	@Test
	public void testPartition() throws Exception {
		QuickSort<Integer> q = new QuickSort<>(new Integer[]{5, 2, 7, 10, 3, 6, 9, 4, 1, 8});
		int j = q.partition(0, q.t.length-1);
		assertEquals(4, j);
		assertEquals(5, q.t[4].intValue());
		assertEquals(6, q.t[5].intValue());
	}
	@Test
	public void testSorting() throws Exception {
		QuickSort<Integer> q = new QuickSort<>(new Integer[]{5, 2, 7, 10, 3, 6, 9, 4, 1, 8});
		q.sort(0, q.t.length-1);
		assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, q.t);
	}

}
