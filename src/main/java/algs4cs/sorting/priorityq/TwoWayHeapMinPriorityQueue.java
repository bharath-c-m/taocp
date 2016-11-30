package algs4cs.sorting.priorityq;

import algs4cs.sorting.SortUtils;
import art.of.programming.ds.ResizingArray;
import edu.princeton.cs.introcs.StdRandom;

public class TwoWayHeapMinPriorityQueue<T extends Comparable<T>> {

	ResizingArray<T> t;
	int N;
	public TwoWayHeapMinPriorityQueue(int initialCapacity) {
		this.t = new ResizingArray<>(initialCapacity);
		this.t.add(null);
	}
	
	public void sink() {
		int k=1;
		while(2*k<N) {
			int j = 2*k;
			if(N>j&&SortUtils.less(t.get(j+1), t.get(j))) {
				j++;
			}
			if(SortUtils.less(t.get(k), t.get(j)))
				break;
			SortUtils.exchange(t, k, j);
			k=j;
		}
	}
	
	public void swim() {
		int k = N;
		while(k>1) {
			if(SortUtils.less(t.get(k),t.get(k/2))) {
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
	public static void main(String[] args) {
		TwoWayHeapMinPriorityQueue<Integer> p = new TwoWayHeapMinPriorityQueue<>(16);
		int count=100;
		while(count-->0) {
			p.insert(StdRandom.uniform(1, 1000));
		}
		while(!p.isSmpty()) {
			Integer i=p.delMin();
			System.out.println(i.intValue());
		}
	}
}
