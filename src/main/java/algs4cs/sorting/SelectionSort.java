package algs4cs.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;

/**
 * mvn exec:java  -Dexec.mainClass=algs4cs.sorting.SelectionSort -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.sorting.SelectionSort -Dexec.arguments=<data/tinyInts.txt
 * 
 * @param <T>
 */
public class SelectionSort<T> {
	
	Logger l = LoggerFactory.getLogger(getClass());

	Comparable<T> t[];
	
	SortUtils.StatsCollector s = SortUtils.newStatsCollector();
	
	public SelectionSort() {
	}
	
	public SelectionSort(Comparable<T>[] t) {
		this.t = t;
	}
	
	public Comparable<T>[] sort() {
		for(int i = 0; i<t.length; i++) {
			int smallIndex = i;
			for(int j = i+1; j<t.length; j++) {
				if(SortUtils.less(t[j], t[smallIndex], s)) {
					smallIndex = j;
				}
			}
			SortUtils.exchange(t, i, smallIndex, s);
		}
		return t;
	}
	
	public static void main(String[] args) {
		SelectionSort<Integer> si = new SelectionSort<>();
		si.t =  Arrays.stream(StdIn.readAllInts()).boxed().toArray(Integer[]::new);
		si.l.info("Before Sorting - ");
		si.l.info(Arrays.toString(si.t));
		si.sort();
		si.l.info("After Sorting - ");
		si.l.info(Arrays.toString(si.t));
		si.s.printStats();
	}
}
