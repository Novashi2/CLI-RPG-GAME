// This file contains the informatoin for the basic dungeon entity which player and enemy will inherit
import java.util.Scanner;
import java.util.Random;


public class Entity{
    // common general variables
    public int health = 500;
    public String name = "You";
    public String element;
    public ServantLog servants = new ServantLog();
    
    // effects variables
    public int burn = 0;
    public int poison = 0;
    public int regeneration = 0;



    public void attack(Random random, Entity target){
	System.out.println("This should not be used. Please use inheritance and overwrite this function.");
    }
    

    public void leech(Entity target){
	int damage = 15;
	target.health -= damage;
	this.health += damage;
	System.out.println(name + " leeched " + damage + " health from " + target.name.toLowerCase());
    }


    public void elementalAttack(Entity target, Random random, Scanner console, Enemy attacker1, Player attacker2){
	String[] elements = {"", "fire", "lightning", "air", "poison", "earth"};
	int elementNumber = 0; // used to determine attack
	if (element.equals("elder")){
	    elementNumber = random.nextInt(1, elements.length);
	}
	
	String targetNote = "";
	String servantNote = ""; // this will be added to the servant note in the for loop
	int damage = 50; // default damage
	int burnIncrement = 0;
	int poisonIncrement = 0;
	int regenerationIncrement = 0;
	
	boolean isLightning = element.equals("lightning") || elements[elementNumber].equals("lightning");
	boolean isAir = element.equals("air") || elements[elementNumber].equals("air");


	if (element.equals("fire") || elements[elementNumber].equals("fire")){
	    targetNote = name + " unleashed a wave of fire, dealing " + damage + " damage and setting " + target.name.toLowerCase() + " on fire";
	    servantNote = " was also caught in the fire and took " + damage + " damage.";
	    burnIncrement = 15;
	} else if (isLightning){
	    damage = 80;
	    targetNote = name + " unleashed a torrent of lightning, dealing " + damage + " damage and shocking " + target.name.toLowerCase();
	    servantNote = " also got shocked and took " + damage + " damage.";
	} else if (isAir){
	    damage = 80;
	    targetNote = name + " created a storm, dealing " + damage + " damage.";
	    servantNote = " also got caught in the storm and was dealt " + damage + " damage.";
	} else if (element.equals("poison") || elements[elementNumber].equals("poison")){
	    poisonIncrement = 10;
	    targetNote = name + " unleashed a wave of poison, dealing " + damage + " damage and poisoned " + target.name.toLowerCase() + ".";
	    servantNote = " also got poisoned and took " + damage + " damage.";
	} else if (element.equals("earth") || elements[elementNumber].equals("earth")){
	    regenerationIncrement = 13;
	    targetNote = name + " created an earthquake dealing " + damage + " damage to " + target.name.toLowerCase();
	    servantNote = " was also shook by an earthquake and took " + damage + " damage.";
	}


	target.health -= damage;
	target.burn += burnIncrement;
	target.poison += poisonIncrement;
	regeneration += regenerationIncrement;

	System.out.println(targetNote);

	target.servants.damage(servantNote, damage, burnIncrement, poisonIncrement);
	
    }

    public void dragonBite(Entity target){
	int damage = 50;
	target.health -= damage;
	System.out.println(name + " bit " + target.name.toLowerCase() + " and dealt " + damage + " damage.");
    }

    public void tailWhip(Entity target){
	int damage = 60;
	target.health -= damage;

	String posessive = null;
	if (name == "You") posessive = "your";
	else posessive = "its";
	System.out.println(name + " threw " + target.name + " with " + posessive + " tail and dealt " + damage + " damage.");
    }

    public void addScales(int scales){
	System.out.println("FAILED TO ADD SCALES");
	System.out.println(scales);
    }
}
