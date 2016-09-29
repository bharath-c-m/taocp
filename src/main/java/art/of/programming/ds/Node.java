package art.of.programming.ds;

public class Node {

	public int val;
	public Node next;
	
	public String toString() {
		if(next == null)
			return val+"-EOF";
		else
			return val+ "-"+next.toString();
	}
}
