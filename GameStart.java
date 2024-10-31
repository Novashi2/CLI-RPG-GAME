import java.util.Scanner;

public class GameStart {
    public static void main(String[] args) {

        //instantiates a new Scanner object
        Scanner console = new Scanner(System.in);

        //placeholder ints
        // Health
        int playerHealth = 150;
        int spiderHealth = 30;
        int dragonHealth = 100;
        int skeletonHealth = 30;

        // Damage 
        int peaDamage = 45; //peaShooter
        int swordDamage = 35; 
        int wandDamage = 25; //add effects?
        int dragonDamage = 15;
        int skeletonDamage = 15;



        System.out.print("First message");

	// gathers information on which path the player will take	
        int dungeonChoice = getDungeonChoice(console);


        //main game loop, game keeps going if the player's health is above 0
        while(playerHealth>0) { // This will need to be changed. The player health should be checked after each turn at a minimum. 
				// It may be better to put this while playerHealth > 0 in every individual loop.
            

            if  (dungeonChoice == 1) {
                //call dungeon 1
                //call dungeon 2
            } else if(dungeonChoice == 2) {
                //call dungeon 3
                //call dungeon 4
	    }
	    break;
        }

        if (playerHealth>0) {
            System.out.println("You win!");
        } 
        else {
            System.out.println("You died!");
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
