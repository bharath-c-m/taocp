package algs4cs.search;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.sorting.SortUtils;
import edu.princeton.cs.introcs.StdIn;

/**
 * 
 * This is a very crude implementation for a symbol table using 
 * sequential allocation.
 * @param <K>
 * @param <V>
 */
public class SequentialAllocationST<K extends Comparable<K>, V> implements Iterable<K>{

	Node<K, V> first;
	int N=0;
	SortUtils.StatsCollector sc = SortUtils.newStatsCollector();
	Logger l = LoggerFactory.getLogger(getClass());
	public V get(K key) {
		V val=null;
		for(Node<K, V> n=first; n!=null; n=n.next) {
			if(SortUtils.isEqual(n.key, key, sc)) {
				val=n.val;
				break;
			}
		}
		return val;
	}
	
	/**
	 * It should take ~N^2/2 compare operations to determine
	 * put N items.
	 * 
	 * @param k
	 * @param v
	 */
	public void put(K k, V v) {
		for(Node<K, V> n=first; n!=null; n=n.next) {
			if(SortUtils.isEqual(n.key, k, sc)) {
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
	
	public static void main(String[] args) {
//		$ mvn exec:java -Dexec.mainClass=algs4cs.search.SequentialAllocationST -Dexec.arguements=<data/1000words.txt
//		The above mvn command executes this program with a file of 1000 words in random order and inserts it into the map.
//		In theory it should take about N^2/2 compare operations. 
//		Testing it came to 
//		compareCount:499500 -- it is 1000 short because we are comparing only 999 keys
//		So technically it is 500500 compares
//		It checksout so accurately because the 1000 words i tested are all unique. If it had duplicates keys it would have been less
		SequentialAllocationST<String, Integer> st=new SequentialAllocationST<>();
		st.l.info("Updating items ..");
		while(!StdIn.isEmpty()) {
			st.put(StdIn.readString(), 0);
		}
		st.l.info("Size of the map is -- {}",st.size());
		st.l.info("Stats ->");
		st.sc.printStats();
	}
	
	public void putIf(Predicate<K> predK, K k, V v) {
		if(predK.test(k))
			put(k, v);
	}

	public void printStats() {
		if(sc!=null)
			sc.printStats();
		else
			l.info("No Stats to print");
	}
}
