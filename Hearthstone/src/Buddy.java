
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
    
    public void simulateFixed(Battleground b) {
        
    }
    
    public static void main (String[] args) {
        Buddy bob = new Buddy();
        bob.simulateRandoms(10000);

        System.out.printf("Wins: %s%\n", (bob.getWins() / 100));
        System.out.printf("Losses: %s%\n", (bob.getLosses() / 100));
        System.out.printf("Draws: %s%\n", (bob.getDraws() / 100));
    }
}
