package art.of.programming.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.sorting.SortUtils;
/**
 * Execute test class ShellSortCrudeTest to test this program
 * 
 * Execute:
 * mvn test -Dtest=ShellSortTest
 * 
 * @param <T>
 */
public class ShellSortCrude<T extends Comparable<T>> {

	Logger l = LoggerFactory.getLogger(getClass());
	T[] t;
	int h[] = new int[]{8, 4, 2, 1};
	public ShellSortCrude(T[] t) {
		this.t = t;
	}
	
	public void sort() {
		//TODO investigate excessive compare bug
		//	[1, 2, 3, 4, 6, 5, 8, 7, 9, 10, 13, 11, 14, 12, 15, 16]
		//The above intermediate sorted array after insertion sort results in 5 exchanges (which is correct) but 120 compares, which is excessive
		// Running the above array in InsertionSort.java results in 5 exchanes and 20 comares
		SortUtils.StatsCollector statsCollector = SortUtils.newStatsCollector();
		for(int hIndex=0; hIndex<h.length; hIndex++) {
			int hVal = h[hIndex];
			for(int i=0; i<t.length; i++) {
				for(int j=i; (j+hVal<t.length); j+=hVal) {
					if(SortUtils.less(t[j+hVal], t[j], statsCollector))
						SortUtils.exchange(t, j, j+hVal, statsCollector);
					}
			}
			l.info("After h:{}-sorting {}", hVal, Arrays.toString(t));
			statsCollector.printStats();
		}
	}
	
}
