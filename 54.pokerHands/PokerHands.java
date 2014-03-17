/*
	In the card game poker, a hand consists of five cards and are 
	ranked, from lowest to highest, in the following way:

	High Card: Highest value card.
	One Pair: Two cards of the same value.
	Two Pairs: Two different pairs.
	Three of a Kind: Three cards of the same value.
	Straight: All cards are consecutive values.
	Flush: All cards of the same suit.
	Full House: Three of a kind and a pair.
	Four of a Kind: Four cards of the same value.
	Straight Flush: All cards are consecutive values of same suit.
	Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
	The cards are valued in the order:
	2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

	If two players have the same ranked hands then the rank made 
	up of the highest value wins; for example, a pair of eights 
	beats a pair of fives (see example 1 below). But if two ranks 
	tie, for example, both players have a pair of queens, then 
	highest cards in each hand are compared (see example 4 below); 
	if the highest cards tie then the next highest cards are 
	compared, and so on.

	Consider the following five hands dealt to two players:

	Hand	 	Player 1	 	Player 2	 	Winner
	1	 	5H 5C 6S 7S KD
	Pair of Fives
	 	2C 3S 8S 8D TD
	Pair of Eights
	 	Player 2
	2	 	5D 8C 9S JS AC
	Highest card Ace
	 	2C 5C 7D 8S QH
	Highest card Queen
	 	Player 1
	3	 	2D 9C AS AH AC
	Three Aces
	 	3D 6D 7D TD QD
	Flush with Diamonds
	 	Player 2
	4	 	4D 6S 9H QH QC
	Pair of Queens
	Highest card Nine
	 	3D 6D 7H QD QS
	Pair of Queens
	Highest card Seven
	 	Player 1
	5	 	2H 2D 4C 4D 4S
	Full House
	With Three Fours
	 	3C 3D 3S 9S 9D
	Full House
	with Three Threes
	 	Player 1
	The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.

	How many hands does Player 1 win?

	----------

	This seems to involve just a lot of logic. I spent a good amount
	of time reading in the data and figuring out how to best read it
	in and use what I read in to evaluate hands. 

	I manually changed the T, J, Q, K, A to numeric representations.
	It would have been simpler to use sed to adjust the file, but I
	want to use only java here.

	Solved it. Used a bunch of logic. Was a good excercise in organizing code
	and needing testing.
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;

import java.util.StringTokenizer;

import java.lang.Math;

// import org.junit.Object;

public class PokerHands {
	/*

	*/
	private static int playHand(ArrayList<Integer> hand1Card, 
		ArrayList<String> hand1Suit, ArrayList<Integer> hand2Card,
		ArrayList<String> hand2Suit) {

		int player1Score = 0;
		int player2Score = 0;

		// Order collections for easier evaluations
		Collections.sort(hand1Card);
		Collections.sort(hand2Card);

		/*
			Determine flush
			=====================================================
		*/
		boolean flushHand1 = false;
		boolean flushHand2 = false;

		for (int i = 0; i < 4; i++) {
			flushHand1 = true;
			if (hand1Suit.get(i).equals(hand1Suit.get(i+1))) {
				continue;
			} else {
				flushHand1 = false;
				break;
			}
		}

		for (int i = 0; i < 4; i++) {
			flushHand2 = true;
			if (hand2Suit.get(i).equals(hand2Suit.get(i+1))) {
				continue;
			} else {
				flushHand2 = false;
				break;
			}
		}
		/*
			=====================================================
		*/

		/*
			Determine straight
			=====================================================
		*/
		boolean straightHand1 = false;
		boolean straightHand2 = false;

		for (int i = 0; i < 4; i++) {
			straightHand1 = true;
			if (hand1Card.get(i) +1 == hand1Card.get(i+1)) {
				continue;
			} else {
				straightHand1 = false;
				break;
			}
		}

		for (int i = 0; i < 4; i++) {
			straightHand2 = true;
			if (hand2Card.get(i) +1 == hand2Card.get(i+1)) {
				continue;
			} else {
				straightHand2 = false;
				break;
			}
		}
		/*
			=====================================================
		*/

		/*
			Determine if Royal flush
			=====================================================
		*/
		boolean royalFlushHand1 = false;
		boolean royalFlushHand2 = false;

		if (straightHand1) {
			if (hand1Card.get(0) == 10 && hand1Card.get(1) == 11 && hand1Card.get(2) == 12 && hand1Card.get(3) == 13 && hand1Card.get(4) == 14) {
				royalFlushHand1 = true;
			}
		}

		if (straightHand2) {
			if (hand2Card.get(0) == 10 && hand2Card.get(1) == 11 && hand2Card.get(2) == 12 && hand2Card.get(3) == 13 && hand2Card.get(4) == 14) {
				royalFlushHand2 = true;
			}
		}
		/*
			=====================================================
		*/

		/*	Determine if 4 of a kind
			=====================================================
		*/
		boolean fourOfKindHand1 = false;
		boolean fourOfKindHand2 = false;

		int fourOfKindScore1 = 0;
		int fourOfKindScore2 = 0;


		if (hand1Card.get(0) == hand1Card.get(3)) {
			fourOfKindHand1 = true;
			fourOfKindScore1 = hand1Card.get(0) * 4;
		} else if (hand1Card.get(1) == hand1Card.get(4)) {
			fourOfKindHand1 = true;
			fourOfKindScore1 = hand1Card.get(1) * 4;
		}

		if (hand2Card.get(0) == hand2Card.get(3)) {
			fourOfKindHand2 = true;
			fourOfKindScore2 = hand2Card.get(0) * 4;
		} else if (hand2Card.get(1) == hand2Card.get(4)) {
			fourOfKindHand2 = true;
			fourOfKindScore2 = hand2Card.get(2) * 4;
		}
		/*
			=====================================================
		*/

		/*	Determine full house
			=====================================================
		*/
		boolean fullHouseHand1 = false;
		boolean fullHouseHand2 = false;

		int fullHouseScore1 = 0;
		int fullHouseScore2 = 0;

		if (hand1Card.get(0) == hand1Card.get(2) && hand1Card.get(3) == hand1Card.get(4)) {
			fullHouseHand1 = true;
			fullHouseScore1 = hand1Card.get(0) * 1000 + hand1Card.get(4) * 10; // Spit-balled these
		}
		if (hand1Card.get(0) == hand1Card.get(1) && hand1Card.get(2) == hand1Card.get(4)) {
			fullHouseHand1 = true;
			fullHouseScore1 = hand1Card.get(4) * 1000 + hand1Card.get(0) * 10; // Spit-balled these
		}

		if (hand2Card.get(0) == hand2Card.get(2) && hand2Card.get(3) == hand2Card.get(4)) {
			fullHouseHand2 = true;
			fullHouseScore2 = hand2Card.get(0) * 1000 + hand2Card.get(4) * 10; // Spit-balled these
		}
		if (hand2Card.get(0) == hand2Card.get(1) && hand2Card.get(2) == hand2Card.get(4)) {
			fullHouseHand2 = true;
			fullHouseScore2 = hand2Card.get(4) * 1000 + hand2Card.get(0) * 10; // Spit-balled these
		}						
		/*
			=====================================================
		*/

		/*
			3 of a kind
			=====================================================
		*/
		boolean threeOfKindHand1 = false;
		boolean threeOfKindHand2 = false;

		int threeOfKindScore1 = 0;
		int threeOfKindScore2 = 0;

		if (hand1Card.get(0) == hand1Card.get(2)) {
				threeOfKindHand1 = true;
				threeOfKindScore1 = hand1Card.get(0) * 1000 + hand1Card.get(3) + hand1Card.get(4);
		} else if (hand1Card.get(1) == hand1Card.get(3)) {
			threeOfKindHand1 = true;
			threeOfKindScore1 = hand1Card.get(1) * 1000 + hand1Card.get(0) + hand1Card.get(4);
		} else if (hand1Card.get(2) == hand1Card.get(4)) {
			threeOfKindHand1 = true;
			threeOfKindScore1 = hand1Card.get(2) * 1000 + hand1Card.get(0) + hand1Card.get(1);
		}

		if (hand2Card.get(0) == hand2Card.get(2)) {
			threeOfKindHand2 = true;
			threeOfKindScore2 = hand2Card.get(0) * 1000 + hand2Card.get(3) + hand2Card.get(4);
		} else if (hand2Card.get(1) == hand2Card.get(3)) {
			threeOfKindHand2 = true;
			threeOfKindScore2 = hand2Card.get(1) * 1000 + hand2Card.get(0) + hand2Card.get(4);
		} else if (hand2Card.get(2) == hand2Card.get(4)) {
			threeOfKindHand2 = true;
			threeOfKindScore2 = hand2Card.get(2) * 1000 + hand2Card.get(0) + hand2Card.get(1);
		}
		/*
			=====================================================
		*/

		/*
			2 pair
			=====================================================
		*/
		boolean twoPairHand1 = false;
		boolean twoPairHand2 = false;

		int twoPairScore1 = 0;
		int twoPairScore2 = 0;

		if (hand1Card.get(0) == hand1Card.get(1) && hand1Card.get(2) == hand1Card.get(3)) {
			twoPairHand1 = true;
			twoPairScore1 = hand1Card.get(0) * 100 + hand1Card.get(2) * 1000 + hand1Card.get(4);
		} else if (hand1Card.get(0) == hand1Card.get(1) && hand1Card.get(3) == hand1Card.get(4)) {
			twoPairHand1 = true;
			twoPairScore1 = hand1Card.get(0) * 100 + hand1Card.get(2) + hand1Card.get(4) * 1000;			
		} else if (hand1Card.get(1) == hand1Card.get(2) && hand1Card.get(3) == hand1Card.get(4)) {
			twoPairHand1 = true;
			twoPairScore1 = hand1Card.get(0) + hand1Card.get(2)  * 100 + hand1Card.get(4) * 1000;			
		}
		if (hand2Card.get(0) == hand2Card.get(1) && hand2Card.get(2) == hand2Card.get(3)) {
			twoPairHand2 = true;
			twoPairScore2 = hand2Card.get(0) * 100 + hand2Card.get(2) * 1000 + hand2Card.get(4);
		} else if (hand2Card.get(0) == hand2Card.get(1) && hand2Card.get(3) == hand2Card.get(4)) {
			twoPairHand2 = true;
			twoPairScore2 = hand2Card.get(0) * 100 + hand2Card.get(2) + hand2Card.get(4) * 1000;			
		} else if (hand2Card.get(1) == hand2Card.get(2) && hand2Card.get(3) == hand2Card.get(4)) {
			twoPairHand2 = true;
			twoPairScore2 = hand2Card.get(0) + hand2Card.get(2)  * 100 + hand2Card.get(4) * 1000;			
		}
		/*
			=====================================================
		*/

		/*
			1 pair
			=====================================================
		*/
			boolean onePairHand1 = false;
			boolean onePairHand2 = false;

			int onePairScore1 = 0;
			int onePairScore2 = 0;

			if (hand1Card.get(0) == hand1Card.get(1)) {
				onePairHand1 = true;
				onePairScore1 = 1000 * hand1Card.get(0) + hand1Card.get(2) + hand1Card.get(3) + hand1Card.get(4);
			} else if (hand1Card.get(1) == hand1Card.get(2)) {
				onePairHand1 = true;
				onePairScore1 = 1000 * hand1Card.get(1) + hand1Card.get(0) + hand1Card.get(3) + hand1Card.get(4);
			} else if (hand1Card.get(2) == hand1Card.get(3)) {
				onePairHand1 = true;
				onePairScore1 = 1000 * hand1Card.get(2) + hand1Card.get(0) + hand1Card.get(1) + hand1Card.get(4);
			} else if (hand1Card.get(3) == hand1Card.get(4)) {
				onePairHand1 = true;
				onePairScore1 = 1000 * hand1Card.get(3) + hand1Card.get(0) + hand1Card.get(1) + hand1Card.get(2);
			}
			if (hand2Card.get(0) == hand2Card.get(1)) {
				onePairHand2 = true;
				onePairScore2 = 1000 * hand2Card.get(0) + hand2Card.get(2) + hand2Card.get(3) + hand2Card.get(4);
			} else if (hand2Card.get(1) == hand2Card.get(2)) {
				onePairHand2 = true;
				onePairScore2 = 1000 * hand2Card.get(1) + hand2Card.get(0) + hand2Card.get(3) + hand2Card.get(4);
			} else if (hand2Card.get(2) == hand2Card.get(3)) {
				onePairHand2 = true;
				onePairScore2 = 1000 * hand2Card.get(2) + hand2Card.get(0) + hand2Card.get(1) + hand2Card.get(4);
			} else if (hand2Card.get(3) == hand2Card.get(4)) {
				onePairHand2 = true;
				onePairScore2 = 1000 * hand2Card.get(3) + hand2Card.get(0) + hand2Card.get(1) + hand2Card.get(2);
			}			
		/*
			=====================================================
		*/								
	/*
		Score Evaluations
		=====================================================
	*/
		// Royal Flush
		if (royalFlushHand1) player1Score = 1;
		if (royalFlushHand2) player2Score = 1;

		// return winner if there is a score
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}

		// Straight flush
		if (straightHand1 && flushHand1) player1Score = 1;
		if (straightHand2 && flushHand2) player2Score = 1;

		// return winner if there is a score
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}

		// 4 of a kind
		if (fourOfKindHand1) player1Score = fourOfKindScore1;
		if (fourOfKindHand2) player2Score = fourOfKindScore2;
		// return winner if there is a score
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}

		// Full House
		if (fullHouseHand1) player1Score = fullHouseScore1;
		if (fullHouseHand2) player2Score = fullHouseScore2;
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}

		// Flush
		if (flushHand1) player1Score = 1;
		if (flushHand2) player2Score = 1;	
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}

		// Straight
		if (straightHand1) player1Score = 1;
		if (straightHand2) player2Score = 1;
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}

		// 3 of a kind
		if (threeOfKindHand1) player1Score = threeOfKindScore1;
		if (threeOfKindHand2) player2Score = threeOfKindScore2;
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}

		// 2 pair
		if (twoPairHand1) player1Score = twoPairScore1;
		if (twoPairHand2) player2Score = twoPairScore2;
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}		

		// 1 pair
		if (onePairHand1) player1Score = onePairScore1;
		if (onePairHand2) player2Score = onePairScore2;
		if (player1Score > 0 || player2Score > 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}

		if (player1Score == 0 && player2Score == 0) {
			return returnWinner(player1Score, player2Score, hand1Card, hand2Card);
		}				
		return Integer.MAX_VALUE;
	}

	// method to return a winner
	private static int returnWinner(int player1Score, int player2Score,
		ArrayList<Integer> hand1Card, ArrayList<Integer> hand2Card) {
		if (player1Score < player2Score) {
			return 0;
		} else if (player1Score > player2Score) {
			return 1;
		} else if (player2Score == player1Score) {
			for (int i = 4; i >= 0; i--) {
				if (hand1Card.get(i) > hand2Card.get(i)) {
					return 1;
				} else if (hand2Card.get(i) > hand1Card.get(i)) {
					return 0;
				}
			}			
		}
		return Integer.MAX_VALUE;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// Read in the file by line
		String input	 = "poker.txt";
		String delimiter = "\n";
		Scanner	sc 		 = new Scanner(new File(input)).useDelimiter(delimiter);
		// Using 2 lists per player, one for suit, one for card value
		ArrayList<Integer> hand1Card = new ArrayList<Integer>();
		ArrayList<String> hand1Suit = new ArrayList<String>();
		ArrayList<Integer> hand2Card = new ArrayList<Integer>();
		ArrayList<String> hand2Suit = new ArrayList<String>();
		// Used to divey up the input tokens to respective hands
		int counter 	 = 0;


		int player1Wins = 0;
		int player2Wins = 0;

		// Loop through each line
		while(sc.hasNext()) {
			String nextLine 	= sc.next();
			// Break each line into Tokens
			StringTokenizer st  = new StringTokenizer(nextLine);
			// Loop through the tokens dividing them up between
			// the respective lists. Replace higher cards with
			// numerica values
			while (st.hasMoreTokens()) {
				String nextCard = st.nextToken();
				if (counter < 5) {
					String card = nextCard.substring(0,1);
					if (card.equals("T")) card = "10";
					else if(card.equals("J")) card = "11";
					else if(card.equals("Q")) card = "12";
					else if(card.equals("K")) card = "13";
					else if(card.equals("A")) card = "14";
					hand1Card.add(Integer.parseInt(card));
					hand1Suit.add(nextCard.substring(1));
					counter++;
				} else if (counter > 4) {
					String card = nextCard.substring(0,1);
					if (card.equals("T")) card = "10";
					else if(card.equals("J")) card = "11";
					else if(card.equals("Q")) card = "12";
					else if(card.equals("K")) card = "13";
					else if(card.equals("A")) card = "14";
					hand2Card.add(Integer.parseInt(card));
					hand2Suit.add(nextCard.substring(1));
					counter++;
				}
			}
			int winner = (playHand(hand1Card, hand1Suit, hand2Card, hand2Suit));
			if (winner == 1) {
				player1Wins++;
			} else if (winner == 0) {
				player2Wins++;
			}
			
			// Reset variables for next iteration
			hand1Suit.clear();
			hand2Suit.clear();
			hand1Card.clear();
			hand2Card.clear();			
			counter = 0;
		}
		System.out.println("Player 1 wins: " + player1Wins + " Player 2 wins " + player2Wins);

	}
}