import java.util.Scanner;
/**
 * Driver class for Hearthstone
 *
 * @author Luna
 * @version 19/03/2021
 */
public class Battleground
{
    public static void main(String[] args) {
        Board topBoard; // = new Board("Top", 7);
        Board lowerBoard; // = new Board("Lower", 7);
        int first;
        Scanner input = new Scanner(System.in);
        String stringInput;
        int intInput = 0;
        boolean askAgain = true;
        
        // Initialize top board
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
        topBoard = new Board("Top", intInput);
        
        // Initialize lower board
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
        lowerBoard = new Board("Lower", intInput);
        // Show both boards
        System.out.println("Top board:");
        topBoard.displayBoard();
        
        System.out.println("\nLower board:");
        lowerBoard.displayBoard();
        
	    do {
	       	System.out.println("Press enter to continue");
	       	input.nextLine();
	        stringInput = input.nextLine();
	    } while (!stringInput.equals(""));
        input.close();
        
        do { // run game while both boards have cards on them
            first = (int) (Math.random());
            
            if (first == 0) { // top board goes first
            	// cycle through, starting on 1. odds is first evens are second.
                for (int i = 0; i < topBoard.getLen(); i++) {
                	// Check that the card is alive
                    if (topBoard.getDeck()[i].isAlive()) {
                        topBoard.randomAttack(i, lowerBoard);
                    }
                    if (lowerBoard.getDeck()[i].isAlive()) {
                        lowerBoard.randomAttack(i, topBoard);
                    }
                }
            }
            else if (first == 1) { // bottom board goes first
                for (int i = 0; i < topBoard.getLen(); i++) {
                	// Check that the card is alive
                    if (lowerBoard.getDeck()[i].isAlive()) {
                        lowerBoard.randomAttack(i, topBoard);
                    }
                    if (topBoard.getDeck()[i].isAlive()) {
                        topBoard.randomAttack(i, lowerBoard);
                    }
                }
            }
        } while ((topBoard.checkDeck() > 0) && (lowerBoard.checkDeck() > 0));
        
        if ((topBoard.checkDeck() == 0) && (lowerBoard.checkDeck() == 0)) {
        	System.out.println("\nIt's a draw!");
        }
        else if (topBoard.checkDeck() == 0) {
        	System.out.println("\nYou won!");
        }
        else if (lowerBoard.checkDeck() == 0) {
        	System.out.println("\nThe opponent won!");
        }
        else {
        	System.out.println("nothing");
        }
    }
}