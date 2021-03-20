/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    private final int MIN = 1, MAXCARDS = 7;
    
    // Array for the cards
    private Card[] cards = new Card[MAXCARDS];
    
    /**
     * Constructor for objects of class Board
     */
    public Board() {
        this.fillBoard();
    }
    
    public void fillBoard() {
        for (int i = 0; i < MAXCARDS; i++) {
            cards[i] = new Card("Card" + Integer.toString(i + 1));
        }
    }
    
    public void randomAttack(int idx, Card[] defenders) {
        int random;
        do {
            random = (int) (Math.random() * MAXCARDS);
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
        return (int) (MIN + Math.random() * MAXCARDS);
    }
    
    public final int getMax() {
        return MAXCARDS;
    }
}