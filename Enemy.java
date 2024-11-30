// This file stores information for enemy objects
import java.util.Random;


public class Enemy{
    
	
    //enemy fields
    public String title; // name enemy may hav ehad before if it was a player
    public String type; // what the enemy is
    public String name; // total name
    public String element;
    public int health;

    // effects variables
    public int burn = 0;
    public int poison = 0;

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
    public void attack(Random rand, Player player){
	if (type.equals("spider")) spiderAttack(rand, player);
	else if (type.equals("skeleton")) skeletonAttack(player, rand);
	else if (type.equals("dragonic hornets")) hornetAttack(player);
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
    }





/*---------------------------------------This section contains information for the spider type-------------------------------------*/
    public void setSpider(){
	health = 90;
    }

    public void spiderAttack(Random rand, Player player){
	int decider = rand.nextInt(100) + 1;
	if (decider <=10) spiderCacoon(player, rand);
	else if (decider <= 55) spiderBite(player);
	else leech(player);
    }

    public void spiderBite(Player player){
	int damage = 15;
	player.health -= damage;
	System.out.println("The spider bit you and dealt " + damage + " damage. \nYou are now poisoned.");
	player.poison += 3;
    }
    
    public void leech(Player player){
	int damage = 15;
	player.health -= damage;
	this.health += damage;
	System.out.println("The spider leeched " + damage + " health from you");

    }

    public void spiderCacoon(Player player, Random rand){
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
    
    public void skeletonAttack(Player player, Random random){
	int decider = random.nextInt(100) + 1;
	if (decider <= 40) soulArrow(player);
	else if (decider <=70) fireArrow(player);
	else poisonArrow(player);
    }
    
    public void soulArrow(Player player){
	int damage = this.health * 5;
	player.health -= damage;
	health *= 2;
	System.out.println(name + " shot a soul arrow and dealt " + damage + " health.");
    }

    public void fireArrow(Player player){
	int damage = 25;
	player.health -= damage;
	player.burn += 5;
	System.out.println(name + " shot a fire arrow and dealt " + damage + " damage. You are now burnt.");
    }

    public void poisonArrow(Player player){
	int damage = 25;
	player.health -= damage;
	player.poison += 8;
	System.out.println(name + " shot a poisoned arrow and dealt " + " damage. You are now poisoned.");
    }


/*----------------------------------This contains the information for a hornet hive enemy------------------------------------------*/
    public void setHornets(){
	health = 40;
    }

    public void hornetAttack(Player player){
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


    public void dragonAttack(Player player, Random random){
	int decision = random.nextInt(100) + 1;
	if (decision <= 50) elementalAttack(player, random, name, "you");
	else if (decision <= 75) dragonBite(player);
	else if (type.equals("dragon")) tailWhip(player);
	else spikeShot(player);

    }

    public void elementalAttack(Player player, Random random, String name, String targetName){
	String[] elements = {"fire", "lightning", "air", "poison", "earth"};
	int i = 0; // used to determine attack
	if (!type.equals("Elder Dragon")){
	    i = random.nextInt(elements.length);
	}else while(!elements[i].equals(element)) i++;

	if (i == 0) fireAttack(player, name, targetName);
	else if (i == 1) lightningAttack(player, random, name, targetName);
	else if (i == 2) airAttack(player, random, name, targetName);
	else if (i == 3) poisonAttack(player, name, targetName);
	else if (i == 4) earthAttack(player, name, targetName);
    }

    public void fireAttack(Player player, String name, String targetName){
	int damage = 50;
	int burnAdder = 10;
	player.burn += burnAdder;
	player.health -= damage;
	System.out.println(name + " unleashed a wave of fire, dealt " + damage + " damage to " + targetName + "and set " + targetName + " on fire." );
	if (player.servants.length != 0){
	    for (int i = 0; i < player.servants.length; i++){
		player.servants[i].health -= damage;
		player.servants[i].burn += burnAdder;
		System.out.println(player.servants[i].name + "was also caught in the fire wave and took " + damage + " damage.");
	    }
	}
    }

    public void lightningAttack(Player player, Random random, String name, String targetName){
	int damage = 50;
	player.health -= damage;
	System.out.println( name + " unleashed a torrent of lightning, dealt " + damage + " damage to " + targetName + ", and shocked " + targetName + ".");
	if (player.servants.length != 0){
	    for (int i = 0; i < player.servants.length; i++){
		player.servants[i].health -= damage;
		System.out.println(player.servants[i].name + "also got shocked and took " + damage + " damage.");
	    }
	}	

	attack(random, player);
    }

    public void airAttack(Player player, Random random, String name, String targetName){
	int damage = 50;
	player.health -= damage;
	System.out.println( name + " created a storm, dealt " + damage + " damage to " + "targetName");
	if (player.servants.length != 0){
	    for (int i = 0; i < player.servants.length; i++){
		player.servants[i].health -= damage;
		System.out.println(player.servants[i].name + "also got tossed around and took " + damage + " damage.");
	    }
	}

	if (random.nextBoolean()) airAttack(player, random, name, targetName);
    }

    public void poisonAttack(Player player, String name, String targetName){
	int damage = 50;
	int poisonAdder = 10;
	player.health -= damage;
	player.poison += poisonAdder;
	System.out.println(name + "unleashed a wave of poison, dealt " + damage + " damage to and poisoning " + targetName + ".");
	if (player.servants.length != 0){
	    for (int i = 0; i < player.servants.length; i++){
		player.servants[i].health -= damage;
		System.out.println(player.servants[i].name + "also got poisoned and took " + damage + " damage.");
	    }
	}
    }

    public void earthAttack(Player player, String name, String targetName){
	int damage = 65;
	int healMarker = 70;
	player.health -= damage;
	health += healMarker;
	System.out.println(name + " created an earthquake dealing " + damage + " damage to " + targetName + ".");
	System.out.println(name + " was healed for " + healMarker + " health.");
	if (player.servants.length != 0){
	    for (int i = 0; i < player.servants.length; i++){
		player.servants[i].health -= damage;
		System.out.println(player.servants[i].name + "was also affected by the earthquake and took " + damage + " damage.");
	    }
	}
    }

    public void dragonBite(Player player){
	int damage = 50;
	player.health -= damage;
	System.out.println(name + " bit you and dealt " + damage + " damage.");
    }

    public void tailWhip(Player player){
	int damage = 60;
	player.health -= damage;
	System.out.println(name + " threw you with its tail and dealt " + damage + " damage.");
    }

    public void spikeShot(Player player){
	int damage = 60;
	player.health -= damage;
	System.out.println("The Elder Dragon threw a spike at you, dealing " + damage + " damage.");
	// Possible add dragon curse function.
    }
}
