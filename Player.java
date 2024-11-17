import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Player{
    
    // general data varibles
    public static String name;
    public static int health = 100; 
    public static int savePoint = 0;

    // effects varaibles
    public static int burn = 0;
    public static int poison = 0;

    // abilities variables
    public static String[] abilities = {"Peashooter", "Wand", "Sword", null, null, null, null};// null slots added to expand skills
    public static int peashooterAmmo = 20;
    
    
    
    public static void main(String[] args){
	Random random = new Random();
	Scanner console = new Scanner(System.in);
	Enemy spider = new Enemy();
	spider.setSpider();
	attack(spider, random, console);
    }
    

    // This function prompts the user to enter a number that corresponds to an attack printed in the terminal and then uses that
    // input to determine an attack.
    public static void attack(Enemy enemy, Random random, Scanner console){
	int choice = -1;
	System.out.println("Here are your abilities:\n");
	
	int moveCounter = 0;

	while (moveCounter < abilities.length && !(abilities[moveCounter] == null)){
	    System.out.println((moveCounter + 1) + "." + abilities[moveCounter]);
	    moveCounter++;
	}
	
	System.out.println("\nPeashooter ammo: " + peashooterAmmo);
	System.out.print("Enter the number in front of the attack you want to use: ");
	
	while(!console.hasNextInt()){
	    System.out.print("You must type in one of the corresponding numbers in the list above. Please try again: ");
	    console.next();
	}
	
	choice = console.nextInt() - 1;
	
	while(choice < 0 || choice > moveCounter){
	    System.out.print("You must type in one of the corresponding numbers in the list above. Please try again: ");
	    if (console.hasNextInt()) choice = console.nextInt();
	    else console.next();
	}

	if (abilities[choice].startsWith("Peashooter")) peashooter(enemy, random);
	else if (abilities[choice].startsWith("Wand")) wand(enemy);
	else if (abilities[choice].startsWith("Sword")) sword(enemy);	
    }




/*------------------------------------- Below are weapons functions for the player.------------------------------------------------*/
// weapons are called in the attack method


    // The peashooter function uses a random object to decude whether the player will or won't hit the enemy. 
    public static void peashooter(Enemy enemy, Random random){
    	int damage = 45;
	int hitNumber = random.nextInt(100);
	
	if (peashooterAmmo > 0){
	    if (hitNumber < 50){
		System.out.println("You dealt " + damage + " damage to the " + enemy.name);
		enemy.health -= damage;
	    } else System.out.println("You missed.");

	    peashooterAmmo = peashooterAmmo - 1;
	} else System.out.println("You're out of ammunition");//possibly add this to the player attack function.
    }



    // The wand function adds a random negative effect to the enemy.
    public static void wand (Enemy enemy){
	System.out.println("Will work on soon");
    }
    
    
    public static void sword (Enemy enemy){ //possibly add something interesting to this part of the code?
	int damage = 35;
	System.out.println("You dealt " + damage + " damage to the " + enemy.name);
	enemy.health -= damage;
    }




/*-----------------------------------------Here are the effects functions for the player-------------------------------------------*/
    // This function processes the effects variables.
    public static void dealEffects(){
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


/*----------------------------Constructor for player object and necessary functions--KEEP AT END-----------------------------------*/
    public static void newPlayer(Scanner console){
	System.out.print("Enter the name you want your character to have: ");
	name = console.nextLine();
	
	// input verification
	while (name.indexOf(" ") != -1){
	    System.out.print("Your name must have no spaces. Please try again: ");
	    name = console.nextLine();
	}
    }



    public Player(Scanner console) throws FileNotFoundException{
	File playerFile = new File("Player.txt");
	Scanner playerData = new Scanner(playerFile);
	Scanner playerAssigner = new Scanner(playerFile);

	int playerLines = 2;

	// prints welcome ASCII art
	General.printText("Printable_Text.txt", 0);
	
	// checks to see if data is in file. If so, then the program prompts the user on whether he or she wants to pull up a profile
	if (playerData.hasNext()){
	    System.out.print("Do you have a profile on this computer? (y/n): ");
	    char choice = (console.next()).charAt(0);
	    
	    // what happens if a player wants to pull a character out of the player file.
	    if (choice == 'y'){
		System.out.print("Here are the stored characters:");
		int playerCounter = 0; // counts how many players are stored in the file

		// lists the names of all players in the file
		while (playerData.hasNextLine()){
		    Scanner characterData = new Scanner(playerData.nextLine());
		    playerCounter++;

		    // This is a loop that was added to deal with multiple lines of storage that will appear in later versions
		    for (int i = 1; i < playerLines; i++) playerData.nextInt(); 

		    String name = characterData.next();
		    System.out.println(playerCounter + ". " + name);
		}
		
		System.out.print("Select the number for the character you want to play: ");
		
		// data verification for the player option
		while (!console.hasNextInt()){
		    console.nextLine();
		    System.out.print("Please enter a number from the options above: ");
		}

		int selection = console.nextInt();
		
		while (selection >= playerCounter || selection < 1 || !console.hasNextInt()){
		    System.out.print("Please enter a number from the options above: ");
		    if (console.hasNextInt()) selection = console.nextInt();
		    else console.nextLine();
		}

		// The next set of lines assign the player data in the file to the player object that is being created
		for (int i = 1; i < playerCounter; i++){
		    for (int j = 0; j < playerLines; j ++){
			console.nextLine();
		    }
		}
		

		// general information is assigned assigned to the object in the lines below
		playerData = new Scanner(console.nextLine());
		name = playerData.next();
		health = playerData.nextInt();
		savePoint = playerData.nextInt();
		poison = playerData.nextInt();
		burn = playerData.nextInt();
		peashooterAmmo = playerData.nextInt();
		
		// assigns abilities based on what is in the file
		Scanner abilitiesData = new Scanner(console.nextLine());
		int abilitiesCounter = 0;
		while (abilitiesData.hasNext()){
		    abilities[abilitiesCounter] = abilitiesData.next();
		    abilitiesCounter++;
		}
	    }else newPlayer(console);
	}else newPlayer(console);

    }

}
