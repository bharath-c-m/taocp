package art.of.programming.ds;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopologicalSorting {

	// To execute the program using maven
	//$ mvn exec:java  -Dexec.mainClass=art.of.programming.ds.TopologicalSorting
	
	
	int COUNT[];
	
	Node TOP[];
	
	int R;
	
	int F;
	
	int QLINK[];
	
	Node avail; //Storage Pool
	
	int N;
	
	Logger l = LoggerFactory.getLogger(TopologicalSorting.class);
	
	//This code is an implementation of algorithm-T for performing topological sort of numbers. TAOCP third edition, vol:1, pg:265 
	public void printTopologicalOrder(int n, int []partialOrderArray) {
		if(partialOrderArray == null || partialOrderArray.length ==0 || partialOrderArray.length%2 !=0 )
			throw new RuntimeException("Invalid partial order");
		//T1
		initialize(n, partialOrderArray.length/2);
		
		//T2, T3
		nextRelation(partialOrderArray);
		
		//T4
		scanForZeros();
		
		//T5
		printOutput();
	}
	
	public void initialize(int n, int numRelations) {
		l.info("Initializing partial order set of {} numbers with {} relations", n, numRelations);
		N = n;
		COUNT = new int[n+1];
		TOP = new Node[n+1];
		QLINK = new int[n+1];
		
		while(numRelations-->0) {
			Node node = new Node();
			node.next = avail;
			avail = node;
		}
		l.info(avail.toString());
	}
	
	public void nextRelation(int []partialOrderArray) {
		for(int j=0,k=1; j<partialOrderArray.length-1 && k < partialOrderArray.length; j+=2,k+=2) {
			//T3
			recordRelation(partialOrderArray[j], partialOrderArray[k]);
		}
		
		for(int x = 1; x<TOP.length; x++) {
			l.debug("Node in the relation is {} ",TOP[x]);
		}
		
//		This completes recording the relation in a hierarchial order
	}
	
//	In this args j, k should be read as j precedes k
	public void recordRelation(int j, int k) {
		l.debug("Recording relation for {} precedes {}", j, k);
		COUNT[k]+=1;
		Node p = getNodeFromStoragePool();
		p.SUC = k;
		p.next = TOP[j];
		TOP[j] = p;
	}
	
	public void scanForZeros() {
		R = 0;
		QLINK[0] = 0;
		l.debug("COUNT[] {} ",Arrays.toString(COUNT));
		for(int k = 1; k<=N; k++) {
			if(COUNT[k] == 0) {
				QLINK[R] = k;
				R = k;
			}
		}
		
		F = QLINK[0]; //This will contain the first value for which COUNT[k] was 0
		
	}
	
	public void printOutput() {
		l.debug("Printing topological sort order - "+F);
		l.info(""+F);
		if(F != 0 ) {
			N-=1;
			Node p = TOP[F];
			if(p == null) {
				//T7
				removeFromQueue();
			} else {
				eraseRelation(p);
			}
		} else {
			//T8 - End of program
			if(N == 0) {
				l.info("End of program");
			} else {
				if (N>0) {
					l.info("{} numbers in the input contian a loop in it", N);
				}
			}
		}
		
	}
	
	public void eraseRelation(Node p) {
		COUNT[p.SUC]-=1;
		if(COUNT[p.SUC]==0) {
			QLINK[R] = p.SUC;
			R = p.SUC;
		}
		
			p = p.next;
			if(p != null) {
				//T6
				eraseRelation(p);
			} else {
				//T7
				removeFromQueue();
			}
	}
	
	public void removeFromQueue() {
		F = QLINK[F];
		printOutput();
	}
	
	private Node getNodeFromStoragePool() {
		Node p = avail;
		avail = avail.next;
		p.next = null;
		return p;
	}
	
	class Node {
		int SUC;
		Node next;
		
		public String toString() {
			if(next == null) {
				return SUC+" -> /\\";
			}
			return SUC+" -> "+next.toString();
		}
	}
	
	public static void main(String[] args) {
		TopologicalSorting t = new TopologicalSorting();
		t.l.info("Topological sorting -- Alogrithm T -- TAOCP third edition, vol:1, pg:265");
		t.printTopologicalOrder(9, new int[]{9, 2, 3, 7, 7, 5, 5, 8, 8, 6, 4, 6, 1, 3, 7, 4, 9, 5, 2, 8});
		
		//A test case with a loop in it
//		t.printTopologicalOrder(5, new int[]{1,3,3,2,3,4,4,1,5,1});
	}
}
