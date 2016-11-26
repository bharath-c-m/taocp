package algs4cs.sorting.priorityq;

public class TwoWayHeapMinPriorityQueue<T extends Comparable<T>> {

	T[] t;
	
	@SuppressWarnings("unchecked")
	public TwoWayHeapMinPriorityQueue(Comparable<T>[] c) {
		this.t = (T[])c;
	}
}
