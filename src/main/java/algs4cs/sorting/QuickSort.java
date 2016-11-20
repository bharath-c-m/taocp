package algs4cs.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;

/**
 * 
 * Execute test 
 * mvn -Dtest=algs4cs.sorting.QuickSortTest test
 *
 * Execute App
 * mvn exec:java  -Dexec.mainClass=algs4cs.sorting.QuickSort -Dexec.arguments=<data/tinyInts.txt
 *  
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> {

	Logger l = LoggerFactory.getLogger(getClass());
	T t[];
	SortUtils.StatsCollector sc = SortUtils.newStatsCollector();

	public QuickSort(T t[]) {
		this.t = t;
	}

	public int partition(int lo, int hi) {
		int i = lo;
		int j = hi+1;
		while (true) {
			while (SortUtils.less(t[++i], t[lo], sc))
				if (i == hi)
					break;
			while (SortUtils.less(t[lo], t[--j], sc))
				if (j == lo)
					break;
			if(i>=j)
				break;
			SortUtils.exchange(t, i, j, sc);
		}
		SortUtils.exchange(t, lo, j, sc);
		return j;
	}
	
	public void sort(int lo, int hi) {
		if(lo>=hi)
			return;
		int j = partition(lo, hi);
		sort(lo, j-1);
		sort(j+1, hi);
	}
	
	public static void main(String[] args) {
		QuickSort<Integer> q = new QuickSort<>(Arrays.stream(StdIn.readAllInts()).boxed().toArray(Integer[]::new));
		q.l.debug("Before sorting - {}", Arrays.toString(q.t));
		q.sort(0, q.t.length-1);
		q.l.debug("After sorting - {}", Arrays.toString(q.t));
		q.sc.printStats();
		
		q.l.info("Sorting complete -- verifying sort order");
		boolean b=SortUtils.testSortedAscending(q.t);
		q.l.info("Sorting complete result: {}",b);
		
	}
}
