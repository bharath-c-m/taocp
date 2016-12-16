package art.of.programming.ds.applications;

import art.of.programming.ds.Node;

public class ReverseNode {

	public static Node reverse(Node p) {
		assert p!=null:"Input node cannot be null";
		Node r=null;
		System.out.println(p);
		while(p!=null) {
			Node q=p.next;
			p.next=null;
			if(r==null) {
				r = p;
			} else {
				p.next=r;
				r=p;
			}
			p=q;
		}
		System.out.println(r);
		return r;
	}
	
	public Node reverseRecur(Node p) {
		if(p.next==null) {
			return p;
		} else {
			Node x = reverseRecur(p.next);
			p.next=null;
			x.next=p;
			return p;
		}
	}
	
	
	public static Node reverseNodeKGroup(Node p, int k) {
		assert p!=null:"Input node cannot be null";
		assert k>0:"value for k should be > 0";
		Node group=null;
		Node result=null;
		int i=0;
		while(p!=null) {
			Node q=p.next;
			p.next=null;
			if(group==null) {
				group = p;
			} else {
				p.next=group;
				group=p;
			}
			if(++i%k==0) {
				i=0;//resets counter
				if(result==null) {
					result=group;
				} else {
					Node x=result;
					while(x.next!=null) {
						x=x.next;
					}
					x.next=group;
				}
				group=null;
			}
			p=q;
		}
		if(group!=null) {
			Node x=result;
			while(x.next!=null) {
				x=x.next;
			}
			x.next=reverse(group);
		}
		return result;
	}
	
	public static Node reverseNodeKGroupIntuitive(Node p, int k) {
		//Add intuitive solution
		return null;
	}
}
