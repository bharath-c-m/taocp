package art.of.programming.ds.applications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.ds.Node;

public class InvertLinkedList {

	Node p = null;
	Logger l = LoggerFactory.getLogger(InvertLinkedList.class);
	
	public InvertLinkedList(Node n) {
		p = n;
	}

	public void invert() {
		l.info("Before Invert {}", p);
		Node r=null;
		while(p!=null) {
			Node q=p;
			p = p.next;
			q.next = r;
			r = q;
		}
		p=r;
		l.info("After Invert {}", p);
	}
}
