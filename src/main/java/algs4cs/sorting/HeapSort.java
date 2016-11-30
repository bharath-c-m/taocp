package algs4cs.sorting;

import art.of.programming.ds.ResizingArray;
import edu.princeton.cs.introcs.StdRandom;

public class HeapSort<T extends Comparable<T>> {

	int N=0;
	ResizingArray<T> t;
	public HeapSort(int size) {
		t = new ResizingArray<>(size);
		t.add(null);//reserver index 0
	}
	
	public static void main(String[] args) {
		HeapSort<Integer> h = new HeapSort<>(128);
		h.N = 100;
		int i = h.N;
		while(i-->0) {
			h.t.add(StdRandom.uniform(10, 1000));
		}
		h.t.stream().filter(x-> x!=null).forEach(x->System.out.print(x+" "));
		h.sort();
		System.out.println("\nAfter sorting\n");
		h.t.stream().filter(x-> x!=null).forEach(x->System.out.print(x+" "));
	}
	
	public void sink(int k, int n) {
		while(2*k<=n) {
			int j = 2*k;
			if((j+1)<=n&&SortUtils.less(t.get(j), t.get(j+1))) {
				j++;
			}
			if(SortUtils.less(t.get(j), t.get(k))) {
				break;
			}
			SortUtils.exchange(t, j, k);
			k=j;
		}
	}
	
	public void sort() {
		int p=N/2;
		while(p>0) {
			sink(p--, N);
		}
		while(N>0) {
			SortUtils.exchange(t, 1, N);
			sink(1, --N);
		}
	}
}
