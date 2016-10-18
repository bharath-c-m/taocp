package art.of.programming.ds.tree;

/**
 *  Parameterized node that has two nodes linking to left and right.
 *  
 *  Contains value T
 */
public class Node<T> {
	T t;
	
	Node<T> LLINK;
	
	Node<T> RLINK;
	
	int LTAG; //Used in case of threaded trees
	
	int RTAG; //Used in case of threaded trees
	
	public T visit() {
		return  t;
	}
}
