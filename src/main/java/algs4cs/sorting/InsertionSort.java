package algs4cs.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdIn;

/**
 * mvn exec:java  -Dexec.mainClass=algs4cs.sorting.InsertionSort -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.sorting.InsertionSort -Dexec.arguments=<data/tinyInts.txt
 * 
 * @param <T>
 */
public class InsertionSort<T> {

	Logger l = LoggerFactory.getLogger(getClass());

	Comparable<T> t[];
	
	SortUtils.StatsCollector statsCollector = SortUtils.newStatsCollector();
	
	public InsertionSort() {
	}
	
	public InsertionSort(Comparable<T>[] t) {
		this.t = t;
	}
	
	public Comparable<T>[] sort() {
		for(int i=1; i<t.length; i++) {
			for(int j = i; j>0; j--) {
				if(SortUtils.less(t[j], t[j-1], statsCollector)) {
					SortUtils.exchange(t, j, j-1, statsCollector);
				} else {
					break;
				}
			}
		}
		
		return t;
	}
	
	public static void main(String[] args) {
		InsertionSort<Integer> si = new InsertionSort<>();
		si.t =  Arrays.stream(StdIn.readAllInts()).boxed().toArray(Integer[]::new);
		si.l.debug("Before Sorting - ");
		si.l.debug(Arrays.toString(si.t));
		si.sort();
		si.l.debug("After Sorting - ");
		si.l.debug(Arrays.toString(si.t));
		si.statsCollector.printStats();
		si.l.info("Sorting complete -- verifying sort order");
		boolean b=SortUtils.testSortedAscending(si.t);
		si.l.info("Sorting complete result: {}",b);
		
	}
}
