// This file stores information for enemy objects
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
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
	else if (type.equals("Amalgam")) setAmalgam();
	else if (type.equals("Golem")) setGolem();
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
	else if (type.equals("Amalgam")) amalgamAttack(rand, player);
	else if (type.equals("Golem")) golemAttack(rand, player);
	else if (type.equals("mimic")) {
	    player.health -= 50;
	    System.out.println(name + "dealt 50 damage to you.");
	}
	else if (type.equals("shadow")) {
		player.health -= 25;
		System.out.println(name + " dealt 25 damage to you.");
	} 


	System.out.println();
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
	    damage = poison;
	    poison /= 2;
	    health -= damage;
	    System.out.println("The poison in " + name + "'s body dealt " + name + " " + damage + " damage.");
	}
	if (regeneration > 0){
	    int bonusHealth = regeneration * 2;
	    regeneration /= 2;
	    health += bonusHealth;
	    System.out.println(name + " regenerated " + bonusHealth + " health.");
	}

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
		
		int choice = General.getInt(console, 1, drops.length - choiceNumber) - 1;
		
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
	System.out.println(name + " bit " + player.name + " and dealt " + damage + " damage, poisoning " + player.name + ".");
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
	System.out.println(name + " shot a soul arrow and dealt " + damage + " damage.");
    }

    public void fireArrow(Entity player){
	int damage = 25;
	player.health -= damage;
	player.burn += 5;
	System.out.println(name + " shot a fire arrow and dealing " + damage + " damage and burning " + player.name.toLowerCase() + ".");
    }

    public void poisonArrow(Entity player){
	int damage = 25;
	player.health -= damage;
	player.poison += 20;
	System.out.println(name + " shot a poisoned arrow and dealing " + damage + " damage and poisoning " + player.name.toLowerCase()  + ".");
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


/*-------------------------------------This contains the information for the slime enemy----------------------------------------------------------------------*/


	public void setSlime() {health = 100;}


	public void jellySlam(Entity player){
		int damage = 30;
		player.health -= damage;
		player.poison += 6;
		System.out.println( "The "+name + " knocks you back and dealt" + damage+" damage. You become poisoned.");
	}

	public void slimeShot(Entity player) {
		int damage = 20;
		player.health -= damage;
		player.poison += 8;
		System.out.println("The "+name+" Shoots goop at you and dealt "+damage+"damage. You have been poisoned.");
	}

	public void slimeAttack(Entity player, Random random) {
		int decider = random.nextInt(100) + 1;
		if (decider < 40) slimeShot(player);
		else jellySlam(player);
	}

	/*--------------------------------This contains information for the Werewolf enemy--------------------------------------------------------------*/



	public void setWerewolf(){health = 100;}

	public void savageBite(Entity player){
		int damage = 30;
		player.health -= damage;
		health += 15;
		System.out.println("The werewolf lunges and bites you and dealt "+damage+", it looks envigorated.");
	}

	public void wolfClaw(Entity player){
		int damage = 25;
		player.health -= damage;
		player.poison += 8;
		System.out.println("It runs forward slashing and dealt "+damage+", you feel poisoned");

	}
	public void werewolfAttack(Entity player, Random random){
		int decider = random.nextInt(100) + 1;
		if (decider < 40) wolfClaw(player);
		else savageBite(player);
	}




/*-----------------------------------This contains information for the dragon enemies----------------------------------------*/

    public void setDragon() throws FileNotFoundException{
	this.health = 200;
	if (type.equals("Elder Dragon")){
	    this.health = 250;
	    element = "elder";
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
	int damage = 45;
	player.health -= damage;
	System.out.println("The Elder Dragon threw a spike at you, dealing " + damage + " damage.");
	player.addScales(20);
    }


	public void setAmalgam() {
		health = 120;
	}

	public void amalgamAttack(Random rand, Entity player){
		int decider = rand.nextInt(100) + 1;
		if (decider <=10) Grapple(player, rand);
		else if (decider <= 70) Fleshball(player);
		else leech(player);
		}
	
		public void Fleshball(Entity player){
		int damage = 20;
		player.health -= damage;
		System.out.println(name + " Threw a ball of flesh at " + player.name + " and dealt " + damage + " damage. ");
		}
		
		public void Grapple(Entity player, Random rand){
		String message = "You are still trapped in the Amalgam's embrace.";
	
		System.out.println(name + " enveloped you in itself. You struggle to break free from its embrace.");
	
	
		Fleshball(player);
	
		for (int i = 0; i < rand.nextInt(3); i++){	    
			System.out.println(message);
			Fleshball(player);
		}
		}


		public void setGolem() {
			health = 150;
		}
	
		public void golemAttack(Random rand, Entity player){
			int hitChance = rand.nextInt(101);
			int decider = rand.nextInt(100) + 1;
			if (decider <=30) {
				if (hitChance <= 60) Boulder(player, rand);
				else System.out.println("The Golem threw a boulder and missed!");
			}else Punch(player);
			}
			public void Punch(Entity player){
			int damage = 20;
			player.health -= damage;
			System.out.println(name + " Threw a punch at " + player.name + " and dealt " + damage + " damage. ");
			}
			
			public void Boulder(Entity player, Random rand){
			int damage = 35;
			player.health -= damage;
			System.out.println(name + " launched a boulder at " + player.name + " and dealt " + damage + " damage. ");
			}
		
}
