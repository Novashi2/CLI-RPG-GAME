// This file contains the information for the dragon Fight
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

public class Dragon {
    public static void main(String[] args) {
        
    }

    public static void run(Player player, Random random, Scanner console) throws InterruptedException, FileNotFoundException{
	System.out.println("\tAfter exiting the portal, you see massive heaps of gold artifacts that the dragon has hoarded over");
	System.out.println("centuries of raiding and cursing the land. As you look around, one of the piles starts to move. You ");
	System.out.println("hear a skull-rattling roar and the Elder dragon appears. You see many shapes move in the shadows.");
	System.out.println();
	General.Continue(console);

	Enemy elderDragon = new Enemy("Elder Dragon", null, null, false);

	General.battle(player, elderDragon, random, console);

	player.slayedDragon = true;

	String[] drops = {"temporary curse cure", "general potion", "dragon crystal"};
	elderDragon.drop(player, random, console, drops, false, 1);
	
	System.out.println("\tAs the dragon fades from existence, a small glowing stone appears from where it once stood.");

	player.win(console);
    }
}
