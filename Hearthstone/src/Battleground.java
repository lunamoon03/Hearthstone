import java.util.Scanner;
/**
 * Driver class for Hearthstone
 *
 * @author Luna
 * @version 19/03/2021
 */
public class Battleground
{
	Board topBoard;
	Board lowerBoard;
	private int first;
    Scanner input = new Scanner(System.in);
    private int intInput = 0;
    private boolean askAgain = true;
    
    /**
     * Constructor for objects of class Battleground
     */
    public Battleground() {
    }
    
    public int[] askCardAmt() {
    	int[] cardAmts = new int[2];
    	// Get num for top board
        while (askAgain) {
        	System.out.println("How many cards does the top board have? ");
        	if (input.hasNextInt()) {
        		intInput = input.nextInt();
        		askAgain = false;
        	}
        	else {
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
        		askAgain = false;
        	}
        	else {
        		System.out.println("Please enter a number");
        	}
        }
        cardAmts[1] = intInput;
        return cardAmts;
    }
    
    /**
     * Fills the battleground with two Boards (top and bottom) full of Cards
     */
    public void fillBoards(int[] cardAmts) {
    	// Initialize top board
        topBoard = new Board("Top", cardAmts[0]);
        
        // Initialize lower board
        lowerBoard = new Board("Lower", cardAmts[1]);
    }
    
    /**
     * Repeats the battle for a number of rounds
     * @param rounds how many times the game repeats
     */
    public int[] playGame(int[] cardAmts) {
    	this.fillBoards(cardAmts);
    	int[] results = {0, 0, 0};
		do { // run game while both boards have cards on them
            first = (int) (Math.random());
            
            if (first == 0) { // top board goes first
            	// cycle through, starting on 1. odds is first evens are second.
                for (int j = 0; j < topBoard.getLen(); j++) {
                	// Check that the card is alive
                    if (topBoard.getDeck()[j].isAlive()) {
                        topBoard.randomAttack(j, lowerBoard);
                    }
                    if (lowerBoard.getDeck()[j].isAlive()) {
                        lowerBoard.randomAttack(j, topBoard);
                    }
                }
            }
            else if (first == 1) { // bottom board goes first
                for (int j = 0; j < topBoard.getLen(); j++) {
                	// Check that the card is alive
                    if (lowerBoard.getDeck()[j].isAlive()) {
                        lowerBoard.randomAttack(j, topBoard);
                    }
                    if (topBoard.getDeck()[j].isAlive()) {
                        topBoard.randomAttack(j, lowerBoard);
                    }
                }
            }
        } while ((topBoard.checkDeck() > 0) && (lowerBoard.checkDeck() > 0));
		
    	if ((topBoard.checkDeck() == 0) && (lowerBoard.checkDeck() == 0)) {
    		results[0]++;
        }
        else if (topBoard.checkDeck() == 0) {
        	System.out.println("\nYou won!");
        	results[1]++;
        }
        else if (lowerBoard.checkDeck() == 0) {
        	System.out.println("\nThe opponent won!");
        	results[2]++;
        }
    	return results;
    }

    /**
     * Driver for basic game
     */
    public static void main(String[] args) {
        Battleground battleground = new Battleground();
        int[] cardAmts = {7, 7};
        int[] results = battleground.playGame(cardAmts);
        
        if (results[0] == 0) {
        	System.out.println("It's a draw!");
        }
        else if (results[0] == 1) {
        	System.out.println("\nYou won!");
        }
        else if (results[0] == 2) {
        	System.out.println("\nThe opponent won!");
        }
    }
}