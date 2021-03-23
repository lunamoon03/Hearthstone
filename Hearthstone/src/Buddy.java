
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
    final int[] CARD_AMT = {7, 7};
    final int SIMULATIONS = 10000;
	final int PERCENTAGE = SIMULATIONS / 100;
    
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
    
    public int getSimulation() {
    	return SIMULATIONS;
    }
    
    public int getPercentage() {
    	return PERCENTAGE;
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
    
    public void simulateFixed(Card[] originalOpponent, Card[] originalPlayer) {
    	Card[][] opponent = new Card[SIMULATIONS][originalOpponent.length];
    	Card[][] player = new Card[SIMULATIONS][originalPlayer.length];
    	int[] results;
    	Battleground sim = new Battleground();
    	
        for (int i = 0; i < opponent.length; i++) {
        	for (int j = 0; j < originalOpponent.length; j++) {
        		opponent[i][j] = originalOpponent[j].makeCopy();
        	}
        }
        for (int i = 0; i < player.length; i++) {
        	for (int j = 0; j < originalPlayer.length; j++) {
        		player[i][j] = originalPlayer[j].makeCopy();
        	}
        }
        
        results = sim.playSimulation(opponent, player);

        float drawPercentage = results[0] / PERCENTAGE;
        float winPercentage = results[1] / PERCENTAGE;
        float lossPercentage = results[2] / PERCENTAGE;
        
        System.out.println("Win chance: %" + winPercentage);
        System.out.println("Loss chance: %" + lossPercentage);
        System.out.println("Draw chance: %" + drawPercentage);
        
    }
    
    public static void main (String[] args) {
        Buddy bob = new Buddy();
        bob.simulateRandoms(bob.getSimulation());
        float winPercentage = bob.getWins() / bob.getPercentage();
        float lossPercentage = bob.getLosses() / bob.getPercentage();
        float drawPercentage = bob.getDraws() / bob.getPercentage();

        System.out.println("Wins: %" + winPercentage);
        System.out.println("Losses: %" + lossPercentage);
        System.out.println("Draws: %" + drawPercentage);
    }
}
