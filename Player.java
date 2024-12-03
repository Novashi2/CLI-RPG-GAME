import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Player extends Entity{
    
    public static final int PLAYER_LINES = 4;

    // general data variables
    public int savePoint = 0;
    private int index = -1;
    int health = 500;
    String ID = null;

    // player-only effects variables
    int scales = 0;
    private int newScales = 0; // this is private because it ensures that the player is notified that he or she has recieved scales
    boolean stageOne = false;
    boolean stageTwo = false;
    boolean stageThree = false;
    
    // abilities variables
    Inventory inventory = new Inventory();
    public String[] abilities = {"Peashooter", "Wand", "Sword", null, null, null, null};// null slots added to expand skills
    public int peashooterAmmo = 20;

    // gameplay variables that aren't stored
    boolean slayedDragon = false;
    
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
	
	choice = General.getInt(console, 1, moveCounter) - 1;

	if (abilities[choice].startsWith("Peashooter")) peashooter(enemy, random);
	else if (abilities[choice].startsWith("Wand")) wand(enemy, random);
	else if (abilities[choice].startsWith("Sword")) sword(enemy);	
	else if (abilities[choice].startsWith("Leech")) leech(enemy);
	else if (abilities[choice].startsWith("Draconic")) elementalAttack(enemy, random, console, element);
	else if (abilities[choice].startsWith("Bite")) dragonBite(enemy);
	else if (abilities[choice].startsWith("Tail")) tailWhip(enemy);
	System.out.println();
	// servant attack
	servants.attack(random, enemy, console);
    }



/*------------------------------------- Below are weapons functions for the player.------------------------------------------------*/
// weapons are called in the attack method


    // The peashooter function uses a random object to decude whether the player will or won't hit the enemy. 
    public void peashooter(Enemy enemy, Random random){
    	int damage = 45;
	int hitNumber = random.nextInt(100);
	
	if (peashooterAmmo > 0){
	    if (hitNumber < 50){
		System.out.println("You dealt " + damage + " damage to" + enemy.name);
		enemy.health -= damage;
	    } else System.out.println("You missed.");

	    peashooterAmmo --;
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
    public void dealEffects(Random random) throws FileNotFoundException{
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
	if (regeneration > 0){
	    int bonusHealth = 5 * regeneration;
	    regeneration --;
	    health += bonusHealth;
	    System.out.println(name + " regenerated " + bonusHealth + " health.");
	}
	dragonCurse(random);
	servants.dealEffects();
    } 

    // Dragon curse -- scales
    public void addScales(int newScales){
	this.newScales += newScales;
	health += newScales * 2;
	regeneration += 10 * newScales;
	poison -= 5 * newScales;
	burn -= 5 * newScales;
	System.out.println("You have recieved " + newScales + " scales. The scales immediately merge with your skin and you feel a");
	System.out.println("surge in power. Yet, you also sense a bit of your humanity slip away...\n");
    }

    public void dragonCurse(Random random) throws FileNotFoundException{
	if (scales == 0 && newScales > 0){
	    System.out.println("You have recieved the Elder's Dragon's curse. As you get more scales, you will start to become a");
	    System.out.println("dragon.");
	}

	scales += newScales;
	newScales = 0;

	if (scales >= 1000){ // kills player
	    if (!slayedDragon){
		File dragonServants = new File("DragonServants.txt");
		Scanner servantReader = new Scanner(dragonServants);
	    
		String[] currentServants = new String[0];

		while (servantReader.hasNextLine()){
		    currentServants = Arrays.copyOf(currentServants, currentServants.length + 1);
		    currentServants[currentServants.length - 1] = servantReader.nextLine();
		}
	    
		// adds player as a dragon
		currentServants = Arrays.copyOf(currentServants, currentServants.length + 1);
		currentServants[currentServants.length] = ID + " " + "dragon " + element;
	    
		PrintStream servantWriter = new PrintStream(dragonServants);
		for (int i = 0; i < currentServants.length; i++){
		    servantWriter.println(currentServants[i]);
		}
		System.out.println("You became a dragon and are cursed to serve the elder dragon until the mountain's core is found.\n");
	    } else {
		System.out.println("The dragon's curse has been completed. You are now the Elder Dragon");
	    }
	    // print end image
	    System.exit(0);  
	} else if (scales >= 750 && !stageThree){
	    stageThree = true;
	    stageTwo = true;
	    stageOne = true;
	    System.out.println("You have trouble walking on two and have grown wings. Your hands have become talons, and you");
	    System.out.println("have lost all humanity. The only thing keeping you from succumbing to the curse is the urge to get");
	    System.out.println("to the mountain's core.");

	    for (int i = 0; i < abilities.length; i++){
		if (abilities[i] != null && abilities[i].equals("Sword")){
		    abilities[i] = "Bite";
		}else if (abilities[i] != null && abilities.equals("Peashooter")){
		    abilities[i] = "Tail whip";
		}else if (abilities[i] != null && abilities.equals("Wand")){
		    for (int j = i; j < abilities.length - 1; j++){
			abilities[j] = abilities[j + 1];
		    }
		    abilities[abilities.length - 1] = null;
		}
	    }

	    System.out.println("\nYou have lost some of your abilities.\n");
	} else if (scales >= 500 && !stageTwo){
	    stageTwo = true;
	    stageOne = true;
	    System.out.println("You feel a sudden burst of power as any sense of humanity continues to wane.");
	    String[] elements = {"fire", "earth", "air", "poison", "lightning"};
	    element = elements[random.nextInt(elements.length)];
	    
	    // adds ability
	    int abilitiesCounter = 0;
	    while (abilities[abilitiesCounter] != null) abilitiesCounter++;
	    abilities[abilitiesCounter] = "Draconic " + element;
	    System.out.println("You have recieved the " + abilities[abilitiesCounter] + " ability.");
	    System.out.println();
	} else if (scales > 250 && !stageOne){
	    stageOne = true;
	    System.out.println("You notice that everything seems to be smaller. When you look down, you realize the opposite is");
	    System.out.println("A large amount of your body is covered in scales, and your hands look less human and are harder");
	    System.out.println("to move than before...");
	    System.out.println();
	}
    }
	

/*----------These functions are the ones that end the game once a player dies, wants to exit at a savepoint, or wins---------------*/

    public void kill(Enemy enemy) throws FileNotFoundException, InterruptedException{
	File dragonServants = new File("DragonServants.txt");
	Scanner servantReader = new Scanner(dragonServants);

	System.out.println("You were slain by " + enemy.name + ".");
	System.out.println("As you die, you feel the Elder Dragon's power turning you into a part of the dungeon.");
	Thread.sleep(3000);

	// prints death images
	if (enemy.type.equals("spider")) General.printText("Printable_Text.txt", 2);

	String[] dragonServantLines = new String[100];
	
	// puts current information in the file in the array
	int i = 0;
	while (servantReader.hasNextLine()){
	    if (i == dragonServantLines.length){
		dragonServantLines = Arrays.copyOf(dragonServantLines, dragonServantLines.length + 100);
	    }
	    dragonServantLines[i] = servantReader.nextLine();
	    i++;
	}

	// adds player data to DragonServants.txt
	dragonServantLines[i] = ID + " " + enemy.type;

	PrintStream servantPrinter = new PrintStream(dragonServants);
	
	for (int j = 0; j <= i; j++){
	    servantPrinter.println(dragonServantLines[j]);
	}

	System.exit(0);
    }
    
    // This method updates the savePoint variable, saves the player information to the Players.txt file, and prompts the player on 
    // whether he or she wants to exit the game. 
    public void save(int savePoint, Scanner console) throws FileNotFoundException, InterruptedException {
	File playerFile = new File("Players.txt");
	Scanner playerReader = new Scanner(playerFile);
	
	this.savePoint = savePoint; // sets savePoint to the correct value
	
	// the next few lines write the player information to the PLAYERS.txt file. 
	String[][] playerData = new String[PLAYER_LINES][10000];

	int playerIndex = 0;
	while (playerReader.hasNextLine()){
	    for (int i = 0; i < PLAYER_LINES; i++){
		playerData[i][playerIndex] = playerReader.nextLine();
	    }
	    playerIndex++;
	}


	if (index == -1) index = playerIndex; // prevents the player from being written twice in the same file or overwriting data
	// stores general data line in the array
	playerData[0][index] = ID + " " + health + " " + savePoint + " " + poison + " " + burn + " " + peashooterAmmo + " " + scales;
	
	// stores the abilities line in the array
	playerData[1][index] = abilities[0] + " ";
	
	boolean atEndOfAbilities = false;
	int abilitiesIndex = 1;
	while (!atEndOfAbilities){
	    playerData[1][index] += abilities[abilitiesIndex] + " ";
	    abilitiesIndex ++;
	    atEndOfAbilities = abilitiesIndex == abilities.length || abilities[abilitiesIndex] == null;
	}
	
	// stores the items in an array
	playerData[2][index] = "";
	for (int i = 0; i < inventory.size; i++){
	    String item = inventory.items[i];
	    if (item.indexOf(' ') != -1) item = item.replace(" ", "_");
	    playerData[2][index] += item + " ";
	}

	playerData[3][index] = "";
	for (int i = 0; i < servants.servants.length; i++){
	    Enemy servant = servants.servants[i];
	    String type = servant.type;
	    int health = servant.health;
	    int poison = servant.poison;
	    int burn = servant.burn;
	    int regeneration = servant.regeneration;
	    playerData[3][index] += type + " " + health +  " " + poison + " " + burn + " " + regeneration + " ";
	}

	PrintStream playerWriter = new PrintStream(playerFile);
	// Writes everything to the file
	for (int i = 0; i <= playerIndex; i++){
	    for (int j = 0; j < PLAYER_LINES; j++){
		playerWriter.println(playerData[j][i]);
	    }
	    if (playerData[0][i + 1] == null) i = playerIndex + 1;
	}
	
	// sees if user wants to exit the game
	System.out.print("You have reached a savepoint. Do you want to continue? (y/n): ");
	
	char response = console.next().toLowerCase().charAt(0);
	
	while (response != 'y' && response != 'n'){
	    System.out.print("Please type \"y\" or \"n\": ");
	    response = console.next().toLowerCase().charAt(0);
	}

	if (response == 'n') {
	    System.out.println("You are exiting the game.\n");
	    System.exit(0);
	} else {
	    System.out.println("You have chosen to continue...\n");
	    Thread.sleep(5000);
	}
    }


/*----------------------------Constructor for player object and necessary functions--KEEP AT END-----------------------------------*/
    private void newPlayer(Scanner console){
	System.out.print("Enter the name you want your character to have: ");
	ID = console.nextLine();
	
	// input verification
	while (ID.indexOf(" ") != -1){
	    System.out.print("Your name must have no spaces. Please try again: ");
	    ID = console.nextLine();
	}
    }

    public Player(Scanner console, Random random) throws FileNotFoundException{
	File playerFile = new File("Players.txt");
	Scanner playerData = new Scanner(playerFile);
	String[][] playerInfo = new String[PLAYER_LINES][10000]; // 10000 is present because it is an impossibly high number that 
								 // nobody is likely to hit
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

		// lists the IDs of all players in the file
		while (playerData.hasNextLine()){

		    for (int i = 0; i < PLAYER_LINES; i++){
			playerInfo[i][playerCounter] = playerData.nextLine();
		    }

		    String ID = playerInfo[0][playerCounter].substring(0, playerInfo[0][playerCounter].indexOf(' '));
		    playerCounter++;
		    System.out.println(playerCounter + ". " + ID);
		}
		
		System.out.println();
		System.out.print("Select the number for the character you want to play: ");
		
		int selection = General.getInt(console, 1, playerCounter) - 1;

		// general information is assigned assigned to the object in the lines below
		playerData = new Scanner(playerInfo[0][selection]);
		ID = playerData.next();
		health = playerData.nextInt();
		savePoint = playerData.nextInt();
		poison = playerData.nextInt();
		burn = playerData.nextInt();
		peashooterAmmo = playerData.nextInt();
		scales = playerData.nextInt();
		index = selection;
		    
		// assigns abilities based on what is in the file. assertion made that the abilities array has enough feilds.
		Scanner abilitiesData = new Scanner(playerInfo[1][selection]);
		int abilitiesCounter = 0;
		while (abilitiesData.hasNext()){
		    abilities[abilitiesCounter] = abilitiesData.next();
		    abilitiesCounter++;
		}
		// gets items
		Scanner items = new Scanner(playerInfo[2][selection]);
		while (items.hasNext()){
		    String newItem = items.next();
		    if (newItem.indexOf('_') != -1){
			System.out.println("INVALID");
			newItem = newItem.replace("_", " ");
		    }
		    inventory.addItem(newItem, console, this, random);
		}
		// gets servants	
		Scanner servants = new Scanner(playerInfo[3][selection]);
		while (servants.hasNext()){
		    String newType = servants.next();
		    String newElement = null;
		    if (newType.equals("dragon")) newElement = servants.next();
		    int newHealth = servants.nextInt();
		    int newPoison = servants.nextInt();
		    int newBurn = servants.nextInt();
		    this.servants.addServant(newType, null, newElement, true);
		    Enemy newServant = this.servants.servants[this.servants.servants.length - 1];
		    newServant.health = newHealth;
		    newServant.poison = newPoison;
		    newServant.burn = newBurn;
		}     
	    }else newPlayer(console);
	}else newPlayer(console);

	System.out.println("\nWelcome, " + ID + ".\n");
    }
}
