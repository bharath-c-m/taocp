package art.of.programming.ds;

import static org.junit.Assert.*;

import org.junit.Test;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;

public class StackLinkedAllocationTest {

	@Test
	public void testStackLinkedAllocation() throws Exception {
		StackLinkedAllocation s = new StackLinkedAllocation(3);
		assertNull(s.avail.next.next.next);
	}

	@Test
	public void testPush() throws Exception {
		StackLinkedAllocation s = new StackLinkedAllocation(5);
		assertNull(s.top);
		assertNotNull(s.avail);
		
		s.push(100);
		assertNotNull(s.top);
		assertEquals(100, s.top.val);
		assertNull(s.top.next);
		
		s.push(200);
		assertEquals(200, s.top.val);
		assertNotNull(s.top.next);
		assertEquals(100,s.top.next.val);
		assertNull(s.top.next.next);
	}

	@Test
	public void testPop() throws Exception {
		StackLinkedAllocation s = new StackLinkedAllocation(5);
		assertNull(s.top);
		assertNotNull(s.avail);
		
		Node p = s.avail;
		s.avail = s.avail.next;
		p.val = 100;
		p.next = s.top;
		s.top = p;
		
		p = s.avail;
		s.avail = s.avail.next;
		p.val = 200;
		p.next = s.top;
		s.top = p;
		
		assertEquals(200, s.pop());
		assertEquals(100, s.pop());
	}
	
	@Test
	public void testFullAdd() {
		StackLinkedAllocation s = new StackLinkedAllocation(5);
		s.push(10);
		s.push(20);
		s.push(30);
		s.push(40);
		s.push(50);
		try {
			s.push(60);
			fail("Stack must have been full");
		} catch(StorageFullException e) {
			//All is well
		}
		s.pop();
		s.push(60);
	}
	
	@Test
	public void testEmptyRemove() {
		StackLinkedAllocation s = new StackLinkedAllocation(5);
		try {
			s.pop();
			fail("Stack must have been Empty");
		} catch(StorageEmptyException e) {
			//All is well
		}
		s.push(10);
		s.pop();
	}

}
