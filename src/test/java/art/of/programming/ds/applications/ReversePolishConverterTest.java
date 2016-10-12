package art.of.programming.ds.applications;

import static org.junit.Assert.*;
import org.junit.Test;

public class ReversePolishConverterTest {

	@Test
	public void testInFixToPostFix() throws Exception {
		ReversePolishConverter p = new ReversePolishConverter();
		//The operands when occur in pairs like 10 + 5 or 20 + 4 the postfix notation is reversed, because the stack reverses the order when read using pop.
		// This can easily be fixed by adding some special cases
		assertEquals("( 5 10 + )", p.inFixToPostFix("( 10 + 5 )"));
		assertEquals("( ( 5 10 + ) 20 * )", p.inFixToPostFix("( ( 10 + 5 ) * 20 )"));
		assertEquals("( ( ( 5 10 + ) 100 + ) ( 4 20 + ) * )", p.inFixToPostFix("( ( ( 10 + 5 ) + 100 ) * ( 20 + 4 ) )"));
	}
}
