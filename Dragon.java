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
	System.out.println("hear a skull-rattling roar and the Elder dragon appears. You see many shapes move in the shadows...");
	System.out.println();
	Thread.sleep(3000);

	Enemy elderDragon = new Enemy("elder dragon", null, null, false);

	General.battle(player, elderDragon, random, console);

	// add drop;
	
	System.out.println("As the dragon fades from existence, three pathway appear. You still need to find the core of");
	System.out.println("the mountain. Since you haven't found it yet, you must to explore the tunnels...\n");
	

	int positionNumber = 0;

	positionNumber += choosePath(console, random);

	while (positionNumber % 17 != 0){
	   // drop something at random
	   player.dealEffects();
	   // dragon scales

	   System.out.println("You see three paths in front of you again");
	   positionNumber += choosePath(console, random);
	}

	// win the game
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
	System.out.println("Select the number for the path you want to take: ");

	return options[General.getInt(console, 1, 3) - 1];	
    }
}
