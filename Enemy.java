// THis file stores enemy information built for OOP use.

public class Enemy{
    
    String name;
    int health;
    int burn;
    int poison;
    
    // This function determines which attack function will be used by
    // comparing the name variable to different possinilitues
    public static void attack(Random rand, Player player){
	if (name.equals("spider")) spiderAttack(rand, player);
	
    }












    // This section codes a spider option for the enemy 
    public static void setSpider(){
	health = 50;
	name = "spider";
    }

    public static void spiderAttack(Random rand, Player player){
	decider = rand.nextInt(100) + 1;
	if (decider <=10) spiderCacoon(player, rand);
	else if (decider <= 55) spiderBite(player);
	else spikeShot(player);

    }

    public static void spiderBite(Player player){
	int damage = 15;
	player.health -= damage;
	System.out.println("The spider bit you and dealt " + damage + " damage. \nYou are now poisoned.");
	Player.poison += 3;
    }
    
    public static void spikeShot(Player player){
	int damage = 10;
	player.health -= damage;
	Player.poison += 5;
	System.out.println("The spider spat a spine and dealt " + damage + " damage. You are now poisoned");



    }

    public static void spiderCacoon(Player player, Random rand){
	String message = "You are still trapped in the cocoon";

	System.out.println("The spider has wrapped you in a silk cocoon.
		You struggle to break free as the spider keeps attacking you.");


	spiderBite(player);

	for (i = 0; i < rand.nextInt(5); i++){	    
	    System.out.print(message);
	    spiderBite(player);
	    //possibly add a sleep command here
	}

    }


    // This section contains information for a Skeleton enemy
    

    



}
