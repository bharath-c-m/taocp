package algs4cs.unionfind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;


/**
 * mvn exec:java  -Dexec.mainClass=algs4cs.unionfind.QuickUnion -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.unionfind.QuickUnion -Dexec.arguments=<data/tinyUF.txt
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
 */

public class QuickUnion {

	private int id[];
	int count;
	static Logger l = LoggerFactory.getLogger(QuickUnion.class);
	
	public QuickUnion(int N) {
		id = new int[N];
		for(int i=0; i<N; i++) {
			id[i] = i;
		}
		count = N;
	}
	
	
	public int find(int p) {
		while(p!=id[p]) {
			p = id[p];
		}
		return p;
	}
	
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot != qRoot) {
			id[pRoot] = qRoot;
			count--;
			l.info("{} {}",p,q);
		}
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public static void main(String a[]) {
		int n = StdIn.readInt();
		QuickUnion qu = new QuickUnion(n);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			qu.union(p, q);
		}
		
		l.info("Total number of components: {}", qu.count);
	}
}
