package algs4cs.search;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Ignore;
public class BinarySearchTreeSTIntegrationTestCases {

	@Test
	public void testSize() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		assertEquals(0, bst.size());
		bst.put(5, "A");
		assertEquals(1, bst.size());
		bst.put(6, "B");
		bst.put(1, "B");
		bst.put(10, "D");
		bst.put(2, "E");
		assertEquals(5, bst.size());
	}

	@Test
	public void testGetNPut() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(6, "H");
		bst.put(2, "E");
		bst.put(8, "L");
		bst.put(5, "L");
		bst.put(12, "O");
		assertEquals(6, bst.root.k.intValue());
		assertEquals("H", bst.root.v);
		assertEquals(5, bst.size());
		assertEquals("H", bst.get(6));
		assertEquals("E", bst.get(2));
		assertEquals("L", bst.get(8));
		assertEquals("L", bst.get(5));
		assertEquals("O", bst.get(12));
		assertNull(bst.get(55));
		assertNull(bst.get(0));
	}

	@Test
	public void testMin() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(6, "H");
		bst.put(2, "E");
		bst.put(8, "L");
		bst.put(5, "L");
		bst.put(12, "O");
		assertEquals(6, bst.root.k.intValue());
		assertEquals("H", bst.root.v);
		assertEquals(5, bst.size());
		assertEquals("E", bst.min());
		
		bst=new BinarySearchTreeST<>();
		assertNull(bst.min());
		
		bst=new BinarySearchTreeST<>();
		bst.put(6, "H");
		bst.put(8, "L");
		bst.put(12, "O");
		assertEquals("H", bst.min());
	}

	@Test
	public void testMax() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(6, "H");
		bst.put(2, "E");
		bst.put(8, "L");
		bst.put(5, "L");
		bst.put(12, "O");
		assertEquals(6, bst.root.k.intValue());
		assertEquals("H", bst.root.v);
		assertEquals(5, bst.size());
		assertEquals("O", bst.max());
		
		bst=new BinarySearchTreeST<>();
		assertNull(bst.max());
		
		bst=new BinarySearchTreeST<>();
		bst.put(12, "O");
		bst.put(8, "L");
		bst.put(6, "H");
		assertEquals("O", bst.max());
	}

	@Test
	public void testFloor() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		assertEquals(7, bst.root.k.intValue());
		assertEquals("H", bst.root.v);
		assertEquals(5, bst.size());
		assertEquals("A", bst.floor(6).v);
		assertEquals("H", bst.floor(7).v);
		assertEquals("L", bst.floor(9).v);
		assertEquals("O", bst.floor(1300).v);
		assertNull(bst.floor(1));
	}

	@Test
	public void testCeiling() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		assertEquals(7, bst.root.k.intValue());
		assertEquals("H", bst.root.v);
		assertEquals(5, bst.size());
		assertEquals("H", bst.ceiling(6).v);
		assertEquals("H", bst.ceiling(7).v);
		assertEquals("O", bst.ceiling(9).v);
		assertEquals("E", bst.ceiling(1).v);
		assertNull(bst.ceiling(1300));
	}

	@Test
	public void testSelect() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		assertEquals(bst.select(0).k.intValue(), 7);
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		assertEquals(8, bst.select(3).k.intValue());
		assertEquals(5, bst.select(1).k.intValue());
		assertEquals(2, bst.select(0).k.intValue());
		assertEquals(12, bst.select(4).k.intValue());
		assertNull(bst.select(400));//out of range
		assertNull(bst.select(-400));//out of range
	}

	@Test
	public void testRank() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		assertEquals(2, bst.rank(7));
		assertEquals(3, bst.rank(8));
		assertEquals(4, bst.rank(12));
		assertEquals(5, bst.rank(40));
	}

	@Test
	public void testDeleteMin() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		assertEquals(1, bst.rank(5));
		bst.deleteMin();
		assertEquals(0, bst.rank(5)); //Rank lowers to 0
		
	}

	@Test
	public void testDeleteMax() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		assertEquals(5, bst.rank(13));
		bst.deleteMax();
		assertEquals(4, bst.rank(13)); //Rank lowers to 0
	}

	@Test
	public void testDelete() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		bst.put(9, "P");//r1r2l1
		bst.put(1, "X");//l1l1
		bst.put(4, "Y");//l1r1l2
		bst.put(6, "Z");//l1r1r2
		StringBuilder sb = new StringBuilder();
		bst.keys().forEach((i)->sb.append(i));
		assertEquals("1245678912",sb.toString());
		
		bst.delete(5);
		sb.delete(0, sb.length());
		bst.keys().forEach((i)->sb.append(i));
		assertEquals("124678912",sb.toString());
		
		bst.delete(1);
		sb.delete(0, sb.length());
		bst.keys().forEach((i)->sb.append(i));
		assertEquals("24678912",sb.toString());
		
		bst.delete(9);
		sb.delete(0, sb.length());
		bst.keys().forEach((i)->sb.append(i));
		assertEquals("2467812",sb.toString());
		

		bst.delete(2);
		sb.delete(0, sb.length());
		bst.keys().forEach((i)->sb.append(i));
		assertEquals("467812",sb.toString());
		
		bst.delete(7);
		sb.delete(0, sb.length());
		bst.keys().forEach((i)->sb.append(i));
		assertEquals("46812",sb.toString());
	}

	@Test
	public void testKeys() throws Exception {
		//Covered by test=testInOrderIteration
	}

	@Test
	public void testKeysHiLo() throws Exception {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		bst.put(9, "P");//r1r2l1
		bst.put(1, "X");//l1l1
		bst.put(4, "Y");//l1r1l2
		bst.put(6, "Z");//l1r1r2
		StringBuilder sb = new StringBuilder();
		bst.keys(2, 9).forEach((i)->sb.append(i));
		assertEquals("2456789",sb.toString());
	}
	
	@Test
	public void testPrintTree() {
		BinarySearchTreeST<String, Integer> b=new BinarySearchTreeST<>();
		b.put("M", 1);
		b.put("K", 1);
		b.put("G", 1);
		b.put("U", 1);
		b.put("X", 1);
		b.put("A", 1);
		b.put("Y", 1);
		b.put("T", 1);
		b.put("C", 1);
		b.printBinaryTree();
	}
	
	@Test
	public void testInOrderIteration() {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		bst.put(9, "P");//r1r2l1
		bst.put(1, "X");//l1l1
		bst.put(4, "Y");//l1r1l2
		bst.put(6, "Z");//l1r1r2
		StringBuilder sb = new StringBuilder();
		bst.keys().forEach((i)->sb.append(i));
		assertEquals("1245678912",sb.toString());
	}
	
	@Test
	public void testFindMin() {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		bst.put(9, "P");//r1r2l1
		bst.put(1, "X");//l1l1
		bst.put(4, "Y");//l1r1l2
		bst.put(6, "Z");//l1r1r2
		assertEquals(1, bst.findMin().k.intValue());
		assertEquals("X", bst.findMin().v);
	}
	
	@Test
	public void testFindMax() {
		BinarySearchTreeST<Integer, String> bst=new BinarySearchTreeST<>();
		bst.put(7, "H");//root
		bst.put(2, "E");//l1
		bst.put(8, "L");//r1
		bst.put(5, "A");//l1r1
		bst.put(12, "O");//r1r2
		bst.put(9, "P");//r1r2l1
		bst.put(1, "X");//l1l1
		bst.put(4, "Y");//l1r1l2
		bst.put(6, "Z");//l1r1r2
		assertEquals(12, bst.findMax().k.intValue());
		assertEquals("O", bst.findMax().v);
	}
}
