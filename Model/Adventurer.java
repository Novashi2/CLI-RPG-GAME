package Model;

public class Adventurer {
    

    // MyHp tracks the characters hp
    private int myHp; 

    // Damage that a character will do
    private int myDamage;

    private String myCharName;


    // Base stats of a character, hp and damage
    protected Adventurer (String theCharName, int theHP, int theDamage) {
        myHp = theHP;
        myDamage = theDamage;
        myCharName = theCharName;
    }

    public void takeDamage(int amount) {
        myHp -= amount;
        if (myHp < 0) {
            myHp = 0;
        }
        System.out.println(myCharName + " took " + amount + " damage. HP is now " + myHp + ".");
    }
    public void attack(Adventurer target) {
        System.out.println(myCharName + " attacks " + target.myCharName + " for " + myDamage + " damage.");
        target.takeDamage(myDamage);
    }

    public boolean isAlive() {
        return myHp > 0;
    }

    public int getHp() {
        return myHp;
    }

    public int getDamage() {
        return myDamage;
    }
}

