package backend;

import java.util.ArrayList;

public class Tables {

	private static final char[][] normal = {
		//	  2	   3    4    5    6    7    8    9    10   A
			{'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h'}, //4-8
			{'h', 'd', 'd', 'd', 'd', 'h', 'h', 'h', 'h', 'h'}, //9
			{'d', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'h', 'h'}, //10
			{'d', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd'}, //11
			{'h', 'h', 's', 's', 's', 'h', 'h', 'h', 'h', 'h'},	//12
			{'s', 's', 's', 's', 's', 'h', 'h', 'h', 'h', 'h'},	//13
			{'s', 's', 's', 's', 's', 'h', 'h', 'h', 'h', 'h'},	//14
			{'s', 's', 's', 's', 's', 'h', 'h', 'h', 'h', 'h'},	//15
			{'s', 's', 's', 's', 's', 'h', 'h', 'h', 'h', 'h'},	//16
			{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's'},	//17
			{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's'}	//18+
	};
	
	private static final char[][] soft = {
			//	  2	   3    4    5    6    7    8    9    10   A
				{'h', 'h', 'h', 'd', 'd', 'h', 'h', 'h', 'h', 'h'},	//13
				{'h', 'h', 'h', 'd', 'd', 'h', 'h', 'h', 'h', 'h'},	//14
				{'h', 'h', 'd', 'd', 'd', 'h', 'h', 'h', 'h', 'h'},	//15
				{'h', 'h', 'd', 'd', 'd', 'h', 'h', 'h', 'h', 'h'},	//16
				{'h', 'd', 'd', 'd', 'd', 'h', 'h', 'h', 'h', 'h'},	//17
				{'d', 'd', 'd', 'd', 'd', 's', 's', 'h', 'h', 'h'},	//18
				{'s', 's', 's', 's', 'd', 's', 's', 's', 's', 's'},	//19
				{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's'}	//20+
		};
	
	private static final char[][] pair = {
			//	  2	   3    4    5    6    7    8    9    10   A
				{'p', 'p', 'p', 'p', 'p', 'p', 'h', 'h', 'h', 'h'},	//2,2
				{'p', 'p', 'p', 'p', 'p', 'p', 'h', 'h', 'h', 'h'},	//3,3
				{'h', 'h', 'h', 'p', 'p', 'h', 'h', 'h', 'h', 'h'},	//4,4
				{'d', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'h', 'h'},	//5,5
				{'p', 'p', 'p', 'p', 'p', 'h', 'h', 'h', 'h', 'h'},	//6,6
				{'p', 'p', 'p', 'p', 'p', 'p', 'h', 'h', 'h', 'h'},	//7,7
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},	//8,8
				{'p', 'p', 'p', 'p', 'p', 's', 'p', 'p', 's', 's'},	//9,9
				{'s', 's', 's', 's', 's', 's', 's', 's', 's', 's'},	//10,10
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}	//A,A
		};
	
	public static Moves getCorrect(Card dealer, ArrayList<Card> playerCards) {
		char move;
		if (playerCards.size() == 2 && playerCards.get(0).getValue() == playerCards.get(1).getValue())
			move = pair[getIndex(playerCards.get(0))][getIndex(dealer)];
		
		else if (Main.isSoft(playerCards))
			move = soft[getSoftIndex(Main.getTotalValue(playerCards))][getIndex(dealer)];
		
		else
			move = normal[getNormalIndex(Main.getTotalValue(playerCards))][getIndex(dealer)];
		
		if (move == 'h')
			return Moves.HIT;
		if (move == 's')
			return Moves.STAY;
		if (move == 'p')
			return Moves.SPLIT;
		if (move == 'd')
			return Moves.DOUBLE;
		
		return Moves.ERROR;
	}
	
	
	
	private static int getIndex(Card card) {
		return Main.getValue(card) - 2;
	}
	
	private static int getSoftIndex(int value) {
		if (value == 21)
			return 7;
		
		return value - 13;
	}
	
	private static int getNormalIndex(int total) {
		if (total <= 8)
			return 0;
		if (total >= 18)
			return 10;
		return total - 8;
	}
}
