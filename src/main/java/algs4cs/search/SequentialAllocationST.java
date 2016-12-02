package algs4cs.search;

import java.util.Arrays;
import java.util.Iterator;

public class SequentialAllocationST<K extends Comparable<K>, V> implements Iterable<K>{

	Node<K, V> first;
	int N=0;
	public V get(K key) {
		V val=null;
		for(Node<K, V> n=first; n!=null; n=n.next) {
			if(n.key.compareTo(key)==0) {
				val=n.val;
				break;
			}
		}
		return val;
	}
	
	public void put(K k, V v) {
		for(Node<K, V> n=first; n!=null; n=n.next) {
			if(n.key.compareTo(k)==0) {
				n.val=v;
				return;
			}
		}
		first=new Node<>(k, v, first);
		N++;
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N==0;
	}
	
	public boolean contains(K k) {
		return get(k)!=null;
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<K> keys() {
		if(N==0) {
			return Arrays.asList((K[])new Comparable[0]).iterator();
		}
		Comparable<K> [] o = new Comparable[N];
		int i=0;
		Node<K, V> x = first;
		while(x!=null) {
			o[i++]=x.key;
			x=x.next;
		}
		return Arrays.asList((K[])o).iterator();
	}

	@Override
	public Iterator<K> iterator() {
		return keys();
	}
	
	//Number of items under a given key
	public void delete(K key) {
		assert key!=null:"Key cannot be null";
		Node<K, V>x=first;
		Node<K, V>prev=null;
		while(x!=null) {
			if(x.key.compareTo(key)==0) {
				if(prev!=null) {
					prev.next=x.next;
					break;
				} else {
					//First node matched -- remove it
					first=first.next;
				}
				N--;
				break;
			} else {
				prev=x;
				x=x.next;
			}
		}
	}

}
