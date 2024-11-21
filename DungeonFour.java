//the second dungeon on the second dungeon path 
import java.util.Scanner;
import java.util.Random;

public class DungeonFour {
    public static void main(String[] args) {

    }
    //method to force the player to say yes or no
    public static String yesno(Scanner s) {
        //temp scanner var 
        String tempScan = s.nextLine();
        tempScan = s.nextLine();
        while(!(tempScan.equalsIgnoreCase("y")) &&! (tempScan.equalsIgnoreCase("n"))) {
            tempScan = s.nextLine(); 
            System.out.println("Please respond with \"y\" or \"n\"");
        }
        return(tempScan);
    }
    
    public static void theFourthDungeon(Scanner s, Random r, Player player) {
        //start 4.1
        System.out.println("You hear a voice whisper from the darkness...but don't know where it's coming from, you continue down the path.");
        System.out.println("The halls in this area start to change,\nthey become warped and your senses dull");
        //hand gamble - 4.2  bn  
        System.out.println("A hand reaches out, you hear a voice, \"Want to gamble?\"");
        System.out.print("y/n");
        if(yesno(s).equalsIgnoreCase("y")) {
            //gamble something with the player's stats health for example
        }

        //fight encounter with a mirror of themselves 
        Enemy hand = new Enemy ("shadow", 50);
        General.battle(player, hand, r, s);
        System.out.println();
        System.out.println("If you made it this far...");
        System.out.println();
        System.out.println("Then you can make it to the end...");
        //after the fight, the dark halls start to light up a bit and player is able to see 2 false options on where to go(we love the illusion of choice here)
        //after going through either a trapdoor or a normal door, they find themselves in an abaondoned armory but the player sees a still steaming coffee cup,
        //it ends up being a mimic that they are forced to fight

        
        Enemy mimic = new Enemy ("mimic", 50);
        General.battle(player, hand, r, s);


    }
}