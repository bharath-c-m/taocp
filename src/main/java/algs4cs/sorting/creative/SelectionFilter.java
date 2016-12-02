package algs4cs.sorting.creative;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.sorting.priorityq.TwoWayHeapMinPriorityQueueComparator;
import edu.princeton.cs.algorithms.Vector;
import edu.princeton.cs.introcs.StdRandom;

public class SelectionFilter {

	static Logger l = LoggerFactory.getLogger(SelectionFilter.class);
	static final Vector ORIGIN=new Vector(0, 0, 0);
	public static void main(String[] args) {
		TwoWayHeapMinPriorityQueueComparator<Vector> pq = new TwoWayHeapMinPriorityQueueComparator<>(16, EUCLIDEAN_DISTANCE_COMPARATORE);
		int topM=10;
		pq.insert(ORIGIN);//Test this should be the first to be removed
		pq.insert(new Vector(11.0, 11.0, 11.0));//Test this should not be removed -- and will be part of the top 10
		for(int i=0; i<20; i++) {
			Vector v = new Vector(StdRandom.uniform(10), StdRandom.uniform(10), StdRandom.uniform(10));
			pq.insert(v);
			if(i>topM) {
				Vector removed = pq.delMin();
				l.info("reomoved :{}",removed);
			}
		}
		
		l.info("Printing points top {} points farther from origin", topM);
		while(!pq.isSmpty()) {
			l.info("{}",pq.delMin());
		}
	}
	
	static final Comparator<Vector>EUCLIDEAN_DISTANCE_COMPARATORE=new Comparator<Vector>() {

		@Override
		public int compare(Vector p, Vector q) {
			double pDistance=p.distanceTo(ORIGIN);
			double qDistance=q.distanceTo(ORIGIN);
			return (int)(pDistance-qDistance);//Accepting the risk of over/under flow
		}
		
	}; 
	
}
