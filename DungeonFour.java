//the second dungeon on the second dungeon path 
import java.util.Scanner;
import java.util.Random;

public class DungeonFour {
    public static void main(String[] args) {
        
    }

    public static void theFourthDungeon(Scanner s, Random r) {
        //temp scanner var 
        String tempScan = s.nextLine();
        //start 4.1
        System.out.println("You hear a voice whisper from the darkness...but don't know where it's coming from, you continue down the path.");
        System.out.println("The halls in this area start to change,\nthey become warped and your senses dull");
        //hand gamble - 4.2 
        System.out.println("A hand reaches out, you hear a voice, \"Want to gamble?\"");
        System.out.print("y/n");
        tempScan = s.nextLine();
        while(!(tempScan.equalsIgnoreCase("y"))&&!(tempScan.equalsIgnoreCase("n"))) {
            tempScan = s.nextLine(); 
            System.out.println("Please respond with \"y\" or \"n\"");
        }
        if(tempScan.equalsIgnoreCase("y")) {
            //gamble something with the player's stats health for example
        }
    }
}