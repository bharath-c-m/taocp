package algs4cs.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.In;

/**
 * 
 * To execute this program
 * 
 *  mvn exec:java -Dexec.mainClass=algs4cs.graph.DFSearch -Dexec.args="data/tinyG.txt 5"
 *  
 */

/**
 *  
$ cat data/tinyG.txt
13
13
0 5
4 3
0 1
9 12
6 4
5 4
0 2
11 12
9 10
0 6
7 8
9 11
5 3
*/
public class DFSearch {

	private Logger l=LoggerFactory.getLogger(getClass());
	boolean marked[];
	int count;
	public DFSearch(Graph g, int s) {
		assert g!=null;
		marked=new boolean[g.V()];
		dfs(g, s);
	}
	
	public boolean marked(int v) {
		return this.marked[v];
	}
	
	public int count() {
		return this.count;
	}
	
	private void dfs(Graph g, int s) {
		marked[s]=true;
		count++;
		for(int v:g.adj(s)) {
			if(!marked[v]) {
				dfs(g, v);
			}
		}
	}
	
	public static void main(String[] args) {
		Graph g=new Graph(new In(args[0]));
		int s=Integer.parseInt(args[1]);
		DFSearch dfs=new DFSearch(g,  s);
		if(dfs.count()==g.V()) {
			dfs.l.info("CONNECTED");
		} else {
			dfs.l.info("NOT-CONNECTED");
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<g.V(); i++) {
			if(dfs.marked(i)) {
				sb.append(i).append(" ");
			}
		}
		dfs.l.info(sb.toString());
	}
}
