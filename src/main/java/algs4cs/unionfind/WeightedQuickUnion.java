package algs4cs.unionfind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;


/**
 * mvn exec:java  -Dexec.mainClass=algs4cs.unionfind.WeightedQuickUnion -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.unionfind.WeightedQuickUnion -Dexec.arguments=<data/tinyUF.txt
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
public class WeightedQuickUnion {

	private int id[];
	int count;
	static Logger l = LoggerFactory.getLogger(WeightedQuickUnion.class);
	private int size[];
	
	public WeightedQuickUnion(int N) {
		id = new int[N];
		size = new int[N];
		for(int i=0; i<N; i++) {
			id[i] = i;
			size[i] = 1;
		}
		count = N;
	}
	
	
	public int find(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}
	
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP != rootQ) {
			if(size[rootP]<size[rootQ]) {
				id[rootP] = rootQ;
				size[rootQ] += size[rootP];
			} else {
				id[rootQ] = rootP;
				size[rootP] += size[rootQ];
			}
			count--;
			l.info("{} {}",p,q);
		}
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public static void main(String a[]) {
		int n = StdIn.readInt();
		WeightedQuickUnion wqu = new WeightedQuickUnion(n);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			wqu.union(p, q);
		}
		
		l.info("Total number of components: {}", wqu.count);
	}
}
