package algs4cs.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.ds.ResizingArray;

public class SortUtils {

	static Logger l = LoggerFactory.getLogger(SortUtils.class);
	
	public static void exchange(Object t[], int p, int q, StatsCollector sc) {
		Object x = t[p];
		t[p] = t[q];
		t[q] = x;
		sc.incerementExchangeCount();
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public static boolean less(Comparable c1, Comparable c2, StatsCollector sc) {
		sc.incerementCompareCount();
		return c1.compareTo(c2)<0;
	}
	
	//API with out stats collection
	public static void exchange(Object t[], int p, int q) {
		Object x = t[p];
		t[p] = t[q];
		t[q] = x;
	}
	
	//API with out stats collection
	@SuppressWarnings({"rawtypes","unchecked"})
	public static boolean less(Comparable c1, Comparable c2) {
		return c1.compareTo(c2)<0;
	}
	
	public static StatsCollector newStatsCollector() {
		return new StatsCollector();
	}
	
	public static class StatsCollector {
		int exchangeCount;
		int compareCount;
		
		void incerementCompareCount() {
			compareCount++;
		}
		
		void incerementExchangeCount() {
			exchangeCount++;
		}
		
		public void printStats() {
			l.info("exchangeCount:{}, compareCount:{}", exchangeCount, compareCount);
		}
	}

	@SuppressWarnings({"rawtypes","unchecked"})
	public static boolean testSortedAscending(Comparable[] t) {
		boolean sorted=true;
		if(t!=null && t.length > 1) {
			Comparable p = t[0];
			for(int i=1; i<t.length && sorted; i++) {
				sorted = p.compareTo(t[i]) <=0;
				p=t[i];
			}
		}
		return sorted;
	}

	public static void exchange(ResizingArray t, int p, int q) {
		Object x = t.get(p);
		t.set(p,t.get(q));
		t.set(q,x);
	}

	@SuppressWarnings({"rawtypes","unchecked"})
	public boolean isEqual(Comparable c1, Comparable c2) {
		return c1.compareTo(c2)==0;
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public static boolean isEqual(Comparable c1, Comparable c2, StatsCollector s) {
		s.incerementCompareCount();
		return c1.compareTo(c2)==0;
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public static int compare(Comparable c1, Comparable c2, StatsCollector s) {
		s.incerementCompareCount();
		return c1.compareTo(c2);
	}
}
