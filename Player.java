import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Player{
    
    // general data varibles
    public String name;
    public int health = 100; 
    public int savePoint = 0;
    int index;

    // effects varaibles
    public int burn = 0;
    public int poison = 0;

    // abilities variables
    public String[] abilities = {"Peashooter", "Wand", "Sword", null, null, null, null};// null slots added to expand skills
    public int peashooterAmmo = 20;
    
    
    
    // This function prompts the user to enter a number that corresponds to an attack printed in the terminal and then uses that
    // input to determine an attack.
    public void attack(Enemy enemy, Random random, Scanner console){
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
	    console.nextLine();
	}
	
	choice = console.nextInt() - 1;
	
	while(choice < 0 || choice > moveCounter){
	    System.out.print("You must type in one of the corresponding numbers in the list above. Please try again: ");
	    if (console.hasNextInt()) choice = console.nextInt();
	    else {
		console.next();
		console.nextLine();
	    }
	}

	if (abilities[choice].startsWith("Peashooter")) peashooter(enemy, random);
	else if (abilities[choice].startsWith("Wand")) wand(enemy, random);
	else if (abilities[choice].startsWith("Sword")) sword(enemy);	
    }




/*------------------------------------- Below are weapons functions for the player.------------------------------------------------*/
// weapons are called in the attack method


    // The peashooter function uses a random object to decude whether the player will or won't hit the enemy. 
    public void peashooter(Enemy enemy, Random random){
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
    public void wand (Enemy enemy, Random random){ // if we have time, it may be intersting to add more effects, like mind control
	int decider = random.nextInt(1,101);
	int baseDamage = 25;
	if (decider <= 30){ // bonus damage
	    System.out.println("You dealt 90 damage to the " + enemy.name);
	    enemy.health -= 90;
	} else if (decider <= 66){// burn attack
	    int burnCounter = decider - 10;
	    System.out.println("You dealt " + baseDamage + " damage and burnt the " + enemy.name + ".");
	    enemy.burn += burnCounter;
	    enemy.health -= baseDamage;
	} else { // poison attack
	    int poisonCounter = decider - 66;
	    System.out.println("You dealt " + baseDamage + "damage and poisoned the " + enemy.name + ".");
	    enemy.poison += poisonCounter;
	    enemy.health -= baseDamage;
	}
    }
    
    
    public void sword (Enemy enemy){ //possibly add something interesting to this part of the code?
	int damage = 35;
	System.out.println("You dealt " + damage + " damage to the " + enemy.name);
	enemy.health -= damage;
    }




/*-----------------------------------------Here are the effects functions for the player-------------------------------------------*/
    // This function processes the effects variables.
    public void dealEffects(){
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
/*----------These functions are the ones that end the game once a player dies, wants to exit at a savepoint, or wins---------------*/

    public void kill(Enemy enemy) throws FileNotFoundException{
	File dragonServants = new File("DragonServants.txt");
	PrintStream servantPrinter = new PrintStream(dragonServants);
	Scanner servantReader = new Scanner(dragonServants);

	System.out.println("You were slain by the " + enemy.name + ".");
	System.out.println("As you die, you feel the Elder Dragon's power turning you into a part of the dungeon.");
	
	String[] dragonServantsLines = new String[100];
	// array magic goes here
		

	System.exit(0);
    }
/*----------------------------Constructor for player object and necessary functions--KEEP AT END-----------------------------------*/
    public void newPlayer(Scanner console){
	System.out.print("Enter the name you want your character to have: ");
	name = console.nextLine();
	
	// input verification
	while (name.indexOf(" ") != -1){
	    System.out.print("Your name must have no spaces. Please try again: ");
	    name = console.nextLine();
	}
    }


    public Player(Scanner console) throws FileNotFoundException{
	File playerFile = new File("Players.txt");
	Scanner playerData = new Scanner(playerFile);
	String[][] playerInfo = new String[2][10000]; // 100 is present because it is an impossibly high number that nobody is likely
					      // to hit
	int playerLines = 2;

	// prints welcome ASCII art
	General.printText("Printable_Text.txt", 0);
	
	// checks to see if data is in file. If so, then the program prompts the user on whether he or she wants to pull up a profile
	if (playerData.hasNext()){
	    System.out.print("Do you have a profile on this computer? (y/n): ");
	    char choice = (console.next()).charAt(0);
	    console.nextLine();
	    
	    // what happens if a player wants to pull a character out of the player file.
	    if (choice == 'y'){
		System.out.println("Here are the stored characters:\n");
		int playerCounter = 0; // counts how many players are stored in the file

		// lists the names of all players in the file
		while (playerData.hasNextLine()){

		    for (int i = 0; i < playerLines; i++){
			playerInfo[i][playerCounter] = playerData.nextLine();
		    }

		    String name = playerInfo[0][playerCounter].substring(0, playerInfo[0][playerCounter].indexOf(' '));
		    playerCounter++;
		    System.out.println(playerCounter + ". " + name);
		}
		
		System.out.println();
		System.out.print("Select the number for the character you want to play: ");
		
		while (!console.hasNextInt()){
		    console.next();
		    System.out.print("Please enter a number from the options above: ");
		}

		int selection = console.nextInt();
		console.nextLine();

		while (selection > playerCounter || selection < 1){
		    System.out.print("Please enter a number from the options above: ");
		    if (console.hasNextInt()) selection = console.nextInt();
		    else{
			console.next();
			console.nextLine();
		    }
		}

		// general information is assigned assigned to the object in the lines below
		playerData = new Scanner(playerInfo[0][selection - 1]);
		name = playerData.next();
		health = playerData.nextInt();
		savePoint = playerData.nextInt();
		poison = playerData.nextInt();
		burn = playerData.nextInt();
		peashooterAmmo = playerData.nextInt();
		index = selection - 1;

		    
		// assigns abilities based on what is in the file. assertion made that the abilities array has enough feilds.
		Scanner abilitiesData = new Scanner(playerInfo[1][selection - 1]);
		int abilitiesCounter = 0;
		while (abilitiesData.hasNext()){
		    abilities[abilitiesCounter] = abilitiesData.next();
		    abilitiesCounter++;
		}
	    }else newPlayer(console);
	}else newPlayer(console);

	System.out.println("\nWelcome, " + name + ".\n");

    }
}
