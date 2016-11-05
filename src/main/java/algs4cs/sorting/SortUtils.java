package algs4cs.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
		void printStats() {
			l.info("exchangeCount:{}, exchangeCount:{}", exchangeCount, compareCount);
		}
	}
}
