package art.of.programming.ds.applications;

import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import art.of.programming.ds.generics.StackLinkedAllocation;
import art.of.programming.exception.StorageEmptyException;

//This API uses two stack to evaluate an expression
//This API does not consider operator presidence
public class ArithExprEvaluator {

	Logger l = LoggerFactory.getLogger(ArithExprEvaluator.class);
	
	Predicate<String> nullExpr = String::isEmpty;
	Predicate<String> openParen = (s) -> s.length() == 1 && s.charAt(0) == '(';
	Predicate<String> closedParen = (s) -> s.length() == 1 && s.charAt(0) == ')';
	Predicate<String> oper = (s) -> s.length() == 1 && (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/');

	//For convenience  tokens(operator,operand and parenthesis) are space delimited
	//Until the time re-sizing stack is implemented this code has an upper limit of 100 stack items
	public int evaluate(String expr) {
		if(nullExpr.test(expr))
			throw new RuntimeException("Invalid expression -- expression cannot be null");
		
		StackLinkedAllocation<Integer> operandStack = new StackLinkedAllocation<>(100);
		StackLinkedAllocation<Character> operatorStack = new StackLinkedAllocation<>(100);
		
		String tokens[] = expr.split(" ");
		for(String token : tokens) {
			l.debug("Processing Token {}",token);
			if(!openParen.test(token)) {
				if(oper.test(token)) 
					operatorStack.push(token.charAt(0));
				else if(closedParen.test(token)) {
					operandStack.push(evaluate(operandStack.pop(), operatorStack.pop(), operandStack.pop()));
				} else {
					try {
					Integer i = Integer.parseInt(token);
					operandStack.push(i);
					} catch(NumberFormatException e ) {
						throw new RuntimeException("Invalid expression -- cannot contain "+token);
					}
				}
			}
			
		}
		try {
			int result = operandStack.pop(); 
			if(!operatorStack.isEmpty())
				throw new RuntimeException("Evaluation failed -- Invalid expression: "+expr);
			else
				return result;
		} catch(StorageEmptyException e) {
			throw new RuntimeException("Evaluation failed -- Invalid expression: "+expr);
		}
		//after evaluation when the both the stack are not empty it is an error

	}

	private Integer evaluate(int op1, char opr, int op2) {
		int result=0;
		switch(opr) {
		case '+':
			result = op1+op2;
			break;
		case '-':
			result = op2-op1; //Switching operand since - is not commutative
			break;
		case '*':
			result = op1*op2;
			break;
		case '/':
			result = op2/op1; //Switching operand since / is not commutative
			break;
			default:
				throw new RuntimeException("Evaluation failed -- Invalid operator "+opr);
		}
		return result;
	}
	
}
