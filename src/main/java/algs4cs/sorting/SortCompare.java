package algs4cs.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.Stopwatch;

/**
 * To execute using maven
 * mvn exec:java  -Dexec.mainClass=algs4cs.sorting.SortCompare
 * 
 * Comparing Selection sort and Insertion sort for 100,000 items I got insertion sort is ~1.7 times faster than selection sort
 * Output for n = 100,000
 * 
 * [algs4cs.sorting.SortCompare.main()] INFO algs4cs.sorting.SortCompare - Time taken by InsertionSort is 116.001
   [algs4cs.sorting.SortCompare.main()] INFO algs4cs.sorting.SortCompare - Time taken by SelectionSort is 69.774
   [algs4cs.sorting.SortCompare.main()] INFO algs4cs.sorting.SortCompare - For n=100000 items, InsertionSort is 1.6625247226760684 times faster than SelectionSort
 * @param <T>
 */
public class SortCompare<T> {
	static Logger l = LoggerFactory.getLogger(SortCompare.class);
	
	Comparable<T> t[];
	
	public double time(String alg, Comparable<T> t[]) {
		Stopwatch timer = new Stopwatch();
		if(alg.equals("InsertionSort")) {
			InsertionSort<T> i = new InsertionSort<>(t);
			i.sort();
		} else if(alg.equals("SelectionSort")) {
			SelectionSort<T> s = new SelectionSort<>(t);
			s.sort();
		}
		return timer.elapsedTime();
	}
	
	public static double timeRandom(String alg, int n, int t) {
		SortCompare<Double> sd = new SortCompare<>();
		double total = 0.0;
		double a[] = new double[n];
		for(int iter = 0; iter<t; iter++) {
			for(int i=0; i<n; i++) {
				a[i] = StdRandom.uniform();
			}
			total += sd.time(alg, Arrays.stream(a).boxed().toArray(Double[]::new));
		}
		
		return total;
	}
	
	public static void main(String a[]) {
		String alg1 = "InsertionSort";
		String alg2 = "SelectionSort";
		int n = 10_000;
		int t = 5;
		double t1 = timeRandom(alg1, n, t);
		double t2 = timeRandom(alg2, n, t);
		l.info("Time taken by {} is {}", alg1, t1);
		l.info("Time taken by {} is {}", alg2, t2);
		l.info("For n={} items, {} is {} times faster than {}", n, alg1, t1/t2, alg2);
	}

}
