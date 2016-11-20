package algs4cs.sorting.crude;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entities.Transaction;

import edu.princeton.cs.introcs.StdIn;

/**
 * Execute the program -
 *  mvn exec:java -Dexec.mainClass=algs4cs.sorting.crude.CrudeMaxPriorityQueueClinet -Dexec.arguements=<data/tinyBatch.txt
 * 
 *  contents of tineBatch.txt
 *  =========================
Turing      6/17/1990   644.08
vonNeumann  3/26/2002  4121.85
Dijkstra    8/22/2007  2678.40
vonNeumann  1/11/1999  4409.74
Dijkstra   11/18/1995   837.42
Hoare       5/10/1993  3229.27
vonNeumann  2/12/1994  4732.35
Hoare       8/18/1992  4381.21
Turing      1/11/2002    66.10
Thompson    2/27/2000  4747.08
Turing      2/11/1991  2156.86
Hoare       8/12/2003  1025.70
vonNeumann 10/13/1993  2520.97
Dijkstra    9/10/2000   708.95
Turing     10/12/1993  3532.36
Hoare       2/10/2005  4050.20
 */
public class CrudeMaxPriorityQueueClinet {

	static Logger l = LoggerFactory.getLogger(CrudeMaxPriorityQueueClinet.class);
	public static void main(String[] args) {
		
		//This program sorts the transactions in descending order and prints the least 10 transactions
		int max = 10;
		CrudeMaxPriorityQueue<Transaction> c = new CrudeMaxPriorityQueue<>(20);
		while (StdIn.hasNextLine()) {
			c.insert(new Transaction(StdIn.readLine()));
			if (c.size() > max) {
				Transaction tx = c.delMax();
				l.info("Removed tx: {}", tx);
			}
		}

		l.info("top 10 is");
		while (max-- > 0) {
			l.info("{}",c.delMax());
		}
	}
}
