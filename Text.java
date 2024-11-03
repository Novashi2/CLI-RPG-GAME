// This file is for text that will be printed.

public class Text{
    

    public static void printIntro(){
	System.out.print("Welcome to ");
    }





    // Below this comment are the print functions for dungeon three. Some of these functions return a value.
    public static String dungeonThreeIntro(Scanner console){
	System.out.println("As you exit the tunnel, you see a mineshaft with the words \"To the underneath\" written on a sign.");
	System.out.println("There is another path that leads away from the shaft down another dark tunnel.");
	System.out.print("Do you want to enter the mineshaft or tunnel? ");
	
	String choice = console.next();

	while (!choice.toLowerCase().equals("mineshaft") && !choice.toLowerCase().equals("tunnel")){//possibly switch to do while loop
	    System.out.print("Please enter \"mineshaft\" or \"tunnel\": ");
	    String choice = console.next();
	}

	return choice;
    }	
}
