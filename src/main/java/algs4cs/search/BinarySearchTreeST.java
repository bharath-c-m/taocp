package algs4cs.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class BinarySearchTreeST<K extends Comparable<K>, V>  {

	Node root;
	
	public class Node {
		K k;
		V v;
		int N;
		Node left;
		Node right;
		
		public String toString() {
			return k!=null?k.toString():"EOF";
		}
	}
	public int size() {
		if(root==null)
			return 0;
		else
			return size(root);
	}
	private int size(Node n) {
		if(n==null)
			return 0;
		return n.N;
	}
	public V get(K k){
		return get(root, k);
	}
	private V get(Node x, K k) {
		if(x==null) {
			return null;
		} else {
			int compare=k.compareTo(x.k);
			if(compare<0)
				return get(x.left, k);
			else if(compare>0)
				return get(x.right, k);
			else
				return x.v;
		}
	}
	public void put(K k, V v) {
		root=put(root, k, v);
	}
	private Node put(Node x, K k, V v) {
		if(x==null) {
			Node n = new Node();
			n.k=k;
			n.v=v;
			n.N=1;
			return n;
		} else {
			int compare=k.compareTo(x.k);
			if(compare<0) {
				x.left=put(x.left, k, v);
			} else if(compare>0) {
				x.right=put(x.right, k, v);
			} else {
				x.v=v;
			}
			x.N=size(x.left)+size(x.right)+1;
			return x;
		}
	}
	public V min() {
		return min(root);
	}
	private V min(Node x) {
		if(x==null)
			return null;
		if(x.left==null)
			return x.v;
		else return min(x.left);
	}
	
	public Node findMin() {
		return findMin(root);
	}
	private Node findMin(Node x) {
		if(x==null)
			return null;
		if(x.left==null)
			return x;
		else return findMin(x.left);
	}
	
	public Node findMax() {
		return findMax(root);
	}
	private Node findMax(Node x) {
		if(x==null)
			return null;
		if(x.right==null)
			return x;
		else return findMax(x.right);
	}
	
	public V max() {
		return max(root);
	}
	private V max(Node x) {
		if(x==null)
			return null;
		if(x.right==null)
			return x.v;
		return max(x.right);
	}
	public Node floor(K k) {
		return floor(root, k);
	}
	private Node floor(Node x, K k) {
		if(x==null)
			return null;
		int compare=k.compareTo(x.k);
		if(compare<0) {
			return floor(x.left, k);
		} else if(compare==0)
			return x;
		Node t=floor(x.right, k);
		if(t==null)
			return x;
		else
			return t;
	}
	public Node ceiling(K k) {
		return ceiling(root, k);
	}
	public Node ceiling(Node x, K k) {
		if(x==null)
			return null;
		int c=k.compareTo(x.k);
		if(c<0){
			Node n=ceiling(x.left, k);
			if(n==null)
				return x;
			else
				return n;
		} else if(c>0)
			return ceiling(x.right, k);
		else 
			return x;
	}
	public Node select(int k) {
		return select(root, k);
	}
	private Node select(Node x, int k) {
		if(x==null)
			return null;
		if(k==0&&x.left==null)
			return x;
		int t=0;
		if(x.left!=null)
			t=x.left.N;
		if(k>t) {
			k=k-t-1;
			return select(x.right, k);
		} else if(k<t) {
			return select(x.left, k);
		} else 
			return x;
	}
	public int rank(K k) {
		return rank(root, k);
	}
	private int rank(Node x, K k) {
		if(x==null)
			return 0;
		int c=k.compareTo(x.k);
		if(c<0){
			return rank(x.left, k);
		} else if(c>0){
			int t=0;
			if(x.left!=null)
				t=x.left.N;
			return rank(x.right, k)+t+1;
		} else {
			int t=0;
			if(x.left!=null)
				t=x.left.N;
			return t;
		}
	}
	public Node deleteMin() {
		root=deleteMin(root);
		return root;
	}
	private Node deleteMin(Node x) {
		if(x==null)
			return null;
		if(x.left!=null) {
			x.left=deleteMin(x.left);
			return x;
		}
		else 
			return x.right;
		
	}
	public Node deleteMax() {
		root=deleteMax(root);
		return root;
	}
	private Node deleteMax(Node x) {
		if(x==null)
			return null;
		if(x.right!=null) {
			x.right=deleteMax(x.right);
			return x;
		} else {
			return x.left;
		}
	}
	public void delete(K k) {
		root=delete(root, k);
	}
	private Node delete(Node x, K k) {
		if(x==null || k==null) {
			return null;
		}
		int c=k.compareTo(x.k);
		if(c<0) {//go left
			x.left=delete(x.left, k);
		} else if (c>0) {//go right
			x.right=delete(x.right, k);
		} else { //found match
			if(x.right==null)
				return x.left;
			if(x.left==null)
				return x.right;
			Node t=findMin(x.right);
			x.right=deleteMin(x.right);
			x.k=t.k;
			x.v=t.v;
			x.N=size(x.left)+size(x.right)+1;
		}
		return x;
	}
	
	public Iterable<K> keys() {
		List<K> keys=new ArrayList<>();
		if(root==null)
			return keys;
		keys(root, keys);
		return keys;
	}
	private void keys(Node x, List<K> keys) {
		if(x==null)
			return;
		keys(x.left, keys);
		keys.add(x.k);
		keys(x.right, keys);
	}
	public Iterable<K> keys(K lo, K hi) {
		List<K> subKeys=new ArrayList<>();
		keys().forEach((k)->{if(k.compareTo(lo)>=0&&k.compareTo(hi)<=0)subKeys.add(k);});
		return subKeys;
	}
	
	//Prints the tree in-order
	public void printBinaryTree() {
		assert root!=null:"Tree is empty";
		print(root);
	}
	private void print(Node x) {
		if(x==null)
			return;
		print(x.left);
		System.out.println(x.k+"::"+x.v);
		print(x.right);
	}
	
}
