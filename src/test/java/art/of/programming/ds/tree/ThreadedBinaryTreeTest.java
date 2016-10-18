package art.of.programming.ds.tree;

import org.junit.Test;
import static org.junit.Assert.*;
public class ThreadedBinaryTreeTest {

	
	@Test
	public void testGetSuccessorPredcessorInOrder() throws Exception {
		/**
		 * Test tree
		 * 	
		 * 					A
		 * 				  /   \
		 * 				 B     C
		 *              /	  /	 \
		 *   		   /	 /	  \
		 * 			  /		/  	   \
		 * 			 D 	   E	    F
		 * 					\	   / \
		 * 					 G	  H	  J
		 * 
		 */
		
		/**
		 * In-Oreder traversal
		 * D-B-A-E-G-C-H-F-J
		 * 
		 */
		
		/**
		 * Pre-Order traversal
		 * A-B-D-C-E-G-F-H-J
		 * 
		 */
		
		/**
		 * Post-Order traversal
		 * D-B-G-E-H-J-F-C-A
		 * 
		 */
		
		/**
		 * In case of threaded trees,
		 *  when llink is null - ltag is set to 1 and llink points to higher node in the tree
		 *  when rlink is null - rtag is set to 1 and rlink points to higher node in the tree
		 *  
		 *  the extreme nodes are specal,
		 *   llink of the left extreme points to nothing
		 *   rllink of the right extreme points to nothing
		 */
		
		Node<String> root = getNode("A");
		Node<String> b = getNode("B");
		Node<String> c = getNode("C");
		Node<String> d = getNode("D");
		Node<String> e = getNode("E");
		Node<String> f = getNode("F");
		Node<String> g = getNode("G");
		Node<String> h = getNode("H");
		Node<String> j = getNode("J");
		
		root.LLINK = b; root.RLINK = c;
		
		b.LLINK = d;
		b.RTAG = 1;
		b.RLINK = root;
		
		d.RTAG = 1;
		d.RLINK = b;
		
		c.LLINK = e;
		c.RLINK = f;
		
		e.RLINK = g;
		e.LTAG = 1;
		e.LLINK = root;
		
		g.LTAG = g.RTAG = 1;
		g.LLINK = e;
		g.RLINK = c;
		
		f.LLINK = h;
		f.RLINK = j;
		
		h.LTAG = h.RTAG = 1;
		h.LLINK = c;
		h.RLINK = f;
		
		j.LTAG = 1;
		j.LLINK = f;
		
		ThreadedBinaryTree<String> tree = new ThreadedBinaryTree<>(root);
//		
		assertEquals("A",tree.getPredecessorInOrder(e).visit());
		assertEquals("D",tree.getPredecessorInOrder(b).visit());
		assertEquals("B",tree.getPredecessorInOrder(root).visit());
		assertEquals("H",tree.getPredecessorInOrder(f).visit());
		
		try {
			tree.getPredecessorInOrder(d);
			fail("Expected beginning of tree");
		}  catch (RuntimeException re) {
			//All is well
		}
		
		assertEquals("B",tree.getSuccessorInOrder(d).visit());
		assertEquals("G",tree.getSuccessorInOrder(e).visit());
		assertEquals("A",tree.getSuccessorInOrder(b).visit());
		assertEquals("E",tree.getSuccessorInOrder(root).visit());
		assertEquals("J",tree.getSuccessorInOrder(f).visit());
		try {
			tree.getSuccessorInOrder(j);
			fail("Expected end of tree");
		} catch (RuntimeException re) {
			//All is well
		}
		
	}

	private Node<String> getNode(String s) {
		Node<String> x = new Node<>();
		x.t = s;
		return x;
		
	}


}
