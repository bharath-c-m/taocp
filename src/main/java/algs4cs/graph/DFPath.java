package algs4cs.graph;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.In;

/**
 * 
 * To execute this program
 * 
 *  mvn exec:java -Dexec.mainClass=algs4cs.graph.DFPath -Dexec.args="data/tinyG.txt 5"
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
public class DFPath {

	private Logger l = LoggerFactory.getLogger(getClass());
	boolean marked[];
	int edgeTo[];
	int source;

	public DFPath(Graph g, int s) {
		assert g != null;
		marked=new boolean[g.V()];
		edgeTo=new int[g.V()];
		dfp(g, s);
		this.source=s;
	}

	public boolean hasPathTo(int v) {
		return this.marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		Stack<Integer> s=new Stack<>();
		if(hasPathTo(v)) {
			/*int x=v;
			while(edgeTo[x]!=source) {
				s.push(x);
				x=edgeTo[x];
			}s.push(source);*/
			for(int x=v; x!=source; x=edgeTo[x]) {
				s.push(x);
			}s.push(source);
		}
		return s;
	}

	private void dfp(Graph g, int s) {
		marked[s] = true;
		for (int v : g.adj(s)) {
			if (!marked[v]) {
				edgeTo[v]=s;
				dfp(g, v);
			}
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		DFPath dfp = new DFPath(g, s);
		dfp.l.info("Source is {}",s);
		for (int i = 0; i < g.V(); i++) {
			StringBuilder sb = new StringBuilder();
			if (dfp.hasPathTo(i)) {
				sb.append(dfp.source).append(" to ").append(i).append(":" );
				for(int x:dfp.pathTo(i)) {
					sb.append(x).append(" ");
				}
			} else {
				sb.append(dfp.source).append(" to ").append(i).append(": NO-PATH" );
			}
			dfp.l.info(sb.toString());
		}
	}
}
