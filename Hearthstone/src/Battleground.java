import java.util.Scanner;
/**
 * Driver class for Hearthstone.
 *
 * @author Luna
 * @version 19/03/2021
 */

public class Battleground {
	Board topBoard;
	Board lowerBoard;
	Board[] boards = new Board[2];
	private int first;
	Scanner input = new Scanner(System.in);
	private int intInput = 0;
	private boolean askAgain = true;

	/**
	 * Constructor for objects of class Battleground
	 */
	public Battleground() {
	}

	/**
	 * Asks the user how many cards are in each board
	 * 
	 * @return int[] cardAmts [0] is the top board, [1] is the lower board
	 */
	public int[] askCardAmt() {
		int[] cardAmts = new int[2];
		final int MAX_CARDS = 7;
		// Get num for top board
		while (askAgain) {
			System.out.println("How many cards does the top board have? ");
			if (input.hasNextInt()) {
				intInput = input.nextInt();
				if (intInput <= MAX_CARDS) {
					askAgain = false;
				} else {
					System.out.println("Please enter a number between 1 and 7");
				}
			} else {
				System.out.println("Please enter a number");
			}
		}
		cardAmts[0] = intInput;

		// Get num for lower board
		askAgain = true;
		while (askAgain) {
			System.out.println("How many cards does the lower board have? ");
			if (input.hasNextInt()) {
				intInput = input.nextInt();
				if (intInput <= MAX_CARDS)
					askAgain = false;
				else
					System.out.println("Please enter a number between 1 and 7");
			} else
				System.out.println("Please enter a number");
		}
		cardAmts[1] = intInput;
		return cardAmts;
	}

	/**
	 * Fills the battleground with two Boards (top and bottom) full of Cards
	 * 
	 * @param cardAmts amount of cards
	 */
	public void fillBoardsRandom(int[] cardAmts) {
		// Initialize top board
		topBoard = new Board("Top", cardAmts[0], true);
		boards[0] = topBoard;

		// Initialize lower board
		lowerBoard = new Board("Lower", cardAmts[1], true);
		boards[1] = lowerBoard;
	}

	/**
	 * Fills the boards based on pre-defined boards
	 * 
	 * @param board1 board one
	 * @param board2 board two
	 */
	public void fillBoardsFixed(Board board1, Board board2) {
		topBoard = board1.makeCopy();
		lowerBoard = board2.makeCopy();
	}

	/**
	 * Plays the main game
	 * 
	 * @param showDialogue whether to show the dialogue
	 * 
	 * @return result (0 is draw, 1 is win, 2 is loss)
	 */
	public int playGame(boolean showDialogue) {
		do { // run game while both boards have cards on them
			first = (int) (Math.random());

			if (first == 0) { // top board goes first
				// cycle through, starting on 1. odds is first evens are second.
				for (int j = 0; j < topBoard.getLen(); j++) {
					// Check that the card is alive
					if (topBoard.getDeck()[j].isAlive()) {
						topBoard.randomAttack(j, lowerBoard, showDialogue);
					}
					if ((lowerBoard.getLen() - 1) >= j) {
						if (lowerBoard.getDeck()[j].isAlive()) {
							lowerBoard.randomAttack(j, topBoard, showDialogue);
						}
					}
				}
			} else if (first == 1) { // bottom board goes first
				for (int j = 0; j < topBoard.getLen(); j++) {
					// Check that the card is alive
					if (lowerBoard.getDeck()[j].isAlive()) {
						lowerBoard.randomAttack(j, topBoard, showDialogue);
					}
					if ((topBoard.getLen() - 1) >= j) {
						if (topBoard.getDeck()[j].isAlive()) {
							topBoard.randomAttack(j, lowerBoard, showDialogue);
						}
					}
				}
			}
		} while ((topBoard.checkDeck() > 0) && (lowerBoard.checkDeck() > 0));

		if ((topBoard.checkDeck() == 0) && (lowerBoard.checkDeck() == 0))
			return 0;
		else if (topBoard.checkDeck() == 0)
			return 1;
		else if (lowerBoard.checkDeck() == 0)
			return 2;
		return 3;
	}

	/**
	 * Gets the array of Board objs
	 * 
	 * @return boards array of Boards
	 */
	public Board[] getBoards() {
		return boards;
	}

	/**
	 * Makes a copy of the Battleground
	 * 
	 * @return b deep copy of object
	 */
	public Battleground makeCopy() {
		Battleground b = new Battleground();
		b.fillBoardsFixed(topBoard, lowerBoard);

		return b;
	}

	/**
	 * Driver for basic game
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		// Set up battleground obj
		Battleground battleground = new Battleground();
		int[] cardAmts = battleground.askCardAmt();
		battleground.fillBoardsRandom(cardAmts);

		// Set up buddy obj
		Buddy bob = new Buddy();

		// Set up scanner
		Scanner in = new Scanner(System.in);
		String stringIn;

		// Get and display the boards
		Board[] boards = battleground.getBoards();
		// [0] is top board, [1] is lower board
		boards[0].displayBoard();
		System.out.println();
		boards[1].displayBoard();
		
		// Simulate 10000 rounds of play with these boards
		bob.simulateBoard(battleground);

		// Get approval from player
		do {
			System.out.println("\nPress enter to continue");
			stringIn = in.nextLine();
		} while (!stringIn.equals(""));
		in.close();

		// Get and print result of actual game
		int result = battleground.playGame(true);

		if (result == 0)
			System.out.println("\nIt's a draw!");
		else if (result == 1)
			System.out.println("\nYou won!");
		else if (result == 2)
			System.out.println("\nThe opponent won!");
	}
}
