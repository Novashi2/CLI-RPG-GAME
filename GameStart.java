import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

public class GameStart {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException{

        //instantiates a new Scanner object
        Scanner console = new Scanner(System.in);
        //instantiates a new Random object
        Random r = new Random(); 
        
	Player player = new Player(console, r);
        // Health that has not been moved out yet.
        int dragonHealth = 100;
        int skeletonHealth = 30;
	
        // Damage   
        int dragonDamage = 15;
        int skeletonDamage = 15;


	


        // This function is an infinite loop because the battle function and dragon fight will end the game.
        while (true) { 
            if  (player.savePoint == 0) {
		General.printText("Printable_text.txt", 1);
		player.savePoint = -1 + 2 * getDungeonChoice(console); // Determines the next path that the player will go down.
	    }else if (player.savePoint == 1){
		// dungeon 1
		player.save(2, console);
	    } else if(player.savePoint == 2){
                //call dungeon 2
		player.save(5, console);
	    }else if (player.savePoint == 3){
		DungeonThree.run(player, r, console);
		player.save(4, console);
	    } else if (player.savePoint == 4) {
                DungeonFour.theFourthDungeon(console, r, player); //calls the fourth dungeon method with scanner and random objects
		player.save(5, console);
	    } else if (player.savePoint == 5) {
		Dragon.run(player, r, console);
	    }
	    //break;
        }
    }

    public static int getDungeonChoice(Scanner console){
	System.out.println("\tYou follow the map that was given to you to the lair's enterance. When you look up from the map,");
	System.out.println("you see the cliff face of a mountain with a cave entrance. You enter into the cave. Inside, there are ");
	System.out.println("signs of the remains of the battles that were fought before. In front of you, there are two tunnels.");
	System.out.println("You hear a roar come from the first tunnel, while the second as a sign that reads \"Only darkness lies");
	System.out.println("past this point\"...");
	System.out.println();

        System.out.print("Do you want to go down the first path (1) or the second path (2)? ");

	return General.pickPath(console);

    }
}
