package art.of.programming.ds;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * A very crude implementation of a re-sizing array.
 * 
 * This is a quick and dirty implementation purley for academic purposes
 * 
 * The operations in this class are NOT thread safe
 * 
 * Client code needs to take care of any syncronization issues that may occur. Although it is pretty simple to acheive it.
 * 
 * the array doesnt grow down
 * @param <T>
 */
public class ResizingArray<T> implements Iterable<T>{

	Object[] o;
	private int N;
	private int len;
	private float resizeFactor = .5f;
	public ResizingArray(int capacity) {
		o = new Object[capacity];
		len = capacity;
	}
	
	public synchronized void add(T t) {
		if(isResizeRequired()) {
			reSize(getNewsize());
		}
		o[N++]=t;
	}
	
	private void reSize(int newSize) {
			Object[] newOb = new Object[newSize];
			for(int i=0; i<o.length; i++) {
				newOb[i] = o[i];
			}
			this.o = newOb;
			this.len = newSize;
		
	}
	
	private int getNewsize() {
		float newSize =len+(len*resizeFactor); 
		if(newSize>Integer.MAX_VALUE || newSize<=0) {
			throw new RuntimeException(String.format("array cannot grow out of bounds %d-%d",0, Integer.MAX_VALUE));
		} 
		return (int)newSize;	
	}
	
	public static void main(String[] args) {
		ResizingArray<Integer> r = new ResizingArray<>(5);
		r.add(1);r.add(2);r.add(3);r.add(4);r.add(5);r.add(6);
		r.add(1);r.add(2);r.add(3);r.add(4);r.add(5);r.add(6);
		r.add(1);r.add(2);r.add(3);r.add(4);r.add(5);r.add(6);
		System.out.println(r.get(r.getN()-1)); //Must be 6
		
		
		ResizingArray<String> s = new ResizingArray<>(2);
		s.add("Red");s.add("Rum");
		s.add("Red");s.add("Rum");
		s.add("Red");s.add("Rum");
		s.add("Red");s.add("Rum");
		s.add("Red");s.add("Rum");
		System.out.println(s.get(s.getN()-1)); //Must be Rum
	}
	
	private boolean isResizeRequired() {
			return N>len*resizeFactor;
	}
	
	//Not a thread safe operation. Client code must ensure the operation is atomic for any index based operation. 
	public int getN() {
		return N;
	}
	
	//Not a thread safe operation. Client code must ensure the operation is atomic for any index based operation.
	public T get(int i) {
		if(i>N) {
			throw new RuntimeException(String.format("Index: %d out of bounds of %d ", i, N));
		}
		return (T)o[i];
	}
	
	public void set(int i, T t) {
		if(i>N||i<0) {
			throw new RuntimeException(String.format("Index: %d out of bounds of %d ", i, N));
		}
		o[i] = t;
	}

	public int length() {
		return len;
	}
	
	public int  size() {
		return getN();
	}
	
	public String toString() {
		return Arrays.deepToString(o);
	}

	@Override
	public Iterator<T> iterator() {
		return (Iterator<T>)Arrays.asList(o).iterator();
//		return null;
	}
	
	public Stream<T> stream() {
		return (Stream<T>)Arrays.asList(o).stream();
	}
	
	public void remove(int index) {
		if(index>N||index<0) {
			throw new RuntimeException(String.format("Index: %d out of bounds of %d ", index, N));
		}
		while(index<N) {
			o[index]=o[++index];
		}
		o[N--]=null;
	}
}
