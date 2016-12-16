package algs4cs.search.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.search.SequentialAllocationST;
import edu.princeton.cs.introcs.StdIn;

/**
 * To execute program
 * export maven option -ea to enable assertion
 * 
 * shorthand --
 * export MAVEN_OPTS="-ea"&&mvn exec:java -Dexec.mainClass=algs4cs.search.client.FreqCounter -Dexec.arguements=<data/tinyST.txt
 */
public class FreqCounter {

	Logger l = LoggerFactory.getLogger(FreqCounter.class);
	
	public static void main(String[] args) {
		FreqCounter f = new FreqCounter();
//		f.testFrequCount(8);
		f.testCount();
	}
	
	public void testDelete() {
		l.info("Delete test");
		SequentialAllocationST<String, Integer> freqTableDel = new SequentialAllocationST<>();
		freqTableDel.put("Hello", 1);
		freqTableDel.put("World", 9);
		assert freqTableDel.size()==2:"Size must be 2";
		freqTableDel.delete("World");
		assert freqTableDel.size()==1:"Size must be 1";
		freqTableDel.delete("Hello");
		assert (freqTableDel.isEmpty()):"ST must have been empty";
		freqTableDel.delete("nothing-will-happen");
		assert (freqTableDel.isEmpty()):"ST must have been empty";
		l.info("Delete test successfull");
	}
	
	//DEtermines the frequency for all the words
	public void testBasicFreqCount() {
		SequentialAllocationST<String, Integer> freqTable = new SequentialAllocationST<>();
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(freqTable.contains(s)) {
				freqTable.put(s, freqTable.get(s)+1);
			}
			else {	
				freqTable.put(s, 1);
			}
		}
		
		l.info("Freq table size is {}",freqTable.size());
		for(String s : freqTable) {
			l.debug("{}:{}",s,freqTable.get(s));
		}
	}
	
	//Determines the frequency of all the words whose length is minimum minLen
	public void testFrequCount(int minLen) {
		SequentialAllocationST<String, Integer> freqTable = new SequentialAllocationST<>();
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(minLen>s.length())
				continue;
			if(freqTable.contains(s)) {
				freqTable.put(s, freqTable.get(s)+1);
			}
			else {	
				freqTable.put(s, 1);
			}
		}
		
		l.info("Freq table size is {}",freqTable.size());
		for(String s : freqTable) {
			l.debug("{}:{}",s,freqTable.get(s));
		}
		freqTable.printStats();
	}
	
	public void testCount() {
		SequentialAllocationST<String, Integer> freqTable = new SequentialAllocationST<>();
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			freqTable.put(s, -1);
		}
		l.info("Freq table size is {}",freqTable.size());
		for(String s : freqTable) {
			l.debug("{}:{}",s,freqTable.get(s));
		}
//		The number of compares shoud be ~N^2/2
		freqTable.printStats();
		
	}
	
}
