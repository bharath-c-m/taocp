package algs4cs.sorting.priorityq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.sorting.SortUtils;
import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class IndexedMaxPriorityQ<T extends Comparable<T>> {
	Object[] keys;
	int N=0;
	int pq[];
	Logger l = LoggerFactory.getLogger(getClass());
	public IndexedMaxPriorityQ(int maxSize) {
		l.info("Init :"+maxSize);
		keys = new Object[maxSize+1];//add one to make it 1 based index
		pq = new int[maxSize+1];//add one to make it 1 based index
	}
	public void insert(int i, T t) {
		if(N==keys.length-1)
			throw new StorageFullException("pq is full");
		N++;
		pq[N] = i;
		keys[i] = t;
		swim();
	}
	public int delMax() {
		if(isEmpty())
			throw new StorageEmptyException("pq is empty");
		int maxIndex = pq[1]; 
		pq[1]=pq[N--];
		pq[N+1]=-1;
		sink();
		return maxIndex;
	}
	public int getMaxIndex() {
		return pq[1];
	}
	@SuppressWarnings("unchecked")
	public T getMaxKey() {
		return (T)keys[pq[1]];
	}
	@SuppressWarnings("rawtypes")
	public void swim() {
//		l.info("Swim - Keys:{}", Arrays.toString(keys));
//		l.info("Swim: - PQ{}", Arrays.toString(pq));
		int k = N;
		while(k>1) {
			boolean exchanged = false;
			if (SortUtils.less((Comparable) keys[pq[k/2]], (Comparable) keys[pq[k]])) {
				exchange(pq, k, k/2);
				exchanged = true;
			}
			if(!exchanged)
				break;
			k=k/2;
		}
	}

	@SuppressWarnings("rawtypes")
	public void sink() {
//		l.info("Sink - Keys:{}", Arrays.toString(keys));
//		l.info("Sink - PQ:{}", Arrays.toString(pq));
		//This is a easy to read but crude implementation of sink method. There are better versions available
		//See sinkIntuitive() API this is code is based on the code available in Algorithms 4th edition 
		int k = 1;
		while (2*k < N+1) {
			boolean exchanged = false;
			System.out.println("k is "+k+" N is "+N);
			if(2*k<N && SortUtils.less((Comparable) keys[pq[k]], (Comparable) keys[pq[2*k+1]])) {
				if(SortUtils.less((Comparable) keys[pq[2*k+1]], (Comparable) keys[pq[2*k]])) {
					exchange(pq, k, 2*k);
					exchanged = true;
					k*=2;
				} else {
					exchange(pq, k, 2*k+1);
					exchanged = true;
					k=k*2+1;
				}
			} else {
				if(SortUtils.less((Comparable) keys[pq[k]], (Comparable) keys[pq[2*k]])) {
					exchange(pq, k, 2*k);
					exchanged = true;
					k*=2;
				}
			}
			if(!exchanged)
				break;
		}
	}
	public boolean isEmpty() {
		return N==0;
	}
	public void exchange(Object[] o, int p, int q) {
		Object oP=o[p];
		o[p]=o[q];
		o[q]=oP;
	}
	
	public void exchange(int[] o, int p, int q) {
		int iP=o[p];
		o[p]=o[q];
		o[q]=iP;
	}
}
