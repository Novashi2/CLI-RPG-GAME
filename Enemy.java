// This file stores information for enemy objects
import java.util.Random;
import java.util.Scanner;


public class Enemy extends Entity{
    
	
    //enemy fields
    public String title; // name enemy may hav ehad before if it was a player
    public String type; // what the enemy is


    //Enemy constructor 
    public Enemy(String type, int health) {
	this.type = type;
    	this.health = health; 

	buildName();
    } 

    public Enemy(String type, String title, String element){
	this.type = type;
	this.title = title;
	this.element = element;
	
	buildName();

	if (type.equals("spider")) setSpider();
	else if (type.equals("skeleton")) setSkeleton();
	else if (type.equals("draconic hornets")) setHornets();
	else if (type.equals("dragon") || type.equals("Elder Dragon")) setDragon();
    }

    public void buildName(){
	if (title == null){
	    this.name = "the " + type;
	}else if (element != null){ // dragons can only be fallen players, so there is no need to remove the title variable
	    this.name = title + " the " + element + " " + type;
	}else {
	    this.name = title + " the " + type;
	}
    }
    


/*----------------------------------------------------General Enemy Data-----------------------------------------------------------*/
    

    // This function determines which attack function will be used by
    // comparing the type variable to different possinilitues
    public void attack(Random rand, Entity player, Scanner console){
	if (type.equals("spider")) spiderAttack(rand, player);
	else if (type.equals("skeleton")) skeletonAttack(player, rand);
	else if (type.equals("dragonic hornets")) hornetAttack(player);
	else if (type.equals("dragon") || type.equals("elder dragon")) dragonAttack(player, rand, console);
	else if (type.equals("mimic")) {
	    player.health -= 5;
	    System.out.println(name + "dealt 5 damage to you.");
	} 
    }



    // Although there are still effects for the enemy, I don't believe that they will become large enough to give it its own section.
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
	    damage = poison * 5;
	    poison --;
	    health -= damage;
	    System.out.println("The poison in " + name + "'s body dealt " + name + " " + damage + " damage.");
	}
	if (regeneration > 0){
	    int bonusHealth = 5 * regeneration;
	    regeneration --;
	    health += bonusHealth;
	    System.out.println(name + " regenerated " + bonusHealth + " health.");
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
	System.out.println("The spider bit you and dealt " + damage + " damage. \nYou are now poisoned.");
	player.poison += 3;
    }
    
    public void spiderCacoon(Entity player, Random rand){
	String message = "You are still trapped in the cocoon";

	System.out.println("The spider wrapped you in a silk cocoon. You struggle to break free as the spider keeps attacking you.");


	spiderBite(player);

	for (int i = 0; i < rand.nextInt(5); i++){	    
	    System.out.print(message);
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
	int damage = this.health * 5;
	player.health -= damage;
	health *= 2;
	System.out.println(name + " shot a soul arrow and dealt " + damage + " health.");
    }

    public void fireArrow(Entity player){
	int damage = 25;
	player.health -= damage;
	player.burn += 5;
	System.out.println(name + " shot a fire arrow and dealt " + damage + " damage. You are now burnt.");
    }

    public void poisonArrow(Entity player){
	int damage = 25;
	player.health -= damage;
	player.poison += 8;
	System.out.println(name + " shot a poisoned arrow and dealt " + " damage. You are now poisoned.");
    }


/*----------------------------------This contains the information for a hornet hive enemy------------------------------------------*/
    public void setHornets(){
	health = 40;
    }

    public void hornetAttack(Entity player){
	int damage = 15;
	player.health -= damage;
	player.burn += 1;
	System.out.println( name + " swarmed you and dealt " + damage + " damage to you. You are now burnt.");
    }

/*-----------------------------------This contains information for the dragon enemies----------------------------------------*/

    public void setDragon(){
	health = 200;
	if (type.equals("Elder Dragon")) health += 200;
    }


    public void dragonAttack(Entity player, Random random, Scanner console){
	int decision = random.nextInt(100) + 1;
	if (decision <= 50) elementalAttack(player, random, console);
	else if (decision <= 75) dragonBite(player);
	else if (type.equals("dragon")) tailWhip(player);
	else spikeShot(player);

    }

    public void dragonBite(Entity player){
	int damage = 50;
	player.health -= damage;
	System.out.println(name + " bit you and dealt " + damage + " damage.");
    }

    public void tailWhip(Entity player){
	int damage = 60;
	player.health -= damage;
	System.out.println(name + " threw you with its tail and dealt " + damage + " damage.");
    }

    public void spikeShot(Entity player){
	int damage = 60;
	player.health -= damage;
	System.out.println("The Elder Dragon threw a spike at you, dealing " + damage + " damage.");
	// Possible add dragon curse function.
    }
}
