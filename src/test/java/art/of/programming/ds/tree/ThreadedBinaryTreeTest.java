package art.of.programming.ds.tree;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.Ignore;
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

	@Test
	public void testInsertTerminalLeftSubTree() throws Exception {
		ThreadedBinaryTree<Integer> tree = new ThreadedBinaryTree<>();
		Node<Integer> head = tree.getHead();
		assertEquals(1, head.LTAG);
		assertEquals(0, head.RTAG);//This will be the same during any point of shape of tree
		assertEquals(head, head.RLINK);//This will be the same during any point of shape of tree
		rTagAssertionForHEad.accept(head.RTAG);
		assertEquals(head.RLINK, head.LLINK);
		rTagAssertionForHEad.accept(head.RTAG);
		rLinkAssertionForHead.accept(head, head.RLINK);
		//The above assertions should be satisified for an empty threaded tree
		
		//Test to insert a terminal left subtree(root node in this case) under head
		Node<Integer> q = tree.insertLeftSubTree(head, 1);
		assertEquals(1, q.t.intValue());
		rTagAssertionForHEad.accept(head.RTAG);
		rLinkAssertionForHead.accept(head, head.RLINK);
		assertEquals(0, head.LTAG);
		assertSame(q, head.LLINK);
		
		assertSame(head, q.LLINK);
		assertSame(head, q.RLINK);
		assertEquals(1, q.LTAG);
		assertEquals(1, q.RTAG);
		
	}
	
	@Test
	public void testInsertLeftSubTreeInMiddle() throws Exception {
		ThreadedBinaryTree<Integer> tree = new ThreadedBinaryTree<>();
		Node<Integer> head = tree.getHead();
		assertEquals(1, head.LTAG);
		assertEquals(0, head.RTAG);//This will be the same during any point of shape of tree
		assertEquals(head, head.RLINK);//This will be the same during any point of shape of tree
		rTagAssertionForHEad.accept(head.RTAG);
		assertEquals(head.RLINK, head.LLINK);
		rTagAssertionForHEad.accept(head.RTAG);
		rLinkAssertionForHead.accept(head, head.RLINK);
		//The above assertions should be satisified for an empty threaded tree
		
		//Test to insert a terminal left subtree(root node in this case) under head
		Node<Integer> q = tree.insertLeftSubTree(head, 2);
		assertEquals(2, q.t.intValue());
		rTagAssertionForHEad.accept(head.RTAG);
		rLinkAssertionForHead.accept(head, head.RLINK);
		assertEquals(0, head.LTAG);
		assertSame(q, head.LLINK);
		
		assertSame(head, q.LLINK);
		assertSame(head, q.RLINK);
		assertEquals(1, q.LTAG);
		assertEquals(1, q.RTAG);
		
		//Test to insert a left sub tree in the middle
		Node<Integer> qChild = q;
		q = tree.insertLeftSubTree(head, 1);
		assertEquals(1, q.t.intValue());
		assertEquals(0, q.LTAG);
		assertEquals(1, q.RTAG);
		assertSame(head.LLINK, q);
		assertSame(q.LLINK, qChild);
		assertEquals(1, qChild.LTAG);
		assertEquals(1, qChild.RTAG);
		assertSame(qChild.LLINK, head);
		assertSame(qChild.RLINK, q);
		
	}
	

	private Consumer<Integer> rTagAssertionForHEad = (rtag) -> assertEquals(0, rtag.intValue()); //This will be the same during any point of shape of tree
	private BiConsumer<Node<Integer>, Node<Integer>> rLinkAssertionForHead = (val, rLink) -> assertSame(val, rLink); //This will be the same during any point of shape of tree
	
 	@Test
	public void testInsertRightSubTreeHead() throws Exception {
		ThreadedBinaryTree<Integer> tree = new ThreadedBinaryTree<>();
		Node<Integer> head = tree.getHead();
		try {
			tree.insertRightSubTree(head, 10);
			fail("Expected error for trying to insert to right of head");
		} catch(RuntimeException e) {
			//All is well
		}
		
	}
 	
 	@Test
	public void testInsertTerminalRightSubTree() throws Exception {
		ThreadedBinaryTree<Integer> tree = new ThreadedBinaryTree<>();
		Node<Integer> head = tree.getHead();
		Node<Integer> p = addRoot(head, 1);
		Node<Integer> q = tree.insertRightSubTree(p, 3);
		assertEquals(3, q.t.intValue());
		assertSame(head, p.LLINK); assertEquals(1, p.LTAG);
		assertEquals(0, p.RTAG); assertSame(q, p.RLINK);
		assertEquals(1, q.RTAG); assertSame(head, q.RLINK);
		assertEquals(1, q.LTAG); assertSame(p, q.LLINK);
		
	}
 	
 	@Test
	public void testInsertRightSubTreeInMiddle() throws Exception {
 		ThreadedBinaryTree<Integer> tree = new ThreadedBinaryTree<>();
		Node<Integer> head = tree.getHead();
		Node<Integer> p = addRoot(head, 1);
		
		//Test to insert 3 as a child for root
		Node<Integer> q = tree.insertRightSubTree(p, 3);
		assertEquals(3, q.t.intValue());
		assertSame(head, p.LLINK); assertEquals(1, p.LTAG);
		assertEquals(0, p.RTAG); assertSame(q, p.RLINK);
		assertEquals(1, q.RTAG); assertSame(head, q.RLINK);
		assertEquals(1, q.LTAG); assertSame(p, q.LLINK);

		//Test to insert 2 as a child for root (implicit parent of 3)
		Node<Integer> qChild = q;
		assertSame(qChild, q);
		q = tree.insertRightSubTree(p, 2);
		assertEquals(2, q.t.intValue());
		assertEquals(0, p.RTAG); 
		assertSame(q, p.RLINK);
		assertEquals(0, q.RTAG); 
		assertSame(q.RLINK, qChild); 
		assertSame(head, qChild.RLINK);
		assertEquals(1, q.LTAG); 
		assertSame(p, q.LLINK);
		assertEquals(1, qChild.LTAG);
		assertSame(q, qChild.LLINK);

	}
 	
 	private Node<Integer> addRoot(Node<Integer> head, int rootVal) {
 		Node<Integer> root = new Node<>();
 		root.t=rootVal;
 		head.LTAG = 0; head.LLINK = root;
 		root.LTAG = 1; root.LLINK = head;
 		root.RTAG = 1; root.RLINK = head;
 		rTagAssertionForHEad.accept(head.RTAG);
		rLinkAssertionForHead.accept(head, head.RLINK);
		return root;
 	}


}
