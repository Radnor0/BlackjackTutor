package backend;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import gui.Window;

public class Main {
	
	private static Window win;
	
	private static ArrayList<Card> deck, discard, dealerHand, playerHand;

	public static void main(String[] args) {
		win = new Window();
		dealerHand = new ArrayList<Card>();
		playerHand = new ArrayList<Card>();
		setupDeck();
		deal();
	}
	
	private static void setupDeck() {
		deck = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		
		for (int i = 0; i < Value.values().length - 1; i++) {
			for (Suit s : Suit.values()) {
				if (Value.values()[i] == Value.ACE)
					discard.add(new Ace(s));
				else
					discard.add(new Card(Value.values()[i], s));
			}
		}
		shuffle();
	}
	
	public static void deal() {
		win.finishedHand(false);
		discard();
		
		playerHand.add(draw());
		dealerHand.add(draw());
		playerHand.add(draw());
		dealerHand.add(draw());
		
		win.addPlayerCard(playerHand.get(0));
		win.addPlayerCard(playerHand.get(1));
		win.addDealerCard(new Card(Value.BACK, Suit.CLUBS));
		win.addDealerCard(dealerHand.get(1));
		
		win.canSplit(playerHand.get(0).getValue() == playerHand.get(1).getValue());
	}
	
	public static void checkMove(Moves wanted) {
		if (wanted == Tables.getCorrect(dealerHand.get(1), playerHand)) {
			win.finishedHand(true);
		}
		else {
			String message = "That was not the correct choice for this situation. Instead, you should " + Tables.getCorrect(dealerHand.get(1), playerHand).toString().toLowerCase();
			JOptionPane.showMessageDialog(null, message, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private static Card draw() {
		if (deck.size() <= 0)
			shuffle();
		return deck.remove(0);
	}
	
	private static void discard() {
		while (dealerHand.size() > 0)
			discard.add(dealerHand.remove(0));
		while (playerHand.size() > 0)
			discard.add(playerHand.remove(0));
		
		win.clearDealerCards();
		win.clearPlayerCards();
	}
	
	private static void shuffle() {
		ArrayList<Card> temp = new ArrayList<Card>();
		while (discard.size() > 0)
			temp.add(discard.remove((int)(Math.random() * discard.size())));
		deck = (ArrayList<Card>) temp.clone();
	}
	
	public static int getValue(Card card) {
		if (card instanceof Ace)
			return (((Ace)card).isSoft()) ? 11 : 1;
		
		switch (card.getValue()) {
		case TWO: 
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX: 
			return 6;
		case SEVEN: 
			return 7;
		case EIGHT: 
			return 8;
		case NINE: 
			return 9;
		case TEN:
		case JACK: 
		case QUEEN:
		case KING:
			return 10;
		default:
			return -1;
		}
	}
	
	public static int getTotalValue(Card... cards) {
		int total = 0;
		for (Card c : cards)
			total += getValue(c);
		return total;
	}
	
	public static int getTotalValue(ArrayList<Card> cards) {
		int total = 0;
		for (Card c : cards)
			total += getValue(c);
		return total;
	}
	
	public static boolean isSoft(Card... cards) {
		for (Card c : cards) {
			if (c instanceof Ace && ((Ace) c).isSoft())
				return true;
		}
		return false;
	}
	
	public static boolean isSoft(ArrayList<Card> cards) {
		for (Card c : cards) {
			if (c instanceof Ace && ((Ace) c).isSoft())
				return true;
		}
		return false;
	}
}
