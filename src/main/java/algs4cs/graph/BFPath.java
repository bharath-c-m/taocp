package algs4cs.graph;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algorithms.Queue;
import edu.princeton.cs.introcs.In;


public class BFPath {

	boolean marked[];
	int src;
	int edgesTo[];
	Logger l=LoggerFactory.getLogger(getClass());
	public BFPath(Graph g, int src) {
		this.src=src;
		this.marked=new boolean[g.V()];
		this.edgesTo=new int[g.V()];
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
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		BFPath bfs = new BFPath(g, s);
		bfs.l.info("Source is {}",s);
		bfs.l.info("Printing shortest pats");
		for (int i = 0; i < g.V(); i++) {
			StringBuilder sb = new StringBuilder();
			if (bfs.hasPathTo(i)) {
				sb.append(bfs.src).append(" to ").append(i).append(":" );
				for(int x:bfs.pathTo(i)) {
					sb.append(x).append(" ");
				}
			} else {
				sb.append(bfs.src).append(" to ").append(i).append(": NO-PATH" );
			}
			bfs.l.info(sb.toString());
		}
	}
}
