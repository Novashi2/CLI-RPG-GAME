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
	System.out.println("After a closer investigation, you notice that these are the remnants of those who have been slain by");
	System.out.println("Elder Dragon and its servants.");
	System.out.println();
	General.Continue(console);

	Enemy elderDragon = new Enemy("Elder Dragon", null, null, false);

	General.battle(player, elderDragon, random, console);

	player.slayedDragon = true;

	String[] drops = {"temporary curse cure", "general potion", "dragon crystal"};
	elderDragon.drop(player, random, console, drops, false, 1);
	
	System.out.println("\tAs the dragon fades from existence, three pathway appear. You still need to find the core of");
	System.out.println("the mountain. Since you haven't found it yet, you must to explore the tunnels...\n");

	int positionNumber = 0;
	positionNumber += choosePath(console, random);

	Enemy chest = new Enemy("chest", 1);
	
	String[] chestDrops = {"dragon crystal", "general potion", "dragon crystal", "temporary curse cure"};
	while (positionNumber % 17 != 0){
	    if (random.nextInt(100) < 40){
		System.out.println("You have found a chest and decided to take an item.\n");
		chest.drop(player, random, console, chestDrops, true, 0);
	    }
	    player.addScales(random.nextInt(30) + 1);
	    player.dealEffects(random);
	   

	   System.out.println("You see three paths in front of you again");
	   positionNumber += choosePath(console, random);
	}

	player.win(console);
    }

    public static int choosePath(Scanner console, Random random){
	int[] pathChoices = {5,2,3};
	    
    	// the line below sets the values for the 
	int firstOption = random.nextInt(pathChoices.length);
	int secondOption = random.nextInt(pathChoices.length);
	   
	while (secondOption == firstOption){
	    secondOption = random.nextInt(pathChoices.length);
	}
	    
        int thirdOption = random.nextInt(pathChoices.length);

	while (thirdOption == secondOption && thirdOption == firstOption){
	    thirdOption = random.nextInt(pathChoices.length);
	}

	int[] options = {firstOption, secondOption, thirdOption};

	System.out.println("You can either: \n");
	System.out.println("1. Take the left pathway\n2. Take the middle pathway.\n3. Take the right pathway.\n");
	System.out.print("Select the number for the path you want to take: ");

	return options[General.getInt(console, 1, 3) - 1];	
    }
}
