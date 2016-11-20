package algs4cs.sorting.crude;

import algs4cs.sorting.SortUtils;
import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class CrudeMaxPriorityQueue<T extends Comparable<T>> {

	Object[] t;
	int index=0;
	
//	@SuppressWarnings("unchecked")
	public CrudeMaxPriorityQueue(int maxCapacity) {
		this.t = new Object[maxCapacity];
	}
	
	public void insert(T t) {
		if(index>=this.t.length)
			throw new StorageFullException("Q is full");
		this.t[index++] = t;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public T delMax() {
		if(size()==0)
			throw new StorageEmptyException("Q is empty");
		if(size()>1) {
			int maxIndex=0;
			for(int i=1; i<size(); i++) {
				if(SortUtils.less((Comparable)t[maxIndex], (Comparable)t[i])) {
					maxIndex = i;
				}
			}
			SortUtils.exchange(t, maxIndex, index-1);
		}
		return (T)t[--index];
	}
	
	public boolean isEmpty() {
		return index==0;
	}
	
	public int size() {
		return index;
	}
	
}
