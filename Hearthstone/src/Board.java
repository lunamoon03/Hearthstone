/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    private final int MIN = 1, MAX_CARDS = 7;
    
    // Array for the cards
    private Card[] cards = new Card[MAX_CARDS];
    
    /**
     * Constructor for objects of class Board
     */
    public Board(String boardName) {
        this.fillBoard(boardName);
    }
    
    public void fillBoard(String boardName) {
        for (int i = 0; i < MAX_CARDS; i++) {
            cards[i] = new Card(boardName + " Card " + Integer.toString(i + 1));
        }
    }
    
    public void displayBoard() {
    	for (Card card: cards) {
    		System.out.printf("%s. Attack: %s. Defence: %s\n", card.getName(),
    				card.getAttack(), card.getDefence());
    	}
    }
    
    public void randomAttack(int idx, Card[] defenders) {
        int random;
        do {
            random = (int) (Math.random() * MAX_CARDS);
        } while (!defenders[random].isAlive());
        defenders[random].getHit(cards[idx].getAttack());
        cards[idx].getHit(defenders[random].getAttack());
        System.out.printf("\n%s attacks %s!", cards[idx].getName(), defenders[random].getName());
        if (defenders[random].getDefence() <= 0) {
            System.out.printf("\n%s is dead :(", defenders[random].getName());
        }
        if (cards[idx].getDefence() <= 0) {
            System.out.printf("\n%s is dead :(", cards[idx].getName());
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
    
    public int getRandom() {
        return (int) (MIN + Math.random() * MAX_CARDS);
    }
    
    public final int getMax() {
        return MAX_CARDS;
    }
}