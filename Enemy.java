// This file stores information for enemy objects
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Enemy extends Entity{
    
	
    //enemy fields
    public String title; // name enemy may hav ehad before if it was a player
    public String type; // what the enemy is
    int health;

    //Enemy constructor 
    public Enemy(String type, int health) {
	this.type = type;
    	this.health = health; 

	buildName(false);
    } 

    public Enemy(String type, String title, String element, boolean playerHas) throws FileNotFoundException{
	this.type = type;
	this.title = title;
	this.element = element;
	
	buildName(playerHas);

	if (type.equals("spider")) setSpider();
	else if (type.equals("skeleton")) setSkeleton();
	else if (type.equals("draconic hornets")) setHornets();
	else if (type.equals("dragon") || type.equals("Elder Dragon")) setDragon();
    }

    public void buildName(boolean playerHas){
	if (playerHas){
	    this.name = "your";
	} else{
	    this.name = "the";
	}
	if (title == null){
	    this.name += " " + type;
	}else if (element != null){ // dragons can only be fallen players, so there is no need to remove the title variable
	    this.name = title + " the " + element + " " + type;
	}else {
	    this.name = title + " the " + type;
	}
    }
    


/*-----------------------------------------------General Enemy Functions-----------------------------------------------------------*/
    

    // This function determines which attack function will be used by
    // comparing the type variable to different possibilitues
    public void attack(Random rand, Entity player, Scanner console){
	if (type.equals("spider")) spiderAttack(rand, player);
	else if (type.equals("skeleton")) skeletonAttack(player, rand);
	else if (type.equals("dragonic hornets")) hornetAttack(player);
	else if (type.equals("dragon") || type.equals("Elder Dragon")) dragonAttack(player, rand, console);
	else if (type.equals("mimic")) {
	    player.health -= 50;
	    System.out.println(name + "dealt 50 damage to you.");
	}
	else if (type.equals("shadow")) {
		player.health -= 25;
		System.out.println(name + " dealt 25 damage to you.");
	} 
	else if (type.equals("Amalgam")) {
		player.health -= 15;
		System.out.println(name + " dealt 15 damage to you.");
	}
	else if (type.equals("Golem")) {
		player.health -= 20;
		System.out.println(name + " dealt 20 damage to you.");
	}

	System.out.println();
	// servant attack
	servants.attack(rand, player, console);
	
    }



    // Although there are still effects for the enemy, they likely won't be large enough to have and effects section.
    // Therefore, the dealEffects function is int the general section of this file.
    public void dealEffects(){
	int damage;
	if (burn > 0){
	    damage = 3;
	    burn --;
	    health -= damage;
	    System.out.println(name + " was burnt for " + damage + " damage.");
	}
	if (poison > 0){
	    damage = poison * 2;
	    poison --;
	    health -= damage;
	    System.out.println("The poison in " + name + "'s body dealt " + name + " " + damage + " damage.");
	}
	if (regeneration > 0){
	    int bonusHealth = 2 * regeneration;
	    regeneration --;
	    health += bonusHealth;
	    System.out.println(name + " regenerated " + bonusHealth + " health.");
	}

	servants.dealEffects();
    }

    // The function below makes the enemy drop an item.
    public void drop(Player player, Random random, Scanner console, String[] drops, boolean makeRandom, int choices) throws FileNotFoundException{ 
	if (makeRandom){
	    player.inventory.addItem(drops[random.nextInt(drops.length)], console, player, random);
	}else { 
	    
	    int choiceNumber = 0;
	    while (choiceNumber < choices){
		// Prints options and prompts you to choose.
		System.out.println("Here is a list of " + name + "'s drops:");
		System.out.println();
		
		int dropsIndex = 0;
		while (dropsIndex < drops.length && drops[dropsIndex] != null){
		    System.out.println(dropsIndex + 1 + ". " + drops[dropsIndex]);
		    dropsIndex ++;
		}
		System.out.println();
		System.out.print("Select a number from the list above: ");
		
		int choice = General.getInt(console, 1, drops.length) - 1;
		
		// The lines below use and remove the item from the drops array.
		player.inventory.addItem(drops[choice], console, player, random);
		
		for (int i = choice; i < drops.length - 1; i++){
		    drops[i] = drops[i + 1];
		}
		drops[drops.length - 1] = null;
		
		choiceNumber++;
		if (choices > 1) System.out.println("You can select " + (choices - choiceNumber) + " more items from the drops.\n");
	    }
	}
    }
		
/*---------------------------------------This section contains information for the spider type-------------------------------------*/
   
    //the spider can attack with leech, which can be found in the entity parent class
    public void setSpider(){
	health = 90;
    }

    public void spiderAttack(Random rand, Entity player){
	int decider = rand.nextInt(100) + 1;
	if (decider <=10) spiderCacoon(player, rand);
	else if (decider <= 55) spiderBite(player);
	else leech(player);
    }

    public void spiderBite(Entity player){
	int damage = 15;
	player.health -= damage;
	System.out.println(name + " bit " + player.name + " and dealt " + damage + " damage, poisoning " + target.name + ".");
	player.poison += 3;
    }
    
    public void spiderCacoon(Entity player, Random rand){
	String message = "You are still trapped in the cocoon.";

	System.out.println(name + " wrapped you in a silk cocoon. You struggle to break free as the spider keeps attacking you.");


	spiderBite(player);

	for (int i = 0; i < rand.nextInt(5); i++){	    
	    System.out.println(message);
	    spiderBite(player);
	    //possibly add a sleep command here
	}
    }



/*---------------------------------This section contains information for a Skeleton enemy------------------------------------------*/

    public void setSkeleton(){
	health = 100;
    }
    
    public void skeletonAttack(Entity player, Random random){
	int decider = random.nextInt(100) + 1;
	if (decider <= 40) soulArrow(player);
	else if (decider <=70) fireArrow(player);
	else poisonArrow(player);
    }
    
    public void soulArrow(Entity player){
	int damage = health;
	player.health -= damage;
	health *= 2;
	System.out.println(name + " shot a soul arrow and dealt " + damage + " damage.");
    }

    public void fireArrow(Entity player){
	int damage = 25;
	player.health -= damage;
	player.burn += 5;
	System.out.println(name + " shot a fire arrow and dealing " + damage + " damage and burning " + player.name + ".");
    }

    public void poisonArrow(Entity player){
	int damage = 25;
	player.health -= damage;
	player.poison += 20;
	System.out.println(name + " shot a poisoned arrow and dealing " + damage + " damage and poisoning " + player.name  + ".");
    }


/*----------------------------------This contains the information for a hornet hive enemy------------------------------------------*/
    public void setHornets(){
	health = 100;
    }

    public void hornetAttack(Entity player){
	int damage = 15;
	player.health -= damage;
	player.burn += 1;
	System.out.println( name + " swarmed " + player.name + " and dealt " + damage + " damage to you. You are now burnt.");
    }

/*-----------------------------------This contains information for the dragon enemies----------------------------------------*/

    public void setDragon() throws FileNotFoundException{
	this.health = 200;
	if (type.equals("Elder Dragon")){
	    this.health = 700;
	    element = "elder";
	    // The lines below read the dragon's servants into the servants array
	    Scanner servantsReader = new Scanner(new File("DragonServants.txt"));
	    while (servantsReader.hasNextLine()){
		Scanner newServant = new Scanner(servantsReader.nextLine());
		String newTitle = newServant.next();
		String newType = newServant.next();
		String newElement = null;
		if (newType.equals("dragon")) newElement = newServant.next();
		servants.addServant(newType, newTitle, newElement, false); 
	    }
	}

    }

    public void dragonAttack(Entity player, Random random, Scanner console){
	int decision = random.nextInt(100) + 1;
	if (decision <= 50) elementalAttack(player, random, console, this, null);
	else if (decision <= 75) dragonBite(player);
	else if (type.equals("dragon")) tailWhip(player);
	else spikeShot(player);

    }

    public void spikeShot(Entity player){
	int damage = 60;
	player.health -= damage;
	System.out.println("The Elder Dragon threw a spike at you, dealing " + damage + " damage.");
	player.addScales(20);
    }
}
