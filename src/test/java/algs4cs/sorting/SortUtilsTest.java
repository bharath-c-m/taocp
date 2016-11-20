package algs4cs.sorting;

import org.junit.Test;
import static org.junit.Assert.*;

public class SortUtilsTest {

	@Test
	public void testTestSortedAscending() throws Exception {
		Integer[] i  = null;
		assertTrue(SortUtils.testSortedAscending(i));
		i = new Integer[]{9};
		assertTrue(SortUtils.testSortedAscending(i));
		i = new Integer[]{0};
		assertTrue(SortUtils.testSortedAscending(i));
		i = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		assertTrue(SortUtils.testSortedAscending(i));
		i = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,0};	
		assertFalse(SortUtils.testSortedAscending(i));
		i = new Integer[] {1, 0};
		assertFalse(SortUtils.testSortedAscending(i));

	}

}
