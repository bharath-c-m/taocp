package art.of.programming.ds;

public class Node {

	int val;
	Node next;
	
	public String toString() {
		if(next == null)
			return val+"-EOF";
		else
			return val+ "-"+next.toString();
	}
}
