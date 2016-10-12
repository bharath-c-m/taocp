package art.of.programming.ds.applications;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

import art.of.programming.ds.generics.StackLinkedAllocation;

public class ReversePolishConverter {

	// A max size of 100 is assumed
	StackLinkedAllocation<Character> operatorStack = new StackLinkedAllocation<>(100);
	StackLinkedAllocation<Integer> operandStack = new StackLinkedAllocation<>(100);

	Predicate<String> nullExpr = String::isEmpty;
	Predicate<String> openParen = (s) -> s.length() == 1 && s.charAt(0) == '(';
	Predicate<String> closedParen = (s) -> s.length() == 1 && s.charAt(0) == ')';
	Predicate<String> oper = (s) -> s.length() == 1 && (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/');

	BiConsumer<String, StringBuilder> append = (s, sb) -> sb.append(s);

	public String inFixToPostFix(String infixExpr) {
		StringBuilder sb = new StringBuilder();
		if (nullExpr.test(infixExpr)) {
			throw new RuntimeException("Invalid expression -- cannot be null");
		}
		String tokens[] = infixExpr.split(" ");
		for (String t : tokens) {
			if(openParen.test(t)) {
				sb.append(t);
				append.accept(" ",sb);
			} else if(oper.test(t)){
				operatorStack.push(t.charAt(0));
			} else if(closedParen.test(t)) {
				if(!operandStack.isEmpty()) {
					append.accept(operandStack.pop()+" ", sb);
				}
				if (!operandStack.isEmpty()) {
					append.accept(operandStack.pop()+" ", sb);
				}
				if(!operandStack.isEmpty())
					append.accept(operatorStack.pop()+" ", sb);
				append.accept(operatorStack.pop()+" ) ", sb);
			} else {
				try {
					int operand = Integer.parseInt(t);
					operandStack.push(operand);
				} catch(NumberFormatException e) {
					throw new RuntimeException("Invalid expression -- bad token "+t);
				}
			}
		}
		return sb.toString().trim();
	}
	
}
