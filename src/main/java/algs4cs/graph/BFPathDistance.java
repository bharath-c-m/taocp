package algs4cs.graph;

import java.util.ArrayList;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algorithms.Queue;
import edu.princeton.cs.introcs.In;

/**
 * To execute
 * $ mvn exec:java -Dexec.mainClass=algs4cs.graph.BFPathDistance -Dexec.args="data/tinyG.txt 0"
 */
/**
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
public class BFPathDistance {

	boolean marked[];
	int src;
	int edgesTo[];
	ArrayList<Integer> pathToSource[];
	Logger l=LoggerFactory.getLogger(getClass());
	@SuppressWarnings("unchecked")
	public BFPathDistance(Graph g, int src) {
		this.src=src;
		this.marked=new boolean[g.V()];
		this.edgesTo=new int[g.V()];
		this.pathToSource=new ArrayList[g.V()];
		for(int i=0; i<g.V(); i++) {
			this.pathToSource[i]=new ArrayList<Integer>();
		}
		bfs(g, src);
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		int x=v;
		Stack<Integer> s=new Stack<>();
		while(x!=src) {
			s.push(x);
			x=edgesTo[x];
		}s.push(x);
		return s;
	}
	
	//Enables calculating distance in constant time
	public int distTo(int v) {
		int dist=0;
		if(hasPathTo(v)) {
			dist=pathToSource[v].size();
		}
		return dist;
	}
	
	public void bfs(Graph g, int src) {
		Queue<Integer> q=new Queue<>();
		q.enqueue(src);
		marked[src]=true;
		while(!q.isEmpty()) {
			int v=q.dequeue();
			for(int w:g.adj(v)) {
				if(!marked[w]) {
					marked[w]=true;
					q.enqueue(w);
					edgesTo[w]=v;
					pathToSource[w].add(v);
					if(pathToSource[v].size()>0) {
						pathToSource[w].addAll(pathToSource[v]);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		BFPathDistance bfs = new BFPathDistance(g, s);
		bfs.l.info("Source is {}",s);
		bfs.l.info("Printing shortest pats");
		for (int i = 0; i < g.V(); i++) {
			StringBuilder sb = new StringBuilder();
			if (bfs.hasPathTo(i)) {
				sb.append(bfs.src).append(" to ").append(i).append(":" );
				for(int x:bfs.pathTo(i)) {
					sb.append(x).append(" ");
				}
				sb.append("\n\t\tDist to source=").append(bfs.distTo(i));
			} else {
				sb.append(bfs.src).append(" to ").append(i).append(": NO-PATH" );
			}
			bfs.l.info(sb.toString());
		}
	}
}
