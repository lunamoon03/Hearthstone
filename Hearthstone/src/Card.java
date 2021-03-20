/**
 * Makes cards. They have Attack, Defense
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    private String name;
    private int attack, defence;
    private final int MIN = 1, MAX = 15;

    /**
     * Constructor for objects of class Card
     */
    public Card(String nm)
    {
        name = nm;
        this.getStats();
    }
    
    public void getStats() {
        attack = (int) (MIN + Math.random() * MAX);
        defence = (int) (MIN + Math.random() * MAX);
    }
    
    public int getDefence() {
        return defence;
    }
    
    public int getAttack() {
        return attack;
    }
    
    public String getName() {
        return name;
    }
    
    public void getHit(int dmg) {
        defence -= dmg;
    }
    
    public boolean isAlive() {
        if (defence <= 0) {
            return false;
        }
        return true;
    }
}