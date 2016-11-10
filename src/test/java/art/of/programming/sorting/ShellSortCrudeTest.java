package art.of.programming.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdRandom;

public class ShellSortCrudeTest {
	@Test
	public void testShellSort() throws Exception {
		ShellSortCrude<Integer> shellSortInteger = new ShellSortCrude<>(new Integer[10]);
		assertNotNull(shellSortInteger.t);
		
		ShellSortCrude<String> shellSortString = new ShellSortCrude<>(new String[]{"a","b"});
		assertNotNull(shellSortString.t);
	}

	@Test
	public void testSort() throws Exception {
		Integer[] toSort = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		StdRandom.shuffle(toSort);
		ShellSortCrude<Integer> shellSortInteger = new ShellSortCrude<>(toSort);
		shellSortInteger.sort();

		assertEquals(1, toSort[0].intValue());
		assertEquals(2, toSort[1].intValue());
		assertEquals(3, toSort[2].intValue());
		assertEquals(4, toSort[3].intValue());
		assertEquals(14, toSort[13].intValue());
		assertEquals(15, toSort[14].intValue());
		assertEquals(16, toSort[15].intValue());
		
	}

}
