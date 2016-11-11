package art.of.programming.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algs4cs.sorting.SortUtils;
/**
 * Execute test class ShellSortCrudeTest to test this program
 * 
 * Execute:
 * mvn test -Dtest=ShellSortTest
 * 
 * @param <T>
 */
public class ShellSortCrude<T extends Comparable<T>> {

	Logger l = LoggerFactory.getLogger(getClass());
	T[] t;
	int hS[] = new int[]{8, 4, 2, 1};
	public ShellSortCrude(T[] t) {
		this.t = t;
	}
	
	public void sort() {
		SortUtils.StatsCollector statsCollector = SortUtils.newStatsCollector();
		l.info("To Sort {}", Arrays.toString(t));
		for(int hIndex=0; hIndex<hS.length; hIndex++) {
			int h = hS[hIndex];
			for(int i = h; i<t.length; i++) {
				for(int j = i; j>=h; j-=h) {
					l.info("h:{}, j:{}, j+h:{}}", h, j, j-h);
					if(SortUtils.less(t[j], t[j-h], statsCollector)) {
						SortUtils.exchange(t, j, j-h, statsCollector);
					}
				}
			}
			l.info("After h:{}-sorting {}", h, Arrays.toString(t));
			statsCollector.printStats();
		}
		
		l.info("Fina stats\n------------");
		statsCollector.printStats();
	}
	
}
