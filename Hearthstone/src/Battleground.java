/**
 * Driver class for Hearthstone
 *
 * @author Luna
 * @version 19/03/2021
 */
public class Battleground
{
    public static void main(String[] args) {
        Board topBoard = new Board();
        Board lowerBoard = new Board();
        int first;
        
        do { // run game while both boards have cards on them
            first = (int) (Math.random());
            
            if (first == 0) { // top board goes first
                for (int i = 0; i < topBoard.getMax(); i++) { // cycle through, starting on 1. odds is first evens are second.
                    if (topBoard.getDeck()[i].isAlive()) { // Check that the card is alive
                        topBoard.randomAttack(i, lowerBoard.getDeck());
                    }
                    if (lowerBoard.getDeck()[i].isAlive()) {
                        lowerBoard.randomAttack(i, topBoard.getDeck());
                    }
                }
            }
            else if (first == 1) { // bottom board goes first
                for (int i = 0; i < topBoard.getMax(); i++) {
                    if (lowerBoard.getDeck()[i].isAlive()) { // Check that the card is alive
                        lowerBoard.randomAttack(i, topBoard.getDeck());
                    }
                    if (topBoard.getDeck()[i].isAlive()) {
                        topBoard.randomAttack(i, lowerBoard.getDeck());
                    }
                }
            }
        } while ((topBoard.checkDeck() > 0) && (lowerBoard.checkDeck() > 0));
        
        if ((topBoard.checkDeck() == 0) && (lowerBoard.checkDeck() == 0)) {
        	System.out.println("\nIt's a draw!!");
        }
        else if (topBoard.checkDeck() == 0) {
        	System.out.println("\nYou won!");
        }
        else if (lowerBoard.checkDeck() == 0) {
        	System.out.println("\nThe opponent won!");
        }
    }
}
