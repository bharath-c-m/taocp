package algs4cs.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
public class BinarySearchSTTest {
	@Test
	public void testRank() throws Exception {
		BinarySearchST<Integer, Integer> b=new BinarySearchST<>(16);
		b.keys.add(1);
		b.keys.add(5);
		b.keys.add(50);
		b.keys.add(500);
		b.keys.add(5000);
		b.keys.add(50000);
		assertEquals(0,b.rank(0));
		assertEquals(0,b.rank(1));
		assertEquals(2,b.rank(10));
		assertEquals(3,b.rank(51));
		assertEquals(5,b.rank(40001));
		assertEquals(5,b.rank(50000));
		assertEquals(6,b.rank(50001));
	}
	@Test
	public void testGet() throws Exception {
		BinarySearchST<Integer, Integer> b=new BinarySearchST<>(16);
		b.keys.add(1);
		b.keys.add(5);
		b.keys.add(50);
		b.keys.add(500);
		b.keys.add(5000);
		b.keys.add(50000);
		b.vals.add(1);
		b.vals.add(5);
		b.vals.add(50);
		b.vals.add(500);
		b.vals.add(5000);
		b.vals.add(50000);
		assertEquals(1,b.get(1).intValue());
		assertNull(b.get(0));
		assertNull(b.get(2));
		assertEquals(5,b.get(5).intValue());
		assertNull(b.get(6));
		assertNull(b.get(49));
		assertEquals(50,b.get(50).intValue());
		assertNull(b.get(55));
		assertEquals(500,b.get(500).intValue());
		assertEquals(5000,b.get(5000).intValue());
		assertEquals(50000,b.get(50000).intValue());
		assertNull(b.get(50001));
	}
	@Test
	public void testPut() throws Exception {
		BinarySearchST<Integer, Integer> b=new BinarySearchST<>(16);
		b.put(1, 1);
		b.put(10, 10);
		assertEquals(1, b.keys.get(0).intValue());
		assertEquals(1, b.vals.get(0).intValue());
		assertEquals(10, b.keys.get(1).intValue());
		assertEquals(10, b.vals.get(1).intValue());
		b.put(5, 5);
		assertEquals(5, b.keys.get(1).intValue());
		assertEquals(5, b.vals.get(1).intValue());
		assertEquals(10, b.keys.get(2).intValue());
		assertEquals(10, b.vals.get(2).intValue());
		b.put(25, 25);
		assertEquals(25, b.keys.get(3).intValue());
		assertEquals(25, b.vals.get(3).intValue());
		
		b.put(2, 2);
		assertEquals(2, b.keys.get(1).intValue());
		assertEquals(2, b.vals.get(1).intValue());
		assertEquals(5, b.keys.get(2).intValue());
		assertEquals(5, b.vals.get(2).intValue());
		assertEquals(10, b.keys.get(3).intValue());
		assertEquals(10, b.vals.get(3).intValue());
		assertEquals(25, b.keys.get(4).intValue());
		assertEquals(25, b.vals.get(4).intValue());
	}
	@Test
	public void testDelete() throws Exception {
		BinarySearchST<Integer, Integer> b=new BinarySearchST<>(16);
		b.keys.add(1);
		b.keys.add(5);
		b.keys.add(50);
		b.keys.add(500);
		b.keys.add(5000);
		b.keys.add(50000);
		b.vals.add(1);
		b.vals.add(5);
		b.vals.add(50);
		b.vals.add(500);
		b.vals.add(5000);
		b.vals.add(50000);
		
		assertEquals(500,b.keys.get(3).intValue());
		assertEquals(50000,b.keys.get(5).intValue());
		b.delete(500);
		assertEquals(5000,b.keys.get(3).intValue());
		assertEquals(50000,b.keys.get(4).intValue());
		assertNull(b.keys.get(5));
		b.delete(50000);
		assertNull(b.keys.get(4));
	}
	@Test
	public void testDelMin() {
		BinarySearchST<Integer, Integer> b=new BinarySearchST<>(16);
		b.keys.add(1);
		b.keys.add(5);
		b.keys.add(50);
		b.keys.add(500);
		b.keys.add(5000);
		b.keys.add(50000);
		b.vals.add(1);
		b.vals.add(5);
		b.vals.add(50);
		b.vals.add(500);
		b.vals.add(5000);
		b.vals.add(50000);
		assertEquals(6, b.size());
		assertEquals(1,b.keys.get(0).intValue());
		assertEquals(1,b.vals.get(0).intValue());
		b.deleteMin();
		assertEquals(5,b.keys.get(0).intValue());
		assertEquals(5,b.vals.get(0).intValue());
		assertEquals(5, b.size());
	}
	@Test
	public void testDelMax() {
		BinarySearchST<Integer, Integer> b=new BinarySearchST<>(16);
		b.keys.add(1);
		b.keys.add(5);
		b.keys.add(50);
		b.keys.add(500);
		b.keys.add(5000);
		b.keys.add(50000);
		b.vals.add(1);
		b.vals.add(5);
		b.vals.add(50);
		b.vals.add(500);
		b.vals.add(5000);
		b.vals.add(50000);
		assertEquals(6, b.size());
		assertEquals(50000,b.keys.get(5).intValue());
		assertEquals(50000,b.vals.get(5).intValue());
		b.deleteMax();
		assertEquals(5000,b.keys.get(4).intValue());
		assertEquals(5000,b.vals.get(4).intValue());
		assertEquals(5, b.size());
		assertNull(b.keys.get(5));
	}
	
}
