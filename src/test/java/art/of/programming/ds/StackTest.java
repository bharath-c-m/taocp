package art.of.programming.ds;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.exception.StorageEmptyException;
import art.of.programming.exception.StorageFullException;
import mockit.integration.junit4.JMockit;
public class StackTest {
	
	/**
	 * Init 
	 * TOP = 0
	 * 
	 * Full stack
	 * TOP == X.length-1
	 * 
	 * Empty Stack
	 * TOP == 0
	 */
	
	Logger l = LoggerFactory.getLogger(StackTest.class);
	
	Stack s;
	
	@Before
	public void init() {
		s = new Stack(10);
		s.x = new int[] {0, 1, 2, 3, 4, 5, 0, 0, 0, 0};
		s.top = 5;
	}

	@Test
	public void testPop() throws Exception {
		assertEquals(5, s.pop());
		assertEquals(4, s.pop());
		assertEquals(3, s.pop());
		assertEquals(2, s.pop());
		assertEquals(1, s.pop());
	}

	@Test
	public void testPush() throws Exception {
		s.top = 5;
		s.push(6);
		s.push(7);
		s.push(8);
		assertEquals(6, s.x[6]);
		assertEquals(7, s.x[7]);
		assertEquals(8, s.x[8]);
	}
	
	@Test
	public void isEmpty() {
		Stack s = new Stack(10);
		assertTrue(s.isEmpty());
		s.push(10);;
		assertFalse(s.isEmpty());
	}
	
	@Test
	public void fullAdd() {
		s = new Stack(3);
		s.push(10);
		s.push(20);
		try {
			s.push(30);
			Assert.fail("Failed test, stack must have been full");
		} catch(StorageFullException e) {
			//All is well
		}
	}
	
	@Test
	public void emptyRemove() {
		s = new Stack(3);
		try {
			s.pop();
			Assert.fail("Failed test, stack must have been empty");
		} catch(StorageEmptyException e) {
			//All is well
		} 
	}

}
