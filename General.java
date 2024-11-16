import java.util.Scanner;
import java.util.Random;

public class General {
    public static void main(String[] args) {
	Player player = new Player();
	Random random = new Random();
	Scanner console = new Scanner(System.in);
	Enemy spider = new Enemy();
	spider.setSpider();
	battle(player, spider, random, console);
    }

    public static void battle(Player player, Enemy enemy, Random random, Scanner console){
	
	while (player.health > 0 && enemy.health > 0){
	    //player.useItem(); -- function is not programmed yet, so it is commented out at the moment
	    player.attack(enemy, random, console);
	    enemy.dealEffects();
	    enemy.attack(random, player);
	    player.dealEffects();
	}

	if (enemy.health <= 0) {
	    System.out.println("You have been killed by the " + enemy.name + ".");
	    // Possible drop
	}

	if (player.health <= 0){
	    System.out.println("You have been killed by the " + enemy.name + ".");
	    System.exit(0); // This will likely be put in a player.death() method
	}
	
    }

	
    
}
