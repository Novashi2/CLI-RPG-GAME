// Stores information for the servants functions.
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ServantLog{
    
    Enemy[] servants = new Enemy[0];
    boolean playerHas; // determines if servants belong to players
    

    public void addServant(String type, String title, String element, boolean playerHas) throws FileNotFoundException{
	servants = Arrays.copyOf(servants,servants.length + 1);
	servants[servants.length - 1] = new Enemy(type, title, element, playerHas);
    }

    public void attack(Random random, Entity player, Scanner console){
	if (servants.length > 0){
	    // first attack
	    int firstServant = random.nextInt(servants.length);
	    Enemy firstAttacker = servants[firstServant];
	    firstAttacker.attack(random, player, console);
	    System.out.println("1");
	    if (servants.length > 1) {
		int secondServant = random.nextInt(servants.length);
		while (secondServant == firstServant){
		    secondServant = random.nextInt(servants.length);
		}
		servants[secondServant].attack(random, player, console);
	    }
	}
    }

    public void damage(String message,int damage, int burnIncrement, int poisonIncrement){

	for (int i = 0; i < servants.length; i++){
	    Enemy target = servants[i];
	    target.health -= damage;
	    target.poison+= poisonIncrement;
	    target.burn += burnIncrement;
	    System.out.println(target.name + message);
	}
    }

    public void checkDead(Player player, Random random, Scanner console) throws FileNotFoundException{
	for (int i = 0; i < servants.length; i++){
	    Enemy target = servants[i];
	    if (target.health <= 0){
		System.out.println(target.name + " was slain.");
		if (playerHas) drop(target, player, random, console);
		for (int j = i; j < servants.length - 1; j++){
		    servants[j] = servants[j + 1];
		}
		servants = Arrays.copyOf(servants, servants.length - 1);
	    }
	}
    }

    public void drop(Enemy target, Player player, Random random, Scanner console) throws FileNotFoundException{
	String[] drops;
	if (target.type.equals("spider")){
	    String[] newDrops = {"spider egg sack", "poison potion", "fire potion",  "health potion"};
	    drops = newDrops;
	} else if (target.type.equals("skeleton")){
	    String[] newDrops = {"health potion", "poison potion", "regeneration potion", "general potion"};
	    drops = newDrops;
	} else if (target.type.equals("draconic hornets")){
	    String[] newDrops = {"fire potion", "general potion", "swarm creator", "swarm creator", "general potion"};
	    drops = newDrops;
	} else{
	    String[] newDrops = {"fire potion", "general potion", "swarm creator", "poison potoin", "spider egg sack"};
	    drops = newDrops;
	}
	
	target.drop(player, random, console, drops, true, 0);

    }

    public void dealEffects(){
	for (int i = 0; i < servants.length; i++){
	    servants[i].dealEffects();
	}
    }    
}
