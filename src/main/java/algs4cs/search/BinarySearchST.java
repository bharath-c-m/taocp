package algs4cs.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.sorting.SortUtils;
import art.of.programming.ds.ResizingArray;

public class BinarySearchST<K extends Comparable<K>, V> {

	ResizingArray<K> keys;
	ResizingArray<V> vals;
	SortUtils.StatsCollector sc=SortUtils.newStatsCollector();
	Logger l = LoggerFactory.getLogger(getClass());
	public BinarySearchST(int initialSize) {
		keys=new ResizingArray<>(initialSize);
		vals=new ResizingArray<>(initialSize);
	}
	public V get(K k) {
		assert k!=null:"Key cannot be null";
		int r = rank(k);
		if(keys.get(r)!=null&&k.compareTo(keys.get(r))==0) {
			return vals.get(r);
		} else {
			while(++r<keys.size()-1) {
				if(k.compareTo(keys.get(r))==0) {
					return vals.get(r);
				}
			}
		}
		return null;
	}
	public void put(K k, V v) {
		assert k!=null:"Key cannot be null";
		assert v!=null:"Value cannot be null";
		int rank=rank(k);
		if(keys.get(rank)!=null&&k.compareTo(keys.get(rank))==0) {
			vals.set(rank, v);
		} else {
			//add one item just in case re-sizing array needs re size.
			///This is because in current implementation resize happens only upon add
			int x=keys.size();
			keys.add(null);
			vals.add(null);
			
			while(x>rank) {
				keys.set(x, keys.get(x-1));
				vals.set(x, vals.get(x-1));
				x--;
			}
			keys.set(rank, k);
			vals.set(rank, v);
		}
	}
	/**
	 * A non-recursive implementation for rank
	 * @param k
	 * @return
	 */
	public int rank(K k) {
		int lo=0;
		int hi=keys.size()-1;
		int mid=0;
		while(lo<=hi) {
			mid=lo+(hi-lo)/2;
			l.debug("hi:{}, mid:{}, lo:{} ",hi, mid, lo);
			int compare=SortUtils.compare(k, keys.get(mid), sc);
			if(compare<0) {
				hi=mid-1;
			} else if(compare>0) {
				lo=mid+1;
			} else {
				return mid;
			}
		}
		return lo;
	}
	public int size() {
		return keys.size();
	}
	public boolean isEmpty() {
		return size()==0;
	}
	public K floor(K k) {
		return null;
	}
	public K ceiling(K k) {
		return null;
	}
	public void delete(K k) {
		assert k!=null:"Key cannot be null";
		int rank=rank(k);
		if(k.compareTo(keys.get(rank))==0) {
			keys.remove(rank);vals.remove(rank);
		}
	}
	public void deleteMin() {
		keys.remove(0);
		vals.remove(0);
	}
	public void deleteMax() {
		keys.remove(keys.size()-1);
		vals.remove(vals.size()-1);
	}
	public Iterable<K> keys() {
		return null;
	}
	public Iterable<K> keys(K lo, K hi) {
		return null;
	}
}
