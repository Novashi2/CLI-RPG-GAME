import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

public class GameStart {
    public static void main(String[] args) throws FileNotFoundException{

        //instantiates a new Scanner object
        Scanner console = new Scanner(System.in);
        //instantiates a new Random object
        Random r = new Random(); 
        
	Player player = new Player(console);
        // Health that has not been moved out yet.
        int dragonHealth = 100;
        int skeletonHealth = 30;
	
        // Damage   
        int wandDamage = 25; //add effects?
        int dragonDamage = 15;
        int skeletonDamage = 15;




	// gathers information on which path the player will take	
        int dungeonChoice = getDungeonChoice(console);

        // This funciotn is an infinite loop because the battle function and dragon fight will end the game.
        while (true) { 
            if  (player.savePoint == 0) {
                // introduce player to game and get path choice
	    }else if (player.savePoint == 1){
		// dungeon 1
	    } else if(player.savePoint == 2){
                //call dungeon 2
	    }else if (player.savePoint == 3){
		// call dungeon 3
	    } else if (player.savePoint == 4) {
                DungeonFour.theFourthDungeon(console, r); //calls the fourth dungeon method with scanner and random objects
	    } else if (player.savePoint == 5) {
		// call dragon fight
	    }
	    break;
        }
    }

    public static int getDungeonChoice(Scanner console){
	int dungeonChoice = 0;
	String prompt = "Do you want to go down path 1 or 2? ";
	String error = "Please answer with \"1\" or \"2\": ";


        System.out.print(prompt);
	//ensure that user enters integer first time
	while (!console.hasNextInt()){
	    console.nextLine();
	    System.out.print(error);
	}
	dungeonChoice = console.nextInt();
	//verifies input after verifying that value is an integer
	while(dungeonChoice != 1 && dungeonChoice != 2) {
	    System.out.print(error);
	    //ensure that user enters integer first time
	    if (!console.hasNextInt()){
		console.nextLine();
	    } else dungeonChoice = console.nextInt();
	    
	}
	return dungeonChoice;
    }
}
