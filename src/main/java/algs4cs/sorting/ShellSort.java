package algs4cs.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;

/**
 * mvn exec:java  -Dexec.mainClass=algs4cs.sorting.ShellSort -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.sorting.ShellSort -Dexec.arguments=<data/tinyInts.txt
 * 
 * @param <T>
 */

public class ShellSort<T> {

	Logger l = LoggerFactory.getLogger(getClass());

	Comparable<T> t[];
	
	SortUtils.StatsCollector statsCollector = SortUtils.newStatsCollector();
	
	public ShellSort() {
	}
	
	public ShellSort(Comparable<T>[] t) {
		this.t = t;
	}
	
	public Comparable<T>[] sort() {
		int n = t.length;
		int h = 1;
		while(h<n/3) 
			h = h*3+1;
		
		while(h >= 1) {
			for(int i=h; i<n; i++) {
				for(int j=i; j>=h && SortUtils.less(t[j], t[j-h], statsCollector); j-= h) {
					SortUtils.exchange(t, j, j-h, statsCollector);
				}
			}
			h = h/3;
		}
		
		return t;
	}
	
	public static void main(String[] args) {
		ShellSort<Integer> ss = new ShellSort<>();
		ss.t =  Arrays.stream(StdIn.readAllInts()).boxed().toArray(Integer[]::new);
		ss.l.info("Before Sorting - ");
		ss.l.info(Arrays.toString(ss.t));
		ss.sort();
		ss.l.info("After Sorting - ");
		ss.l.info(Arrays.toString(ss.t));
		ss.statsCollector.printStats();
		
	}
}
