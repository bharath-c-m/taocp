package algs4cs.sorting.priorityq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.In;

/**
 * $ mvn exec:java  -Dexec.mainClass=algs4cs.sorting.priorityq.MultiwaySorting -Dexec.args="data/m1.txt data/m2.txt data/m3.txt"
 * 
 * The input are, streams of sorted strings sorted in descending order. The program merges it and produces a final descending sorted stream of string
 * 
 * $ cat data/m1.txt
 * Z I I G F C B A
 * 
 * $ cat data/m2.txt
 * Q Q P H D B
 * 
 * $ cat data/m3.txt
 * N J F E B A
 *
 */
public class MultiwaySorting {
	
	Logger l = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		MultiwaySorting ms = new MultiwaySorting();
		ms.l.info("Performing multiway sorting for streams length {}", args.length);
		In[] ins = new In[args.length+1];
		for(int i=1; i<ins.length; i++) {
//			ms.l.info("Creating stream for {}",args[i-1]);
			ins[i] = new In(args[i-1]);
		}
		ms.merge(ins);
	}
	
	public void merge(In[] ins) {
		IndexedMaxPriorityQ<String> ix = new IndexedMaxPriorityQ<>(ins.length-1);
		for(int i=1; i<ins.length; i++) {
			String s = ins[i].readString();
			ix.insert(i, s);
		}
		while(!ix.isEmpty()) {
			l.info("{}",ix.getMaxKey());
			int maxIndex = ix.delMax();
			if(!ins[maxIndex].isEmpty()) {
				ix.insert(maxIndex, ins[maxIndex].readString());
			}
		}
	}
}
