package algs4cs.unionfind;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdIn;


/**
 * mvn exec:java  -Dexec.mainClass=algs4cs.unionfind.VerboseWeightedQuickUnion -Dexec.arguments=<$file_location
 * Ex: mvn exec:java  -Dexec.mainClass=algs4cs.unionfind.VerboseWeightedQuickUnion -Dexec.arguments=<data/tinyUF.txt
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
public class VerboseWeightedQuickUnion {

	private int id[];
	int count;
	static Logger l = LoggerFactory.getLogger(VerboseWeightedQuickUnion.class);
	private int size[];
	
	int totalArrayReads;
	int totalArrayWrites;
	int totalArrayReadsForThisPair;
	int totalArrayWritesForThisPair;
	
	private static boolean extremeAnalysisEnabled = false;
	
	public VerboseWeightedQuickUnion(int N) {
		id = new int[N];
		size = new int[N];
		for(int i=0; i<N; i++) {
			id[i] = i;
			size[i] = 1;
		}
		count = N;
	}
	
	
	public int find(int p) {
		while (p != getId(p))
			p = getId(p);
		return p;
	}
	
	public void union(int p, int q) {
		totalArrayReadsForThisPair = 0;
		totalArrayWritesForThisPair = 0;
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP != rootQ) {
			if(size[rootP]<size[rootQ]) {
//				id[rootP] = rootQ;
				storeId(rootP, rootQ);
				size[rootQ] += size[rootP]; //Not counting the size array access for algorithm analysis
			} else {
//				id[rootQ] = rootP;
				storeId(rootQ, rootP);
				size[rootP] += size[rootQ]; //Not counting the size array access for algorithm analysis
			}
			count--;
			l.info("Connecting {} {}. Stats: TotalReads:{}, TotalWrites:{}",p,q, totalArrayReadsForThisPair, totalArrayWritesForThisPair);
		} else {
			l.info("Already Connected {} {}. Stats: TotalReads:{}, TotalWrites:{}", p, q, totalArrayReadsForThisPair, totalArrayWritesForThisPair);
		}
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public static void main(String a[]) {
		int n = StdIn.readInt();
		VerboseWeightedQuickUnion wqu = new VerboseWeightedQuickUnion(n);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			wqu.union(p, q);
		}
		
		l.info("Total number of components: {}", wqu.count);
		l.info("Overall stats. TotalReads:{}, TotalWrites:{}, TotalArrayAccess:{}", wqu.totalArrayReads, wqu.totalArrayWrites, wqu.totalArrayReads+wqu.totalArrayWrites);
		l.info("Final parent nodes array : {}", Arrays.toString(wqu.id));
		l.info("Final node sizes : {}", Arrays.toString(wqu.size));
		if(extremeAnalysisEnabled) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for(int i=0; i<wqu.id.length; i++) {
			sb.append(i).append("(").
			append(wqu.id[i]).
			append(":").
			append(wqu.size[i]).
			append(")").
			append(";");
			if(i!=0&&i%10==0)
				sb.append("\n");
		}
		l.info("Node:Size mappings -- >");
		l.info(sb.toString());
		}
	}
	
	
	//Helper method to help with algorithm analysis
	public int getId(int index) {
		totalArrayReads++;
		totalArrayReadsForThisPair++;
		return id[index];
	}

	//Helper method to help with algorithm analysis
	public void storeId(int index, int val) {
		totalArrayWrites++;
		totalArrayWritesForThisPair++;
		id[index] = val;
	}
}
