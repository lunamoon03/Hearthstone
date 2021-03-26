/**
* Stores Cards in a Card[], deals with attacks
*
* @author (your name)
* @version (a version number or a date)
*/
public class Board
{
    private final int MIN = 1;
    final String[] PROPERTIES = {"none", "none", "none",
        "none", "none", "cleave", /*"taunt", "death rattle"*/};

        // Array for the cards
        private Card[] cards;

    /**
    * Constructor for objects of class Board
    * 
    * @param boardName name of the board
    * @param cardAmt amount of cards
    * @param randomCards whether there are random cards or not
    */
    public Board(String boardName, int cardAmt, boolean randomCards) {
        cards = new Card[cardAmt];
        if (randomCards)
            this.fillBoard(boardName, cardAmt);
    }

    /**
    * Fills the board with cards
    * @param boardName name of the board
    * @param cardsAmt amount of cards on the board
    */
    public void fillBoard(String boardName, int cardsAmt) {
        final int LOCALMAX = 10;
        final int LOCALMIN = 5;
        final int LUCKDIV = 2;
        int at;
        int def;
        float luckMult;
        String prpty;
        int prptyNum;
        
        // Iterate through each card and create it
        for (int i = 0; i < cardsAmt; i++) {
            // Get stats
            at = (int) (LOCALMIN + Math.random() * LOCALMAX);
            def = (int) (LOCALMIN + Math.random() * LOCALMAX);
            
            // Luck multiplier (0.5 to 1?)
            luckMult = (float) (MIN + Math.random() / LUCKDIV);
            at *= luckMult; def *= luckMult;
            
            // Luck adding (-5 to +15)
            at += getStatAdd(LOCALMAX); 
            do {
                def += getStatAdd(LOCALMAX);
            } while (def <= 0);
            
            // Get the property randomly
            prptyNum = (int) (Math.random() * PROPERTIES.length);
            prpty = PROPERTIES[prptyNum];
            
            
            cards[i] = new Card((boardName + " Card " +
                Integer.toString(i + 1)), at, def, prpty);
        }
    }
    
    /**
    * Determines the luck of the card
    * @param MAX maximum amount
    * @return total returns a number to be added to the atck and def
     */
    public int getStatAdd(final int MAX) {
        int total;
        int add;
        int minus;
        
        add = (int) (Math.random() * MAX);
        minus = (int) (Math.random() * (MAX / 1.5));
            
        total = add - minus;
        
        return total;
    }
    
    /**
     * Prints out all the cards on the board
     */
    public void displayBoard() {
        for (Card card : cards)
            System.out.printf("[%s : %s : %s] ", card.getAttack(),
                    card.getProperty(), card.getDefence());
    }
    
    /**
     * Randomly attacks a Card of the enemy
     * @param idx index of cards[]
     * @param defenders defenders Board object
     * @param showDialogue whether you want to show dialogue
     */
    public void randomAttack(int idx, Board defenders, boolean showDialogue) {
        int random;
        /* 
         * Get random number if the other deck is still in play 
         * and the card is alive
         */
        do {
            random = (int) (Math.random() * defenders.getDeck().length);
        } while ((!defenders.getCard(random).isAlive())
            && (defenders.checkDeck() != 0));
        
        // Check that the defender deck still exists
        if (defenders.checkDeck() != 0) {
            // Do the attack
            defenders.getCard(random).getHit(cards[idx].getAttack());
            cards[idx].getHit(defenders.getCard(random).getAttack());
            
            // Check if dialogue is wanted
            if (showDialogue) {
                System.out.printf("\n%s attacks %s!", cards[idx].getName(),
                    defenders.getCard(random).getName());
                        
                // Check if each card is dead
                if (!defenders.getCard(random).isAlive())
                    System.out.printf("\n%s is dead :(",
                        defenders.getCard(random).getName());
    
                if (!cards[idx].isAlive())
                    System.out.printf("\n%s is dead :(", cards[idx].getName());
            }
                
                // Check for cleave and apply
            if (cards[idx].hasCleave())
                this.cleaveAttack(idx, random, defenders, showDialogue);
        }
    }
        
    /**
     * Applys cleave attack to cards
     * 
     * @param atIdx index of attacker
     * @param defIdx index of defender
     * @param defenders Board obj of defenders
     * @param showDialogue whether to show dialogue
     */
    public void cleaveAttack(int atIdx, int defIdx,
        Board defenders, boolean showDialogue) {
        /* Check that the idx isnt out of range
         * & apply half damage to cleaved enemies
         */
        if ((defIdx - 1) >= 0) 
            // Check the Card is alive and apply half normal damage
            if (defenders.getCard((defIdx - 1)).isAlive()) {
                defenders.getCard(defIdx - 1)
                    .getHit((cards[atIdx].getAttack()) / 2);
                // Show dialogue if needed
                if (showDialogue) {
                    System.out.printf("\n%s cleaves %s!",
                        cards[atIdx].getName(),
                        defenders.getCard(defIdx - 1).getName());
                    // Check if the card died from it 
                    // (only defender gets damaged by cleave)
                    if (!defenders.getCard(defIdx - 1).isAlive())
                        System.out.printf("\n%s is dead :(",
                                defenders.getCard(defIdx - 1).getName());
                }
            }
        
        /* Check that the idx isnt out of range
         * & apply half damage to cleaved enemies
         */
        if ((defIdx + 1) < defenders.getLen()) {
            // Check the Card is alive and apply half normal damage
            if (defenders.getCard(defIdx + 1).isAlive()) {
                defenders.getCard(defIdx + 1)
                    .getHit((cards[atIdx].getAttack()) / 2);
                // Show dialogue if needed
                if (showDialogue) {
                    System.out.printf("\n%s cleaves %s!",
                        cards[atIdx].getName(),
                        defenders.getCard(defIdx + 1).getName());
                    // Check if the card died from it
                    // (only defender gets damaged by cleave)
                    if (!defenders.getCard(defIdx + 1).isAlive())
                        System.out.printf("\n%s is dead :(",
                                defenders.getCard(defIdx + 1).getName());
                }
            }
        }
    }
    
    /**
     * Check how many cards in the deck are alive
     * @return int totalAlive
     */
    public int checkDeck() {
        int totalAlive = 0;
        
        for (Card card : cards) {
            if (card.isAlive()) {
                totalAlive++;
            }
        }
        
        return totalAlive;
    }
    
    /**
     * Make a copy of the Board
     * @return Board b
     */
    public Board makeCopy() {
        Board b = new Board("Copy", cards.length, false);
        for (int i = 0; i < cards.length; i++) 
                b.getDeck()[i] = cards[i].makeCopy();
        
        return b;
    }
    
    /**
     * Get the Card array
     * @return Card[] cards
     */
    public Card[] getDeck() {
        return cards;
    }
    
    /**
     * Get a specific card from the array
     * @param idx index of card
     * @return Card[idx]
     */
    public Card getCard(int idx) {
        return cards[idx];
    }
    
    /**
     * Gets the length of the cards array
     * @return int Card[] cards.length
     */
    public final int getLen() {
        return cards.length;
    }
}