package art.of.programming.ds.applications;

import art.of.programming.ds.Node;
import static org.junit.Assert.*;

import org.junit.Test;
public class ReverseNodeTest {

	@Test
	public void testReverse() {
		Node n1=new Node();
		Node n2=new Node();
		Node n3=new Node();
		Node n4=new Node();
		Node n5=new Node();
		
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;
		
		n1.val=1;
		n2.val=2;
		n3.val=3;
		n4.val=4;
		n5.val=5;
		
		assertEquals(2, n1.next.val);
		assertEquals(3, n2.next.val);
		assertEquals(4, n3.next.val);
		assertEquals(5, n4.next.val);
		assertNull(n5.next);
		
		ReverseNode.reverse(n1);
		
		assertEquals(4, n5.next.val);
		assertEquals(3, n4.next.val);
		assertEquals(2, n3.next.val);
		assertEquals(1, n2.next.val);
		assertNull(n1.next);
	}
	
	@Test
	public void testReverseRecur() {
		Node n1=new Node();
		Node n2=new Node();
		Node n3=new Node();
		Node n4=new Node();
		Node n5=new Node();
		
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;
		
		n1.val=1;
		n2.val=2;
		n3.val=3;
		n4.val=4;
		n5.val=5;
		
		assertEquals(2, n1.next.val);
		assertEquals(3, n2.next.val);
		assertEquals(4, n3.next.val);
		assertEquals(5, n4.next.val);
		assertNull(n5.next);
		
	    new ReverseNode().reverseRecur(n1);
		
		assertEquals(4, n5.next.val);
		assertEquals(3, n4.next.val);
		assertEquals(2, n3.next.val);
		assertEquals(1, n2.next.val);
		assertNull(n1.next);
	}

	@Test
	public void testReverseNodeKGroup() throws Exception {
		Node n1=new Node();
		Node n2=new Node();
		Node n3=new Node();
		Node n4=new Node();
		Node n5=new Node();
		
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
//		n4.next=n5;
		
		n1.val=1;
		n2.val=2;
		n3.val=3;
		n4.val=4;
		n5.val=5;
		
		assertEquals(2, n1.next.val);
		assertEquals(3, n2.next.val);
		assertEquals(4, n3.next.val);
//		assertEquals(5, n4.next.val);
		assertNull(n4.next);
		
		ReverseNode.reverseNodeKGroup(n1,2);
		assertEquals(4, n1.next.val);
		assertEquals(1, n2.next.val);
		assertEquals(3, n4.next.val);
//		assertEquals(5, n3.next.val);
		assertNull(n3.next);
		
		
	}
	
	@Test
	public void testReverseNodeKGroupAndChange1() throws Exception {
		Node n1=new Node();
		Node n2=new Node();
		Node n3=new Node();
		Node n4=new Node();
		Node n5=new Node();
		
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;
		
		n1.val=1;
		n2.val=2;
		n3.val=3;
		n4.val=4;
		n5.val=5;
		
		assertEquals(2, n1.next.val);
		assertEquals(3, n2.next.val);
		assertEquals(4, n3.next.val);
		assertEquals(5, n4.next.val);
		assertNull(n5.next);
		
		ReverseNode.reverseNodeKGroup(n1,2);
		assertEquals(4, n1.next.val);
		assertEquals(1, n2.next.val);
		assertEquals(3, n4.next.val);
		assertEquals(5, n3.next.val);
		assertNull(n5.next);
	}
	@Test
	public void testReverseNodeKGroupAndChange2() throws Exception {
		Node n1=new Node();
		Node n2=new Node();
		Node n3=new Node();
		Node n4=new Node();
		Node n5=new Node();
		
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;
		
		n1.val=1;
		n2.val=2;
		n3.val=3;
		n4.val=4;
		n5.val=5;
		
		assertEquals(2, n1.next.val);
		assertEquals(3, n2.next.val);
		assertEquals(4, n3.next.val);
		assertEquals(5, n4.next.val);
		assertNull(n5.next);
		
		ReverseNode.reverseNodeKGroup(n1,3);
		assertEquals(1, n2.next.val);
		assertEquals(2, n3.next.val);
		assertEquals(5, n4.next.val);
		assertEquals(4, n1.next.val);
		assertNull(n5.next);
	}
}
