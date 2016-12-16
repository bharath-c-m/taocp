package algs4cs.sorting.priorityq;

import java.util.Comparator;

import algs4cs.sorting.SortUtils;
import art.of.programming.ds.ResizingArray;

public class TwoWayHeapMinPriorityQueueComparator<T> {

	ResizingArray<T> t;
	Comparator<T> c;
	int N;
	public TwoWayHeapMinPriorityQueueComparator(int initialCapacity, Comparator<T> comparator) {
		assert initialCapacity>0;
		assert comparator!=null:"comparator has to be supplied";
		this.t = new ResizingArray<>(initialCapacity);
		this.t.add(null);
		c=comparator;
	}
	
	public void sink() {
		int k=1;
		while(2*k<N) {
			int j = 2*k;
			if(N>j&&less(t.get(j+1), t.get(j), c)) {
				j++;
			}
			if(less(t.get(k), t.get(j), c))
				break;
			SortUtils.exchange(t, k, j);
			k=j;
		}
	}
	
	public void swim() {
		int k = N;
		while(k>1) {
			if(less(t.get(k),t.get(k/2), c)) {
				SortUtils.exchange(t, k, k/2);
			} else
				break;
			k/=2;
		}
	}
	
	public T delMin() {
		T ret = t.get(1);
		t.set(1, t.get(N--));
		sink();
		return ret;
	}
	
	public void insert(T t) {
		N++;
		this.t.add(t);
		swim();
	}
	public boolean isSmpty() {
		return N==0;
	}
	
	private boolean less(T p, T q, Comparator<T> comp) {
		return comp.compare(p, q)<0;
	}
}
