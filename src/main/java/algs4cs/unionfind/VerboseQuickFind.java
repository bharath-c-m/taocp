package algs4cs.unionfind;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;

/**
 * mvn exec:java  -Dexec.mainClass=algs4cs.VerboseQuickFind.VerboseQuickFind -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.VerboseQuickFind.VerboseQuickFind -Dexec.arguments=<data/tinyUF.txt
 * 
 * The test data files required to test this application can be found at,
 * http://algs4.cs.princeton.edu/15uf/tinyUF.txt 
 * http://algs4.cs.princeton.edu/15uf/mediumUF.txt
 * http://algs4.cs.princeton.edu/15uf/largeUF.txt 
 * 
 * Executing the app with tinyUF.txt results in 2 components
 * Executing the app with mediumUF.txt results in 3 components
 * Executing the app with largeUF.txt results in 6 components 
 *  
 * Caution: Testing largeUF.txt using this algorithm takes a very long time. It took about 16 minutes in my machine. 
 * @see WeightedQuickUnion.java for improved performance
 * 
 * 
 */
public class VerboseQuickFind {

	public int[] id; //array of sites
	public int count; //count of individual components
	private static Logger l = LoggerFactory.getLogger(VerboseQuickFind.class);
	
	int totalArrayReadAccess;
	int totalArrayWriteAccess;
	int totalArrayReadAccessForThisPair;
	int totalArrayWriteAccessForThisPair;
	
	public VerboseQuickFind(int numSites) {
		//Initalize
		id = new int[numSites];
		count = numSites;
		for(int i = 0; i<id.length; i++) {
			id[i] = i;
		}
	}
	
	public int find(int p) {
		return getId(p);
	}
	
	public void union(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		
		if(pId == qId) {
			return;
		}
		//This 
		for(int i=0; i<id.length; i++) {
			if(getId(i) == pId) {
				storeId(i, qId);
//				id[i] = qId;
			}
		}
		count--;
 	}
	
	public boolean connected(int p, int q) {
//		return find(p) == find(q);
		return id[p] == id[q];
	}
	public int componentCount() {
		return count;
	}
	
	public static void main(String a[]) {
		int n = StdIn.readInt();
		VerboseQuickFind uf = new VerboseQuickFind(n);
		while(!StdIn.isEmpty()) {
			uf.totalArrayReadAccessForThisPair = 0;
			uf.totalArrayWriteAccessForThisPair = 0;
			
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(!uf.connected(p, q)) {
				uf.union(p, q);
				l.info("Connecting {} {}, Stats for this pair: TotalArrayReads:{}, TotalArrayWrites:{}",p,q, uf.totalArrayReadAccessForThisPair, uf.totalArrayWriteAccessForThisPair);
			} else {
				l.info("Already Connected {} {}, Stats for this pair: TotalArrayReads:{}, TotalArrayWrites:{}",p,q, uf.totalArrayReadAccessForThisPair, uf.totalArrayWriteAccessForThisPair);
			}
		}
		
		l.info("Total number of components: {}", uf.count);
		l.info("Total number of array reads: {}", uf.totalArrayReadAccess);
		l.info("Total number of array writes: {}", uf.totalArrayWriteAccess);
		l.info("ID array: {}", Arrays.toString(uf.id));
	}
	
	public int getId(int index) {
		totalArrayReadAccess++;
		totalArrayReadAccessForThisPair++;
		
		return id[index];
	}
	
	public void storeId(int index, int val) {
		totalArrayWriteAccess++;
		totalArrayWriteAccessForThisPair++;
		id[index] = val;
	}
}

