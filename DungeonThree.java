import java.util.Scanner;
import java.util.Random;
import java.lang.InterruptedException;
import java.io.FileNotFoundException;

public class DungeonThree {
    public static void main(String[] args) {
    }

    public static void run(Player player, Random random, Scanner console) throws FileNotFoundException, InterruptedException{
	System.out.println("As you continue down the cave, the pathway becomes cleaner and narrower. Eventually, you see a mine");
	System.out.println("shaft to the side of the path. There is a sign on the shaft that says \"Only danger lies below.\"");
	System.out.println("Another sign hanging from the path's ceiling says \"Turn back before it is too late - The guardian\"");
	System.out.print("\nDo you want to go down the mine shaft (1) or continue walking foward (2)? ");

	int choice = General.pickPath(console);
	
	Enemy spider = new Enemy("spider", null, null, false);
	if (choice == 2){ // kills you for going foward and ignoring the warning
	    System.out.println("As you continue down the path, the ground falls out from underneath you, and you get stuck in a");
	    System.out.println("web. Immediately, a spider starts to attack you out of nowhwere");
	    player.kill(spider);
	} 

	System.out.println("As you continue down the mineshaft, you start to see an increase of webs and eventualy enter a cavern.");
	System.out.println("There is a vague path in the cavern, but most of it is covered in a thick layer of webs.");
	System.out.println("You eventually get stuck in the webs and free yourself just in time to respond to a giant spider fast ");
	System.out.println("approaching you.\n");
	General.Continue(console);

	General.battle(player, spider, random, console);
	String[] spiderDrops = {"spider token", "poison potion", "health potion"};
	spider.drop(player, random, console, spiderDrops, false, 2);

	System.out.println("After cutting through the remaining webs, you enter through a hole you find that leads into a tunnel.");
	System.out.println("You walk through the tunnel until you the surrounding walls start to be made of bones. Shortly after,");
	System.out.println("you enter a wide and spacious cavern. Inside, you see a hollow cavern made out of bones.\n");
	General.Continue(console);
	System.out.println("You hear a rattle and a moments later a \"woosh\" as an arrow flies past your head. You see what seems");
	System.out.println("to be a moving set of bones in the back of the cavern.\n");
	General.Continue(console);


	Enemy skeleton = new Enemy("skeleton", null, null, false);
	General.battle(player, skeleton, random, console);
	String[] skeletonDrops = {"general potion", "poison potion", "fire potion"};
	skeleton.drop(player, random, console, skeletonDrops, false, 3);

	System.out.println("After wandering around the cavern, you see a bright light and decide to follow it. The light brings");
	System.out.println("you down a spacious stony passageway. Unfortunately, you were so focused on the light that you failed");
	System.out.println("to notice an enterance for a giant hornet's nest until you accidentally kick it. Immediately, a swarm");
	System.out.println("of giant, scaly hornets erupt form the ground.\n");
	General.Continue(console);

	Enemy hornetSwarm = new Enemy("draconic hornets", null, null, false);
	General.battle(player, hornetSwarm, random, console);
	String[] swarmDrops = {"fire potion", "regeneration potion", "general potion", "dragon crystal", "dragon crystal"};
	hornetSwarm.drop(player, random, console, swarmDrops, false, 3);
	
	// possibly add small dragon--not the elder dragon
	System.out.println("You continue to follow the light down the hallway until you see a hollow doorway made of steel. It the");
	System.out.println("doorway is an inscription saying \"To the underneath.\" The light illuminates the portal and you walk");
	System.out.println("it.\n");
	General.Continue(console);


    }
}
