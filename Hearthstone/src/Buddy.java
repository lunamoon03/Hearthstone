
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
    
    public Buddy() {
    }
    
    public int getWins() {
        return wins;
    }
    
    public int getLosses() {
        return losses;
    }
    
    public int getDraws() {
        return draws;
    }
    
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
    
    public void simulateBoard (Board originalOpponent, Board originalPlayer) {
    	int[] results = {0, 0, 0};
    	int first, randomCard;
    	float drawPercentage, winPercentage, lossPercentage;
    	Board opponentCopy, playerCopy;
    	Card[] opponent, player;
    	
    	for (int i = 0; i < SIM_AMT; i++) {
    		opponentCopy = originalOpponent.makeCopy();
    		opponent = opponentCopy.getDeck();
    		playerCopy = originalPlayer.makeCopy();
    		player = playerCopy.getDeck();
    		do {
	    		first = (int) (Math.random());
	    		
	    		if (first == 0) {
	    			for (int j = 0; j < opponent.length; j++) {
	    				if (opponent[j].isAlive()) {
	    					do {
	    						randomCard = (int) (Math.random() * player.length);
	    					} while ((!player[randomCard].isAlive())
	    							&& (playerCopy.checkDeck() > 0));
	    					if (playerCopy.checkDeck() > 0) {
	    						player[randomCard].getHit(opponent[j].getAttack());
	    						opponent[j].getHit(player[randomCard].getAttack());
	    					}
	    					
	                    }
	                    if (player[j].isAlive()) {
	                    	do {
	    						randomCard = (int) (Math.random() * player.length);
	    					} while ((!opponent[randomCard].isAlive())
	    							&& (opponentCopy.checkDeck() > 0));
	    					if (opponentCopy.checkDeck() > 0) {
	    						opponent[randomCard].getHit(player[j].getAttack());
	    						player[j].getHit(opponent[randomCard].getAttack());
	    					}
	                    }
	                    
	    			}
	    		} else if (first == 1) {
	    			for (int j = 0; j < player.length; j++) {
	    				if (player[j].isAlive()) {
	                    	do {
	    						randomCard = (int) (Math.random() * player.length);
	    					} while ((!opponent[randomCard].isAlive())
	    							&& (opponentCopy.checkDeck() > 0));
	    					if (opponentCopy.checkDeck() > 0) {
	    						opponent[randomCard].getHit(player[j].getAttack());
	    						player[j].getHit(opponent[randomCard].getAttack());
	    					}
	                    }
	    				if (opponent[j].isAlive()) {
	    					do {
	    						randomCard = (int) (Math.random() * player.length);
	    					} while ((!player[randomCard].isAlive())
	    							&& (playerCopy.checkDeck() > 0));
	    					if (playerCopy.checkDeck() > 0) {
	    						player[randomCard].getHit(opponent[j].getAttack());
	    						opponent[j].getHit(player[randomCard].getAttack());
	    					}
	    					
	                    }                  
	    			}
	    		}
    		} while (opponentCopy.checkDeck() > 0
    				&& playerCopy.checkDeck() > 0);
    		
    		if ((opponentCopy.checkDeck() == 0)
    				&& (playerCopy.checkDeck() == 0)) { // Draw
    			results[0]++;
    		}
    		else if (opponentCopy.checkDeck() == 0) { // Win
    			results[1]++;
    		}
    		else if (playerCopy.checkDeck() == 0) { // Loss
    			results[2]++;
    		}
    	}
    	
    	drawPercentage = (results[0] / PERCENTAGE);
    	winPercentage = (results[1] / PERCENTAGE);
    	lossPercentage = (results[2] / PERCENTAGE);
    	
    	System.out.println();
    	System.out.println("Draw chance: %" + drawPercentage);
    	System.out.println("Win chance: %" + winPercentage);
    	System.out.println("Loss Percentage: %" + lossPercentage);
    	System.out.println();
    }

    
    public static void main (String[] args) {
        Buddy bob = new Buddy();
        bob.simulateRandoms(10000);

        System.out.println("Wins: %" + (bob.getWins() / 100));
        System.out.println("Losses: %" + (bob.getLosses() / 100));
        System.out.println("Draws: %" + (bob.getDraws() / 100));
    }
}
