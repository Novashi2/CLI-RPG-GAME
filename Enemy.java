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

/*-----------------------------------This contains information for the regular dragon enemy----------------------------------------*/

    public void setDragon(){
	
    }


}
