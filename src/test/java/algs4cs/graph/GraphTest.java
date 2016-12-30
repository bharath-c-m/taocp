package algs4cs.graph;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

	@Test
	@Ignore
	public void testHasEdge() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCanSafeRemoveVertex() throws Exception {
		assertEquals(4, Graph.canSafeRemoveVertex(getConnectedGraph1()));
		assertEquals(1, Graph.canSafeRemoveVertex(getConnectedGraph2()));
	}
	
	private Graph getConnectedGraph1() {
		Graph g=new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		return g;
	}
	
	private Graph getConnectedGraph2() {
		Graph g=new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
		return g;
	}

}
