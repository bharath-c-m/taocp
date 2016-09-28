package art.of.programming.ds.applications;

import static org.junit.Assert.*;

import org.junit.Test;

import art.of.programming.ds.Node;

public class InvertLinkedListTest {

	@Test
	public void invert() {
		Node first=null;
		for(int i = 1; i<=5; i++) {
			Node n = new Node();
			n.next = first;
			n.val = i;
			first = n;
		}
		
		InvertLinkedList i = new InvertLinkedList(first);
		assertNotNull(i.p);
		assertEquals(5, i.p.val);
		assertEquals(4, i.p.next.val);
		assertEquals(1, i.p.next.next.next.next.val);
		i.invert();
		assertNotNull(i.p);
		assertEquals(1, i.p.val);
		assertEquals(2, i.p.next.val);
		assertEquals(5, i.p.next.next.next.next.val);
		
	}
}
