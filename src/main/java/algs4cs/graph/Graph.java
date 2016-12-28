package algs4cs.graph;

import java.util.ArrayList;

import edu.princeton.cs.introcs.In;

/**
 * 
 * To execute this program
 mvn exec:java -Dexec.mainClass=algs4cs.graph.Graph -Dexec.args="data/tinyG.txt"
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
public class Graph {

	public final int V;
	public int E;
	ArrayList<Integer>[] adj=null;

	@SuppressWarnings("unchecked")
	public Graph(int v) {
		this.V=v;
		adj=new ArrayList[V];
		for(int i=0; i<V; i++) {
			adj[i]=new ArrayList<>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Graph(In in) {
		V=in.readInt();
		int e=in.readInt();
		adj=new ArrayList[V];
		for(int i=0; i<V; i++) {
			adj[i]=new ArrayList<>();
		}
		while(e-->0) {
			addEdge(in.readInt(), in.readInt());
		}
	}
	
	public int V() {
		return this.V;
	}
	
	public int E() {
		return this.E;
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int v=0; v<V; v++) {
			sb.append(v).append(":");
			for(int i:adj[v]) {
				sb.append(i).append(" ");
			}sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		In i = new In(args[0]);
		Graph g = new Graph(i);
		System.out.println(g);
	}
}
