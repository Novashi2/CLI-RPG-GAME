import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.File;

public class General {
    public static void main(String[] args) {
    }
    
    // This is the battle function for the program.
    public static void battle(Player player, Enemy enemy, Random random, Scanner console) throws FileNotFoundException, InterruptedException{

	while (player.health > 0 && enemy.health > 0){
	    if (player.inventory.size > 0){
		System.out.println("Do you want to use an item? (y/n): ");
		char choice = yOrN(console);
		if (choice == 'y') player.inventory.useItem(console, player, random);
	    }
	    player.attack(enemy, random, console);
	    enemy.dealEffects();
	    enemy.attack(random, player, console);

	    // adds scales to the player
	    if (random.nextInt(100) < 30) player.addScales(random.nextInt(1,50));
	    player.dealEffects(random);
	}

	if (enemy.health <= 0) {
	    System.out.println("You have slain " + enemy.name + ".");
	}

	if (player.health <= 0){
	    player.kill(enemy);
	}
	
    }

    // This method takes a file name and integer. Afterwards, it uses the file name to print the entire file if the textNumber
    // parameter is -1. If textNumber >= 0, sections of the code will be printed. Sections are determined by putting the word "break"
    // on a line at the end of a section of text. 
    public static void printText(String fileName, int textNumber) throws FileNotFoundException{
	Scanner inputFile = new Scanner(new File(fileName));
	if (textNumber == -1){
	    while (inputFile.hasNextLine()) System.out.println(inputFile.nextLine());
	} else {
	    int i = 0;
	    String text = "";
	    System.out.print(text);
	    // gets to text section to print
	    while (i != textNumber && inputFile.hasNextLine()){
		text = inputFile.nextLine();
		if (text.startsWith("break")) i++;
	    }
	    //prints section--fencpost issue occurrs, so the nextLine is called before the while loop starts
	    if (inputFile.hasNextLine()){
		text = inputFile.nextLine();
	    }
	    while (!text.startsWith("break") && inputFile.hasNextLine()){
		System.out.println(text);
		text = inputFile.nextLine();
	    }
	}
    }
    
    // This function 
    public static int pickPath(Scanner console){
	String error = "Please answer with \"1\" or \"2\": ";

	// checks for integer as next input
	while (!console.hasNextInt()){
	    console.nextLine();
	    System.out.print(error);
	}

	int choice = console.nextInt();

	//verifies input after verifying that value is an integer
	while(choice != 1 && choice != 2) {
	    System.out.print(error);
	    //ensure that user enters integer first time
	    if (!console.hasNextInt()){
		console.nextLine();
	    } else choice = console.nextInt();
	}
	return choice;
    }

    public static int getInt(Scanner console, int min, int max){
	
	while (!console.hasNextInt()){
	    console.next();
	    console.nextLine();
	    System.out.print("Please enter the corresponding number for one of the items above: ");
	}
	
	int playerInput = console.nextInt();

	while (playerInput < min || playerInput > max){
	    System.out.print("Please enter the corresponding number for one of the items above: ");
	    if (console.hasNextInt()) playerInput = console.nextInt();
	    else {
		console.next();
	        console.nextLine();
	    }
	}
	System.out.println();
	return playerInput;
    }

    public static char yOrN(Scanner console){
	char choice = console.nextLine().charAt(0);
	while (choice != 'y' && choice != 'n'){
	    System.out.println("Please enter either \"y\" or \"n\": ");
	    choice = console.nextLine().charAt(0);
	}
	System.out.println();
	return choice;
    }
}
