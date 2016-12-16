package algs4cs.sorting.priorityq;

public class PQNode<T> {

	PQNode<T> p;
	PQNode<T> l;
	PQNode<T> r;
	T t;
	
	public T visit() {
		return t;
	}
	
	public boolean isLeaf() {
		return l==null&&r==null;
	}
	
	public String toString() {
		return t.toString();
	}
}
