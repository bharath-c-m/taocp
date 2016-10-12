package art.of.programming.ds.applications;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArithExprEvaluatorTest {

	@Test
	public void testEvaluate() throws Exception {
		ArithExprEvaluator a = new ArithExprEvaluator();
		assertEquals(12, a.evaluate("( 10 + 2 )"));
		assertEquals(20, a.evaluate("( ( 10 * 3 ) - ( 5 + 5 ) )"));
		assertEquals(16, a.evaluate("( ( 10 + 2 ) + 4 )"));
		assertEquals(36, a.evaluate("( ( 10 + 2 ) * 3 )"));


	}

}
