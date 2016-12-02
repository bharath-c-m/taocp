package algs4cs.search;

public class Node<K extends Comparable<K>, V> {

	K key;
	V val;
	Node<K, V> next;
	
	public Node(K k, V v, Node<K, V> next) {
		this.key=k;
		this.val=v;
		this.next=next;
	}
}
