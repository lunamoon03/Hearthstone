/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    private final int MIN = 1;
    
    // Array for the cards
    private Card[] cards;
    
    /**
     * Constructor for objects of class Board
     */
    public Board(String boardName, int cardAmt) {
        this.fillBoard(boardName, cardAmt);
    }
    
    /**
     * Fills the board with cards
     * @param boardName name of the board
     * @param cardsAmt amount of cards on the board
     */
    public void fillBoard(String boardName, int cardsAmt) {
    	cards = new Card[cardsAmt];
        for (int i = 0; i < cardsAmt; i++) {
            cards[i] = new Card(boardName + " Card " + Integer.toString(i + 1));
        }
    }
    
    /**
     * Prints out all the cards on the board
     */
    public void displayBoard() {
    	for (Card card: cards) {
    		System.out.printf("%s. Attack: %s. Defence: %s\n", card.getName(),
    				card.getAttack(), card.getDefence());
    	}
    }
    
    public void randomAttack(int idx, Board defenders) {
        int random;
        do {
            random = (int) (Math.random() * defenders.getDeck().length);
        } while ((!defenders.getCard(random).isAlive())
        	&& (defenders.checkDeck() != 0));
        if (defenders.checkDeck() != 0) {
	        defenders.getCard(random).getHit(cards[idx].getAttack());
	        cards[idx].getHit(defenders.getCard(random).getAttack());
	        System.out.printf("\n%s attacks %s!", cards[idx].getName(),
	        		defenders.getCard(random).getName());
	        if (defenders.getCard(random).getDefence() <= 0) {
	            System.out.printf("\n%s is dead :(", defenders.getCard(random).getName());
	        }
	        if (cards[idx].getDefence() <= 0) {
	            System.out.printf("\n%s is dead :(", cards[idx].getName());
	        }
        }
    }
    
    public int checkDeck() {
        int totalAlive = 0;
        
        for (Card card: cards) {
            if (card.isAlive()) {
                totalAlive++;
            }
        }
        
        return totalAlive;
    }
    
    public Card[] getDeck() {
        return cards;
    }
    
    public Card getCard(int idx) {
    	return cards[idx];
    }
    
    public int getRandom() {
        return (int) (MIN + Math.random() * cards.length);
    }
    
    public final int getLen() {
        return cards.length;
    }
}