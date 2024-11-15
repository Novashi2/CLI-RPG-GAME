package Model;

import java.util.*;

// A child class can also be another parent class
// used to indicate that a class is inheriting from a superclass
// A superclass is a class that is derived from another class (i.e. DungeonEntity)
public abstract class Monster extends DungeonEntity {

    // Making private variables fpr randomized healing
    private int myHealMin; // Minimum healing amount
    private int myHealMax; // Maximum healing amount
    private double myHealChance; // Probability of healing occurring

    protected Monster(String theChrName, int theHp, int theDamage, int theHealMin, int theHealMax, double theHealChance) {
        // The super brings the parameters from the parent class (DungeonEntity) to the child class (Monster)
        super(theChrName, theHp, theDamage);

        // Initializing varibles
        myHealMin = theHealMin;
        myHealMax = theHealMax;
        myHealChance = theHealChance;
    }

    // Method for healing with random chance and variable healing amount
    // Example use: Goblin.heal(); 
    // Note that the name Goblin is not based off of charName but the object name in the initialization
    public void heal() {
        Random random = new Random();

        // Check if healing occurs based on the healChance
        if (random.nextDouble() < myHealChance) {
            // Determine random healing amount within specified range
            int healingAmount = myHealMin + random.nextInt(myHealMax - myHealMin + 1);
            int newHp = getHp() + healingAmount;

            System.out.println(getCharName() + " heals for " + healingAmount + " HP. HP is now " + newHp + ".");
            setHp(newHp); // Apply healing
        } else { // If Monster failed the healChance then no healing occurs
            System.out.println(getCharName() + " tried to heal but failed.");
        }
    }
}