package algs4cs.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entities.Transaction;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;
import edu.princeton.cs.introcs.StdIn;

/**
 * Execute the program -
 *  mvn exec:java -Dexec.mainClass=algs4cs.sorting.TwoWayHeapMaxPriorityQueue -Dexec.arguements=<data/tinyBatch.txt
 * 
 *  contents of tineBatch.txt
 *  =========================
Turing      6/17/1990   644.08
vonNeumann  3/26/2002  4121.85
Dijkstra    8/22/2007  2678.40
vonNeumann  1/11/1999  4409.74
Dijkstra   11/18/1995   837.42
Hoare       5/10/1993  3229.27
vonNeumann  2/12/1994  4732.35
Hoare       8/18/1992  4381.21
Turing      1/11/2002    66.10
Thompson    2/27/2000  4747.08
Turing      2/11/1991  2156.86
Hoare       8/12/2003  1025.70
vonNeumann 10/13/1993  2520.97
Dijkstra    9/10/2000   708.95
Turing     10/12/1993  3532.36
Hoare       2/10/2005  4050.20
 */

public class TwoWayHeapMaxPriorityQueue<T extends Comparable<T>> {
	int N = 0;
	Object[] t;
	static Logger l = LoggerFactory.getLogger(TwoWayHeapMaxPriorityQueue.class);
	
	public TwoWayHeapMaxPriorityQueue(int size) {
		t = new Object[size];
	}

	public void insert(T t) {
		if(isFull()) {
			throw new StorageFullException("Q is full");
		}
		this.t[++N]=t;
		swim();
	}
	@SuppressWarnings("unchecked")
	public T delMax() {
		if(isEmpty())
			throw new StorageEmptyException("Q is empty");
		T ret = (T)t[1];
		t[1] = t[N--];
		sink();
		return ret;
		
	}
	@SuppressWarnings("rawtypes")
	public void swim() {
		int k = N;
		while(k>1) {
			boolean exchanged = false;
			if (SortUtils.less((Comparable) t[k/2], (Comparable) t[k])) {
				SortUtils.exchange(t, k, k/2);
				exchanged = true;
			}
			if(!exchanged)
				break;
			k=k/2;
		}
	}

	@SuppressWarnings("rawtypes")
	public void sink() {
		//This is a easy to read but crude implementation of sink method. There are better versions available
		//See sinkIntuitive() API this is code is based on the code available in Algorithms 4th edition 
		int k = 1;
		while (2*k < N+1) {
			boolean exchanged = false;
			if(2*k<N && SortUtils.less((Comparable) t[k], (Comparable) t[2*k+1])) {
				if(SortUtils.less((Comparable) t[2*k+1], (Comparable) t[2*k])) {
					SortUtils.exchange(t, k, 2*k);
					exchanged = true;
					k*=2;
				} else {
					SortUtils.exchange(t, k, 2*k+1);
					exchanged = true;
					k=k*2+1;
				}
			} else {
				if(SortUtils.less((Comparable) t[k], (Comparable) t[2*k])) {
					SortUtils.exchange(t, k, 2*k);
					exchanged = true;
					k*=2;
				}
			}
			if(!exchanged)
				break;
		}
	}

	public void sinkIntuitive() {
		int k=1;
		while(2*k<N+1) {
			int j = 2*k;
			//TODO complete
		}
	}
	
	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}
	
	public boolean isFull() {
		return N==this.t.length-1;
	}
	
	public static void main(String[] args) {

		
		//This program sorts the transactions in descending order of amount and prints it 
		TwoWayHeapMaxPriorityQueue<Transaction> c = new TwoWayHeapMaxPriorityQueue<>(20);
		while (StdIn.hasNextLine()) {
			c.insert(new Transaction(StdIn.readLine()));
		}
		
		/*
		 *Another test program
	    Integer i[] = new Integer[16];
		for(int x=0; x<16; x++) {
			i[x] = StdRandom.uniform(100, 1000);
		}
		TwoWayHeapMaxPriorityQueue<Integer> c = new TwoWayHeapMaxPriorityQueue<>(20);
		for(int x=0; x<16; x++) {
			c.insert(i[x]);
		}*/
		
		l.info("top 10 is");
		while (c.N > 0) {
			l.info("{}",c.delMax());
		}
	
	}
}
