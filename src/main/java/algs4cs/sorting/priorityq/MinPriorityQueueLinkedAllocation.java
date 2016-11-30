package algs4cs.sorting.priorityq;

import algs4cs.sorting.SortUtils;
import art.of.programming.ds.ResizingArray;
import edu.princeton.cs.introcs.StdRandom;

/**
 * A less intuitive solution for Minimum priority queue based on an indexed binary tree
 * The binary tree node index is maintained in an array ordered based on their priority.  
 *
 *
 * Execute the program with -
 * $ mvn exec:java -Dexec.mainClass=algs4cs.sorting.priorityq.MinPriorityQueueLinkedAllocation
 * @param <T>
 */
public class MinPriorityQueueLinkedAllocation<T extends Comparable<T>> {
	
	int N=0;
	ResizingArray<PQNode<T>> t;
	public MinPriorityQueueLinkedAllocation(int initialCapacity) {
		t=new ResizingArray<>(initialCapacity);
		t.add(null);
	}
	
	public void insert(T t) {
		N++;
		PQNode<T> n = new PQNode<>();
		n.t=t;
		this.t.add(n);
		int parentIndex=N/2;
		if(parentIndex==0)
			return;
		if(N%2==0) {
			this.t.get(parentIndex).l=n;
		} else {
			this.t.get(parentIndex).r=n;
		}
		n.p=this.t.get(parentIndex);
		swim(n);
	}
	
	public void swim(PQNode<T> k) {
		while(k.p!=null) {
			if(SortUtils.less(k.p.t, k.t))
				break;
			exchange(k.p, k);
			k=k.p;
		}
	}
	
	public void sink(PQNode<T> k) {
		while(!k.isLeaf()) {
			PQNode<T> j = k.l;
			if(k.r!=null) {
				if(SortUtils.less(k.r.t, j.t)) {
					j=k.r;
				}
			}
			if(SortUtils.less(k.t, j.t))
				break;
			exchange(k, j);
			k=j;
		}
	}
	
	public T delMin() {
		PQNode<T> root=t.get(1);
		T ret=root.visit();
		PQNode<T> n = t.get(N);
		exchange(n, root);
		if(N>1&&N%2==0) {
			n.p.l=null;
		} else if(N>1){
			n.p.r=null;
		}
		t.set(N--, null);
		sink(root);
		return ret;
	}
	
	public boolean isEmpty() {
		return N==0;
	}
	
	public void exchange(PQNode<T> p, PQNode<T> k) {
		T temp = p.t;
		p.t=k.t;
		k.t=temp;
	}
	
	public static void main(String[] args) {
		MinPriorityQueueLinkedAllocation<Integer> pq = new MinPriorityQueueLinkedAllocation<>(16);
		int count = 25;
		while(count-->0) {
			pq.insert(StdRandom.uniform(10, 10000));
		}
		
		pq.t.stream().filter(t->t!=null).forEach(t->System.out.print(t+" "));
		System.out.println("\nSorted output");
		while(!pq.isEmpty()) {
			System.out.print(pq.delMin());
			System.out.print(" ");
		}
	}
}
