import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BingoGame {
	private int[][] card = new int[5][5];
	private int cardSize = 25;
	private Random random = new Random();
	private int numRange = 50;

	// populate bingo card with 15 random number no duplicate
	public BingoGame() {
		// populate an hashset of 25 random unique numbers
		Set<Integer> possValues = new HashSet<Integer>();
		while (possValues.size() <= cardSize) {
			possValues.add(random.nextInt(numRange) + 1);
		}

		/*
		 * iterate through each row & each column and assign the first value from
		 * hashset remove the first value from hashset after each assignment
		 */
		for (int r = 0; r < card.length; r++) {
			for (int c = 0; c < card[r].length; c++) {
				card[r][c] = possValues.iterator().next();
				possValues.remove(possValues.iterator().next());
			}
		}
		// middle of the card is free/given
		card[2][2] = 0;
		printCard(card);
		continueGame();

	}

	void printCard(int[][] card) {
		System.out.println("B\tI\tN\tG\tO\n");
		for (int r = 0; r < card.length; r++) {
			for (int c = 0; c < card[r].length; c++) {
				if (card[r][c] > 0) {
					System.out.print(card[r][c] + "\t");
				} else {
					System.out.print("X\t");
				}
			}
			System.out.println("\n");

		}

	}

	public boolean isWon() {
		int sumAcross = 0;
		int sumDown = 0;
		int sumDiagLeft = 0;
		int sumDiagRight = 0;
		boolean hasWon = false;

		// sum row and column
		for (int row = 0; row < card.length; row++) {
			for (int column = 0; column < card[row].length; column++) {
				sumAcross += card[row][column];
				sumDown += card[column][row];
			}
			
			if (sumAcross == 0 || sumDown == 0) {
				hasWon = true;
				break;
			}
			
			sumAcross = 0;
			sumDown = 0;
		}

		return hasWon;
	}

	public void continueGame() {
		boolean win = isWon();
		int sumAcross=0;
		int sumDown=0;

		while (!win) {			
			int nextNumber = generateNumber();
			updateCard(card, nextNumber);
			printCard(card);
			
			win = isWon();
		}

		if (win) {
			System.out.println("Bingo");
			System.exit(0);
		}

	}

	public int[][] updateCard(int[][] card, int nextNumber) {
		for (int row = 0; row < card.length; row++) {
			for (int column = 0; column < card[row].length; column++) {
				if (card[row][column] == nextNumber) {
					card[row][column] = 0;
				}
			}
		}
		return card;

	}

	private int generateNumber() {
		int nextNumber = random.nextInt(numRange);
		System.out.println("Next number is " + nextNumber);
		return nextNumber;
	}

	public static void main(String[] args) {
		/*
		 * print card check win if not win, generate a new random number update card
		 * with new random number if match update card if match
		 */
		BingoGame game = new BingoGame();

	}
}
