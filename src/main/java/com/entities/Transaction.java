package com.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Comparable<Transaction>{

	String who; Date when; double amount; String txLine;
	final SimpleDateFormat sdf = new SimpleDateFormat("m/dd/yyyy"); 
	public Transaction(String stmtLine) {
		
		String[] s = stmtLine.split(" ");
		int i = 0;
		if(s.length<3) 
			throw new RuntimeException("Invalid tx line");
		who = s[i++];
		try {
			while(s[i].isEmpty()) i++;
			when = sdf.parse(s[i++]);
		} catch (ParseException e) {
			throw new RuntimeException("Invalid tx line -- bad date");
		}
		while(s[i].isEmpty()) i++;
		amount = Double.parseDouble(s[i]);
		txLine = stmtLine;
//		System.out.println(this);
	}
	
	
	@Override
	public int compareTo(Transaction that) {
		if(this.amount<that.amount)
			return -1;
		else if(this.amount>that.amount)
			return 1;
		else
			return 0;
	}
	
	public String toString() {
		return this.who+"  "+this.when+"  "+this.amount;
	}
	
	
}
