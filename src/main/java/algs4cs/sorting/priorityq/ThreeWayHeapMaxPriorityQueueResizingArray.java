package algs4cs.sorting.priorityq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.sorting.SortUtils;
import art.of.programming.ds.ResizingArray;

public class ThreeWayHeapMaxPriorityQueueResizingArray<T extends Comparable<T>> {

	Logger l = LoggerFactory.getLogger(getClass());
	int N=0;
	ResizingArray<T> r;
	public ThreeWayHeapMaxPriorityQueueResizingArray(int initialCapacity) {
		r = new ResizingArray<>(initialCapacity);
		r.add(null);//Reserving 0th index -- so it looks like a 1 based array suitable for our priority queue implementation
	}
	
	public void insert(T t) {
		N++;
		r.add(t);
		swim();
	}
	
	public void swim() {
		int k = N;
		while(k>1) {
			if(SortUtils.less(r.get((k+1)/3), r.get(k))) {
				SortUtils.exchange(r, (k+1)/3, k);
				k=(k+1)/3;
			} else {
				break;
			}
		}
	}
	
	public void sink() {
		int k=1;
		while(3*k-1<=N) {
			int j=3*k-1;
			int max=k;
			if(SortUtils.less(r.get(max), r.get(j))) {
				max=j;
			}
			
			if(3*k<=N&&SortUtils.less(r.get(max), r.get(++j))) {
				max=j;
			}
			
			if(3*k+1<=N&&SortUtils.less(r.get(max), r.get(++j))) {
				max=j;
			} 
			
			if(max==k)
				break;
			//exchange k and max
			SortUtils.exchange(r, k, max);
			k=max;
			//new k will be max
		}
	}
	
	public T delMax() {
		T max = r.get(1);
		SortUtils.exchange(r, 1, N--);
		sink();
		return max;
	}
	
	public boolean isEmpty() {
		assert N>=0 : "N value cannot be negative";
		return N==0;
	}
	
	public int size() {
		assert N>=0 : "N value cannot be negative";
		return N;
	}
}
