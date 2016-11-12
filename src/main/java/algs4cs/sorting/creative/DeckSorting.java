package algs4cs.sorting.creative;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.introcs.StdRandom;

public class DeckSorting {

	static Logger l = LoggerFactory.getLogger(DeckSorting.class);
	public static void main(String[] args) {
		//Creating a deck of just 8 cards for testing
		Deck<Card> deckOfCards = new Deck<>();
		deckOfCards.addToDeck(new Card(Rank.ACE, Suit.DIMOND));
		deckOfCards.addToDeck(new Card(Rank.TWO, Suit.SPADE));
		deckOfCards.addToDeck(new Card(Rank.THREE, Suit.HEART));
		deckOfCards.addToDeck(new Card(Rank.KING, Suit.CLUB));
		deckOfCards.addToDeck(new Card(Rank.QUEEN, Suit.DIMOND));
		deckOfCards.addToDeck(new Card(Rank.FIVE, Suit.SPADE));
		deckOfCards.addToDeck(new Card(Rank.TEN, Suit.HEART));
		deckOfCards.addToDeck(new Card(Rank.JACK, Suit.DIMOND));
		
		l.info("Deck before sorting {}", deckOfCards);
		deckOfCards.sort();
		l.info("Sorting complete");
		l.info("Deck after sorting {}", deckOfCards);

	}
}

class Deck<T extends Comparable<T>> {
	List<T> items;
	
	public void shuffle() {
		StdRandom.shuffle(items.toArray());
	}
	
	public boolean testSortOrder() {
		boolean testPassed = true;
		T p = null;
		for(T t : this.items) {
			if(p == null) {
				p = t;
				continue;
			} else {
				if(p.compareTo(t) > 0) {
					DeckSorting.l.info("Test failed");
					testPassed = false;
					break;
				}
			}
		}
		return testPassed;
	}
	
	public void addToDeck(T t) {
		if(items == null) {
			items = new ArrayList<>();
		}
		
		items.add(t);
	}
	
	public void sort() {
		//Since the cards are faced down, we will do simple selection sort
		for(int i=0; i<items.size()-1; i++) {
			int small = i;
			for(int j = i+1; j<items.size(); j++) {
				if(items.get(small).compareTo(items.get(j))>0) {
					small = j;
				}
			}
			if(i != small) {
				T temp = items.get(small);
				items.set(small, items.get(i));
				items.set(i, temp);
			}
		}
		
	}
	
	public String toString() {
		return "\n{"+items.toString()+"}";
	}
}

class Card implements Comparable<Card>{

	Rank r;
	Suit s;
	
	public Card(Rank r, Suit s) {
		this.r = r;
		this.s = s;
	}
	
	
	@Override
	public int compareTo(Card o) {
		if(this.s.val == o.s.val) {
			return this.r.val-o.r.val;
		} else {
			return this.s.val-o.s.val;
		}
	}
	
	public String toString() {
		return "("+r.toString()+"-"+s.toString()+")\n";
	}
}

enum Rank {
	ACE(14), KING(13), QUEEN(12), JACK(11), TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2);
	int val;
	Rank(int val) {
		this.val = val;
	}
	
	public String toString() {
		return name();
	}
}

enum Suit {
	SPADE(4), HEART(3), CLUB(2), DIMOND(1);
	
	int val;
	Suit(int val) {
		this.val = val;
	}
	
	public String toString() {
		return name();
	}
}