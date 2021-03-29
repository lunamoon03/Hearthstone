/**
 * Simulates games of battlegrounds
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Buddy {
	private int wins;
	private int losses;
	private int draws;
	private final int SIMAMT = 10000;
	private final int PERCENTAGE = (SIMAMT / 100);
	private final int[] CARDAMT = { 7, 7 };

	/**
	 * Constructor for objects of class Buddy
	 */
	public Buddy() {
	}

	/**
	 * Gets the total wins
	 * 
	 * @return int wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Gets the total losses
	 * 
	 * @return int losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * Gets the total draws
	 * 
	 * @return int draws
	 */
	public int getDraws() {
		return draws;
	}

	/**
	 * Generates a number of random games
	 * 
	 * @param rounds amount of rounds to simulate
	 */
	public void simulateRandoms(int rounds) {
		for (int i = 0; i < rounds; i++) {
			Battleground b = new Battleground();
			b.fillBoardsRandom(CARDAMT);
			int result = b.playGame(false);
			if (result == 0) {
				draws++;
			} else if (result == 1) {
				wins++;
			} else if (result == 2) {
				losses++;
			}
		}
	}

	/**
	 * Takes two specific Boards and simulates them 10000 ties
	 * 
	 * @param original the original Battleground
	 */
	public void simulateBoard(Battleground original) {
		int[] results = { 0, 0, 0 };
		float drawPercentage;
		float winPercentage;
		float lossPercentage;

		// 10000 iterations
		for (int i = 0; i < SIMAMT; i++) {
			Battleground simulated = original.makeCopy();
			int result;
			result = simulated.playGame(false);

			// Find result and increment results counter
			if (result == 0) // Draw
				results[0]++;
			else if (result == 1) // Win
				results[1]++;
			else if (result == 2)// Loss
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
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		Buddy bob = new Buddy();
		bob.simulateRandoms(10000);

		System.out.println("Wins: %" + (bob.getWins() / 100));
		System.out.println("Losses: %" + (bob.getLosses() / 100));
		System.out.println("Draws: %" + (bob.getDraws() / 100));
	}
}