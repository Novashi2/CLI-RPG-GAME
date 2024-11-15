package Model;

// Abstact makes it into a parent class
public abstract class DungeonEntity {
    
    // MyHp tracks the characters hp
    // Private becasue we don't want other things interacting with it oustide of the Parent/child class
    private int myHp; 

    // Damage that a character will do
    private int myDamage;

    // Name for the Entity
    private String myCharName;


    // Base stats of a character, hp and damage
    protected DungeonEntity (String theCharName, int theHp, int theDamage) {
        myHp = theHp;
        myDamage = theDamage;
        myCharName = theCharName;
    }

    protected void setHp(int hp) {
        myHp = hp;
    }

    // Subtracts HP based on damage (which can be used for more than just entities)
    public void takeDamage(int amount) {
        myHp -= amount;
        if (myHp < 0) { // if entity takes damage that would put it past 0, then it sets the HP to zero (so we don't get an negative HP #)
            myHp = 0;
        }
        System.out.println(myCharName + " took " + amount + " damage. HP is now " + myHp + ".");
    }
    /*
     * Attack method for an entity to attack another entity
     * Example use: Goblin.attack(Hero)
     */
    public void attack(DungeonEntity target) {
        System.out.println(myCharName + " attacks " + target.myCharName + " for " + myDamage + " damage.");
        target.takeDamage(myDamage);
    }

    // Checks if an entity is alive
    public boolean isAlive() {
        return myHp > 0;
    }

    public String getCharName() {
        return myCharName;
    }

    public int getHp() {
        return myHp;
    }

    public int getDamage() {
        return myDamage;
    }
}

