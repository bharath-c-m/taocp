package art.of.programming.ds.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.ds.generics.CircularList;

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

public class ThreadedBinaryTree<T> {

	
	CircularList<Node<T>> s;
	
	Node<T> head;
	
	Node<T> T;
	
	Logger l = LoggerFactory.getLogger(ThreadedBinaryTree.class);
	
	public ThreadedBinaryTree(Node<T> root) {
		T = root;
	}
	
	
	public ThreadedBinaryTree() {
		//This means the tree grows by inserting items to the left of the circular tree
		head = new Node<>();
		head.RLINK = head.LLINK = head;
		head.RTAG = 0;
		head.LTAG = 1;
	}
	
	//TAOCP notation - when P is a node in a threaded tree, P$ is the successor node IN-ORDER
	public Node<T> getSuccessorInOrder(Node<T> p) {
		if(p==null)
			throw new RuntimeException("Invalid node");
		
		Node<T> q = p.RLINK;
		if(p.RTAG == 1)
			return q;
		else {
			if(q == null) 
				throw new RuntimeException("Input node is the last node of the tree in-order");
			else {
				while(q.LTAG == 0) {
					q = q.LLINK;
				}
				return q;
			}
		}
	}
	
	//TAOCP notation - when P is a node in a threaded tree, $P is the predecessor node IN-ORDER
	public Node<T> getPredecessorInOrder(Node<T> p) {
		if(p==null)
			throw new RuntimeException("Invalid node");
		
		Node<T> q = p.LLINK;
		
		if(p.LTAG==1) {
			return q;
		} else {
			if(q == null) {
				throw new RuntimeException("Input node is the first node of the tree in-order");
			} else {
				while(q.RTAG == 0) {
					q = q.RLINK;
				}
				return q;
			}
		}
	}
	
	public Node<T> insertLeftSubTree(Node<T> p, T t) {
		Node<T> q = new Node<>();
		q.t = t;
		
		q.LTAG = p.LTAG;
		q.LLINK = p.LLINK;
		p.LLINK = q;
		p.LTAG = 0;
		q.RTAG = 1;
		q.RLINK = p;
		
		if(q.LTAG == 0) {
			Node<T> qPredecessor = getPredecessorInOrder(q);
			qPredecessor.RLINK = q;
		}
		
		return q;
			
	}
	
	public Node<T> insertRightSubTree(Node<T> p, T t) {
		if(p == head) {
			throw new RuntimeException ("Cannot insert a right sub-tree for the head");
		}
		Node<T> q = new Node<>();
		q.t = t;
		q.RTAG = p.RTAG;
		q.RLINK = p.RLINK;
		p.RLINK = q;
		p.RTAG = 0;
		q.LTAG = 1;
		q.LLINK = p;
		if(q.RTAG == 0) {
			//This means a node was inserted in the middle
			Node<T> qSuccessor = getSuccessorInOrder(q);
			qSuccessor.LLINK = q;
		}
		return q;
	}
	
	public Node<T> getHead() {
		return head;
	}
}
