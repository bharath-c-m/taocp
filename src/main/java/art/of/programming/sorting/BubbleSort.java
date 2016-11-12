package art.of.programming.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.sorting.SortUtils;
import edu.princeton.cs.introcs.StdIn;

/**
 * mvn exec:java  -Dexec.mainClass=art.of.programming.sorting.BubbleSort -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=art.of.programming.sorting.BubbleSort -Dexec.arguments=<data/tinyInts.txt
 * Ex: mvn exec:java  -Dexec.mainClass=art.of.programming.sorting.BubbleSort -Dexec.arguments=<data/alpha.txt
 * 
 * @param <T>
 */
public class BubbleSort<T extends Comparable<T>> {

	T[] t;
	
	Logger l = LoggerFactory.getLogger(BubbleSort.class);
	
	public BubbleSort() {
	}
	
	public static void main(String[] args) {
		/*BubbleSort<Integer> si = new BubbleSort<>();
		si.t =  Arrays.stream(StdIn.readAllInts()).boxed().toArray(Integer[]::new);*/
		
		BubbleSort<String> si = new BubbleSort<>();
		si.t =  Arrays.stream(StdIn.readAllStrings()).toArray(String[]::new);
		si.l.info("Before Sorting - ");
		si.l.info(Arrays.toString(si.t));
		si.sort();
		si.l.info("After Sorting - ");
		si.l.info(Arrays.toString(si.t));
	}
	
	public void sort() {
		int bound = t.length; 
		//Index until with sorting is required. 
		SortUtils.StatsCollector sc = SortUtils.newStatsCollector();
		while (bound>0) {
			for (int i = 0; i < bound-1; i++) {
				if (SortUtils.less(t[i + 1], t[i], sc)) {
					SortUtils.exchange(t, i + 1, i, sc);
				}
			} bound--;
		}
		
		sc.printStats();
	}
	
	
}
