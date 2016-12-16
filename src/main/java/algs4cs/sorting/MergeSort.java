package algs4cs.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;

/**
 * mvn exec:java  -Dexec.mainClass=art.of.programming.sorting.MergeSort -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.sorting.MergeSort -Dexec.arguments=<data/tinyInts.txt
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.sorting.MergeSort -Dexec.arguments=<data/alpha.txt
 * 
 * @param <T>
 */

public class MergeSort<T extends Comparable<T>> {

	T[] t;
	T[] aux;
	Logger l = LoggerFactory.getLogger(getClass());
	SortUtils.StatsCollector statsCollector = SortUtils.newStatsCollector();
	
	@SuppressWarnings(value={"unchecked"})
	public MergeSort() {
		assert t!=null && t.length>1;
		aux = (T[])new Comparable[t.length];
	}
	
	@SuppressWarnings(value={"unchecked"})
	public MergeSort(T[] t) {
		assert t!=null && t.length>1;
		this.t = t;
		aux = (T[])new Comparable[t.length];
	}
	
	public void sort(int lo, int hi) {
		if(lo>=hi)
			return;
		int mid = lo+(hi-lo)/2;
		sort(lo, mid);
		sort(mid+1, hi);
		merge(lo, mid, hi);
	}
	
	public void merge(int lo, int mid, int hi) {
		int i=lo, j=mid+1;
		for(int k=lo; k<=hi; k++) {
			aux[k]=t[k];
		}
		
		for(int k=lo; k<=hi; k++) {
			if(i>mid) {
				t[k] = aux[j++];
			} else if(j > hi) {
				t[k] = aux[i++];
			} else if(SortUtils.less(aux[i], aux[j], statsCollector)) {
				t[k] = aux[i++];
			} else {
				t[k] = aux[j++];
			}
		}
	}
	
	public void sort() {
		sort(0, this.t.length-1);
		statsCollector.printStats();
	}
	
	public static void main(String[] args) {
//		MergeSort<Integer> m = new MergeSort<>(Arrays.stream(StdIn.readAllInts()).boxed().toArray(Integer[]::new));
		MergeSort<String> m = new MergeSort<>(Arrays.stream(StdIn.readAllStrings()).toArray(String[]::new));
		m.l.info("Before Sorting - ");
		m.l.info(Arrays.toString(m.t));
		m.sort(0, m.t.length-1);
		m.l.info("After Sorting - ");
		m.l.info(Arrays.toString(m.t));
		m.statsCollector.printStats();
	}
	
}
