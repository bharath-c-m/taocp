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
	
	@SuppressWarnings({"rawtypes","unchecked"})
	//TODO refactor the code to be typesafe
	public boolean less(Comparable c1, Comparable c2) {
		return c1.compareTo(c2)<0;
	}
	
	public void exchange(int p, int q) {
		Comparable<T> x = t[p];
		t[p] = t[q];
		t[q] = x;
		
	}
	
	public Comparable<T>[] sort() {
		for(int i = 0; i<t.length; i++) {
			int smallIndex = i;
			for(int j = i+1; j<t.length; j++) {
				if(less(t[j], t[smallIndex])) {
					smallIndex = j;
				}
			}
			exchange(i, smallIndex);
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
	}
}
