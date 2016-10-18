package art.of.programming.ds.tree;

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

	
	Node<T> T;
	
	public ThreadedBinaryTree(Node<T> root) {
		T = root;
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
	
}
