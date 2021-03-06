/**
 * Makes cards. They have Attack, Defense
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card {
	private String name;
	private String property;
	private int attack;
	private int defence;

	/**
	 * Constructor for objects of class Card
	 * 
	 * @param nm    name of Card
	 * @param at    attack value
	 * @param def   defence value
	 * @param prpty property value
	 */
	public Card(String nm, int at, int def, String prpty) {
		name = nm;
		property = prpty;
		attack = at;
		defence = def;
	}

	/**
	 * Get the defence value.
	 * 
	 * @return int defence
	 */
	public int getDefence() {
		return defence;
	}

	/**
	 * Change the defence value
	 * 
	 * @param newDef new value
	 */
	public void changeDefence(int newDef) {
		defence = newDef;
	}

	/**
	 * Get the attack value
	 * 
	 * @return int attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Chance the attack value
	 * 
	 * @param newAt new value
	 */
	public void changeAttack(int newAt) {
		attack = newAt;
	}

	/**
	 * Get the name value
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the property value
	 * 
	 * @return String property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Check if the card has cleave
	 * 
	 * @return boolean
	 */
	public boolean hasCleave() {
		if (property.equals("cleave"))
			return true;
		return false;
	}

	/**
	 * Check if the card has taunt
	 * 
	 * @return boolean
	 */
	public boolean hasTaunt() {
		if (property.equals("taunt"))
			return true;
		return false;
	}

	/**
	 * Check if the card has death rattle
	 * 
	 * @return boolean
	 */
	public boolean hasRattle() {
		if (property.equals("death rattle"))
			return true;
		return false;
	}

	/**
	 * The card gets hit and reduces defence damage
	 * 
	 * @param dmg damage dealt to Card
	 */
	public void getHit(int dmg) {
		defence -= dmg;
	}

	/**
	 * Checks if the card is alive
	 * 
	 * @return boolean
	 */
	public boolean isAlive() {
		if (defence <= 0)
			return false;
		return true;
	}

	/**
	 * Makes a copy of the Card object with the same attributes
	 * 
	 * @return Card c
	 */
	public Card makeCopy() {
		Card c = new Card(name, attack, defence, property);
		return c;
	}
}