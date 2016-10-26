package algs4cs.unionfind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;

/**
 * mvn exec:java  -Dexec.mainClass=algs4cs.unionfind.UnionFind -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.unionfind.UnionFind -Dexec.arguments=<data/tinyUF.txt
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
public class UnionFind {

	public int[] id; //array of sites
	public int count; //count of individual components
	private static Logger l = LoggerFactory.getLogger(UnionFind.class);
	
	public UnionFind(int numSites) {
		//Initalize
		id = new int[numSites];
		count = numSites;
		for(int i = 0; i<id.length; i++) {
			id[i] = i;
		}
	}
	
	public int find(int p) {
		return id[p];
	}
	
	public void union(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		
		if(pId == qId) {
			return;
		}
		//This 
		for(int i=0; i<id.length; i++) {
			if(id[i] == pId) {
				id[i] = qId;
			}
		}
		count--;
 	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	public int componentCount() {
		return count;
	}
	
	public static void main(String a[]) {
		int n = StdIn.readInt();
		UnionFind uf = new UnionFind(n);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(!uf.connected(p, q)) {
				uf.union(p, q);
				l.info("{} {}",p,q);
			}
		}
		
		l.info("Total number of components: {}", uf.count);
	}
}

