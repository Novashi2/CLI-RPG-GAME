import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.File;

public class General {
    public static void main(String[] args) {
    }
    
    // This is the battle function for the program.
    public static void battle(Player player, Enemy enemy, Random random, Scanner console){
	
	while (player.health > 0 && enemy.health > 0){
	    //player.useItem(); -- function is not programmed yet, so it is commented out at the moment
	    player.attack(enemy, random, console);
	    enemy.dealEffects();
	    enemy.attack(random, player);
	    player.dealEffects();
	}

	if (enemy.health <= 0) {
	    System.out.println("You slain the " + enemy.name + ".");
	    // Possible drop
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
}
