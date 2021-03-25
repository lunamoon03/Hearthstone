/**
 * Write a description of class Buddy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Buddy
{
    private int wins;
    private int losses;
    private int draws;
    private final int SIM_AMT = 10000;
    private final int PERCENTAGE = (SIM_AMT / 100);
    private final int[] CARD_AMT = {7, 7};
    
    /**
     * Constructor for objects of class Buddy
     */
    public Buddy() {
    }
    
    /**
     * Gets the total wins
     * @return int wins
     */
    public int getWins() {
        return wins;
    }
    
    /**
     * Gets the total losses
     * @return int losses
     */
    public int getLosses() {
        return losses;
    }
    
    /**
     * Gets the total draws
     * @return int draws
     */
    public int getDraws() {
        return draws;
    }
    
    /**
     * Generates 10000 random games
     * @param rounds
     */
    public void simulateRandoms(int rounds) {
        for (int i = 0; i < rounds; i++) {
            Battleground b = new Battleground();
            b.fillBoards(CARD_AMT);
            int result = b.playGame(false);
            if (result == 0) {
                draws++;
            }
            else if (result == 1) {
                wins++;
            }
            else if (result == 2) {
                losses++;
            }
        }
    }
    
    /**
     * Takes two specific Boards and simulates them 10000
     * @param originalOpponent
     * @param originalPlayer
     */
    public void simulateBoard (Board originalOpponent, Board originalPlayer) {
    	int[] results = {0, 0, 0};
    	int first, randomCard;
    	float drawPercentage, winPercentage, lossPercentage;
    	Board opponentCopy, playerCopy;
    	Card[] opponent, player;
    	
    	// 10000 iterations
    	for (int i = 0; i < SIM_AMT; i++) {
    		// Makes copy of the Board items and the Card[] arrays
    		opponentCopy = originalOpponent.makeCopy();
    		opponent = opponentCopy.getDeck();
    		playerCopy = originalPlayer.makeCopy();
    		player = playerCopy.getDeck();
    		// While at least one Board is in play
    		do {
	    		first = (int) (Math.random());
	    		
	    		if (first == 0) {
	    			// Iterate through cards
	    			for (int j = 0; j < opponent.length; j++) {
	    				// Check card is alive
	    				if (opponent[j].isAlive()) {
	    					// Get random num for card that is alive on deck in play
	    					do {
	    						randomCard = (int) (Math.random() * player.length);
	    					} while ((!player[randomCard].isAlive())
	    							&& (playerCopy.checkDeck() > 0));
	    					
	    					// If deck in play do attack
	    					if (playerCopy.checkDeck() > 0) {
	    						player[randomCard].getHit(opponent[j].getAttack());
	    						opponent[j].getHit(player[randomCard].getAttack());
	    						// Check for and do cleave damage
	    						if (opponent[j].hasCleave())
	    							opponentCopy.cleaveAttack(j, randomCard, playerCopy, false);
	    					}
	    					
	                    }
	    				// Check that the index will not be out of range
	    				if ((player.length) > j) {
	    					// Check Card is alive
		                    if (player[j].isAlive()) {
		                    	//  Get random num for card that is alive on deck in play
		                    	do {
		    						randomCard = (int) (Math.random() * opponent.length);
		    					} while ((!opponent[randomCard].isAlive())
		    							&& (opponentCopy.checkDeck() > 0));
		                    	
		                    	// If deck in play do attack
		    					if (opponentCopy.checkDeck() > 0) {
		    						opponent[randomCard].getHit(player[j].getAttack());
		    						player[j].getHit(opponent[randomCard].getAttack());
		    						// Check for and do cleave damage
		    						if (player[j].hasCleave()) 
		    							playerCopy.cleaveAttack(j, randomCard, opponentCopy,
		    									false);
		    					}
		                    }
	    				}
	                    
	    			}
	    		} else if (first == 1) {
	    			// Iterate through cards
	    			for (int j = 0; j < player.length; j++) {
	    				// Check card is alive
	    				if (player[j].isAlive()) {
	    					// Get random num for card that is alive on deck in play
	                    	do {
	    						randomCard = (int) (Math.random() * player.length);
	    					} while ((!opponent[randomCard].isAlive())
	    							&& (opponentCopy.checkDeck() > 0));
	                    	
	                    	// If deck in play do attack
	    					if (opponentCopy.checkDeck() > 0) {
	    						opponent[randomCard].getHit(player[j].getAttack());
	    						player[j].getHit(opponent[randomCard].getAttack());
	    						// Check for and do cleave damage
	    						if (player[j].hasCleave())
	    							playerCopy.cleaveAttack(j, randomCard, opponentCopy, false);
	    					}
	                    }
	    				// Make sure index isn't out of range
	    				if ((opponent.length) > j) {
	    					// Check card is alive
		    				if (opponent[j].isAlive()) {
		    					// Get random num for card that is alive on deck in play
		    					do {
		    						randomCard = (int) (Math.random() * player.length);
		    					} while ((!player[randomCard].isAlive())
		    							&& (playerCopy.checkDeck() > 0));
		    					
		    					// If deck in play do attack
		    					if (playerCopy.checkDeck() > 0) {
		    						player[randomCard].getHit(opponent[j].getAttack());
		    						opponent[j].getHit(player[randomCard].getAttack());
		    						// Check for and do cleave damage
		    						if (opponent[j].hasCleave())
		    							opponentCopy.cleaveAttack(j, randomCard, playerCopy,
		    									false);
		    					}
		    					
		                    }
	    				}
	    			}
	    		}
    		} while (opponentCopy.checkDeck() > 0
    				&& playerCopy.checkDeck() > 0);
    		
    		// Find result and increment results counter
    		if ((opponentCopy.checkDeck() == 0)
    				&& (playerCopy.checkDeck() == 0)) // Draw
    			results[0]++;
    		else if (opponentCopy.checkDeck() == 0) // Win
    			results[1]++;
    		else if (playerCopy.checkDeck() == 0)// Loss
    			results[2]++;
    	}
    	
    	// Get the equivalent percentage for results
    	drawPercentage = (results[0] / PERCENTAGE);
    	winPercentage = (results[1] / PERCENTAGE);
    	lossPercentage = (results[2] / PERCENTAGE);
    	
    	// Display results
    	System.out.println();
    	System.out.println("Draw chance: %" + drawPercentage);
    	System.out.println("Win chance: %" + winPercentage);
    	System.out.println("Loss chance: %" + lossPercentage);
    	System.out.println();
    }

    /**
     * Driver method for class Buddy
     * @param args
     */
    public static void main (String[] args) {
        Buddy bob = new Buddy();
        bob.simulateRandoms(10000);

        System.out.println("Wins: %" + (bob.getWins() / 100));
        System.out.println("Losses: %" + (bob.getLosses() / 100));
        System.out.println("Draws: %" + (bob.getDraws() / 100));
    }
}
