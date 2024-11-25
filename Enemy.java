// This file stores information for enemy objects
import java.util.Random;


public class Enemy{
    
	
    //enemy fields
    public String title; // name enemy may hav ehad before if it was a player
    public String type; // what the enemy is
    public String name; // total name
    public int health;

    // effects variables
    public int burn = 0;
    public int poison = 0;

	//Enemy constructor 
    public Enemy(String type, int health) {
	this.type = type;
    	this.health = health; 
    } 
    


/*----------------------------------------------------General Enemy Data-----------------------------------------------------------*/
    

    // This function determines which attack function will be used by
    // comparing the type variable to different possinilitues
    public void attack(Random rand, Player player){
	if (type.equals("spider")) spiderAttack(rand, player);	
    }



    // Although there are still effects for the enemy, I don't believe that they will become large enough to give it its own section.
    // Therefore, the dealEffects function is int the general section of this file.
    public void dealEffects(){
	int damage;
	if (burn > 0){
	    damage = 3;
	    burn --;
	    health -= damage;
	    System.out.println("You have been burnt for " + damage + " damage.");
	}
	if (poison > 0){
	    damage = poison * 5;
	    poison --;
	    health -= damage;
	    System.out.println("The poison in your body dealt " + damage + " damage to you.");
	}
    }





/*---------------------------------------This section contains information for the spider type-------------------------------------*/
    public void setSpider(){
	health = 50;
	type = "spider";
    }

    public void spiderAttack(Random rand, Player player){
	int decider = rand.nextInt(100) + 1;
	if (decider <=10) spiderCacoon(player, rand);
	else if (decider <= 55) spiderBite(player);
	else spikeShot(player);
    }

    public void spiderBite(Player player){
	int damage = 15;
	player.health -= damage;
	System.out.println("The spider bit you and dealt " + damage + " damage. \nYou are now poisoned.");
	player.poison += 3;
    }
    
    public void spikeShot(Player player){
	int damage = 10;
	player.health -= damage;
	player.poison += 5;
	System.out.println("The spider spat a spine and dealt " + damage + " damage. You are now poisoned");

    }

    public void spiderCacoon(Player player, Random rand){
	String message = "You are still trapped in the cocoon";

	System.out.println("The spider has wrapped you in a silk cocoon. You struggle to break free as the spider keeps attacking you.");


	spiderBite(player);

	for (int i = 0; i < rand.nextInt(5); i++){	    
	    System.out.print(message);
	    spiderBite(player);
	    //possibly add a sleep command here
	}
    }


    // This section contains information for a Skeleton enemy
    

    


}
