package algs4cs.search;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mvn exec:java -Dexec.mainClass=algs4cs.search.BinarySearch
 * 
 */
public class BinarySearch<T extends Comparable<T>> {

	static Logger l = LoggerFactory.getLogger(BinarySearch.class); 
	T[] t;
	public BinarySearch(T[] t) {
		this.t=t;
		Arrays.sort(t);
	}
	
	public int rank(T t, int lo, int hi) {
		if(lo>hi)
			return -1;
		int mid = lo+(hi-lo)/2;
		l.info("lo:{},mid:{},hi:{}",lo,mid,hi);
		int x = t.compareTo(this.t[mid]);
		if(x<0) {
			return rank(t, lo, mid-1);
		} else if(x>0) {
			return rank(t, mid+1,hi);
		} else 
			return mid;
	}
	
	
	public boolean contains(T t) {
		return rank(t, 0, this.t.length-1) != -1;
	}
	public static void main(String[] args) {
		BinarySearch<Integer> b = new BinarySearch<>(new Integer[]{5,6,8,2,4,1});
		l.info(Arrays.toString(b.t));
		l.info("Contains 1 is {}",b.contains(1));
		l.info("Contains 4 is {}",b.contains(4));
		l.info("Contains 40 is {}",b.contains(40));
		l.info("Contains 8 is {}",b.contains(8));

	}
	
}
