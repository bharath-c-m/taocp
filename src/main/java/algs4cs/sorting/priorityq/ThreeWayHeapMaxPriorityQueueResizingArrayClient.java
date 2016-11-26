package algs4cs.sorting.priorityq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entities.Transaction;

import edu.princeton.cs.introcs.StdIn;

/**
 * Execute the program -
 *  mvn exec:java -Dexec.mainClass=algs4cs.sorting.priorityq.ThreeWayHeapMaxPriorityQueueResizingArrayClient -Dexec.arguements=<data/1000words.txt
 * 
 *  contents of 1000words.txt
 *  =========================
the
and
to
of
a
I
in
was
he
that
it
his
her
you
as
had
with
for
she
..
..
..

 */
public class ThreeWayHeapMaxPriorityQueueResizingArrayClient {

	static Logger l = LoggerFactory.getLogger(ThreeWayHeapMaxPriorityQueueResizingArrayClient.class);
	public static void main(String[] args) {
		
		//This program sorts the transactions in descending order and prints the least 10 transactions
		ThreeWayHeapMaxPriorityQueueResizingArray<String> c = new ThreeWayHeapMaxPriorityQueueResizingArray<>(200);
		while (!StdIn.isEmpty()) {
			c.insert(StdIn.readString());
		}
		l.info("Words sorted in descending order");
		while (!c.isEmpty()) {
			l.info("{}",c.delMax());
		}
	}
}
