package algs4cs.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.In;

/**
 * To execute
 $ mvn exec:java -Dexec.mainClass=algs4cs.graph.ConnectedComponent -Dexec.args="data/tinyG.txt"
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
public class ConnectedComponent {

	Graph g;
	int id[];
	boolean marked[];
	int count;
	Logger l=LoggerFactory.getLogger(getClass());
	public ConnectedComponent(Graph g) {
		this.g=g;
		this.id=new int[g.V()];
		marked=new boolean[g.V()];
		for(int i=0; i<g.V(); i++) {
			if(!marked[i]){
				count++;
				dfs(g, i);
			}
		}
	}
	
	public boolean connected(int v, int w) {
		return id[v]==id[w];
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public void dfs(Graph g, int v) {
		marked[v]=true;
		id[v]=count;
		for(int w:g.adj(v)) {
			if(!marked[w]) {
				dfs(g, w);
			}
		}
	}
	
	public int componentCount() {
		return count;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(new In(args[0]));
		ConnectedComponent cc = new ConnectedComponent(g);
		cc.l.info("Printing components");
		cc.l.info("Number of components"+cc.componentCount());
		
		for(int i=1;i<=cc.componentCount(); i++) {
			StringBuilder sb=new StringBuilder();
			for(int j=0; j<g.V(); j++) {
				if(cc.id(j)==i) {
					sb.append(j).append(" ");
				}
			}
			cc.l.info(sb.toString());
		}
	}
}
