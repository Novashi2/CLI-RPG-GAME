//the second dungeon on the second dungeon path 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

public class DungeonFour {
    //main method only here for testing purposes 

    public static void main(String[] args) {

    }
    //method to force the player to say yes or no
    public static boolean yesno(Scanner s) {
        //scanner var for the user input
        String tempScan = s.nextLine();
        tempScan = s.nextLine();
        while(!(tempScan.equalsIgnoreCase("y")) &&! (tempScan.equalsIgnoreCase("n"))) {
            tempScan = s.nextLine(); 
            System.out.println("Please respond with \"y\" or \"n\"");
        }
        //just returns a boolean to be used in if/else statements rather than compare later
        return(tempScan.equalsIgnoreCase("y"));
    }
    
    public static void theFourthDungeon(Scanner s, Random r, Player player) throws FileNotFoundException, InterruptedException{

        //variable declarations 
        String healthIndicator = "";
        int randomHealth = r.nextInt(10,15);
        int posNeg = r.nextInt(2);
        boolean mimicCheck = false;

        //start 4.1
        System.out.println("You hear a voice whisper from the darkness...but don't know where it's coming from, you continue down the path.");
        System.out.println("The halls in this area start to change,\nthey become warped and your senses dull");

        //hand gamble - 4.2  
        System.out.println("A hand reaches out, you hear a voice, \"Want to gamble?\"");
        System.out.print("y/n");
        //the call of yesno evaluates true if the player enters "y"
        if(yesno(s)) {
            if(posNeg ==0){
                healthIndicator = "gaining ";
                player.health+=randomHealth;
            }
            else{
                healthIndicator = "losing ";
                player.health-=randomHealth;
            }        
            System.out.println("It seems you will be " +healthIndicator + randomHealth + " health");
        }
        else{
            System.out.println("Maybe in another life...");
        }

        //shadow fight 4.3
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

        //4.4 the hallway
        System.out.println();
        System.out.println("The halls illuminate once again ");
        Thread.sleep(2000);
        System.out.println("You can inspect these walls to find that they are made of a rough marble like material");
        Thread.sleep(2000);
        System.out.println("You continue to the end of a hallway to find that you are met with a trapdoor do you wish to enter? y/n");
        Thread.sleep(2000);
        //4.5 mimic or not 
        Enemy mimic = new Enemy ("mimic", 50);
        if(yesno(s)){
        System.out.println("The trapdoor was a mimic!"); 
        General.battle(player, mimic, r, s); // to do this, add an attack line to the Enemy.attack function
        System.out.println("You make it out safely, with nothing even being in the mimic's room");
        }
        else{
            System.out.println("You decide not to enter the trapdoor as it probably has nothing in there anyway.");
            System.out.println("Although, it does feel like someone is still watching you");
        }

        //4.6
        Thread.sleep(2000);
        System.out.println("A room with traps lies ahead, axes swinging and logs");
        Thread.sleep(2000);
        System.out.println("There is a long path of which you must walk along a long hallway or you can once again gamble your luck in this.");
        Thread.sleep(2000);
        System.out.println("Yet another chance to test your luck, go again? y/n ");
        if(yesno(s)){

        }
        else {
        Thread.sleep(12000);
        }

        //4.6.2 If mimic was not grabbed earlier 
        
        if(mimicCheck) {
            System.out.println("You see an odly placed, yet inviting cup of coffee sitting down at the end of this hallway, \n you decide to reach for it in hopes that it can give you some much needed energy for your current conditon");
            General.battle(player, mimic, r, s);
        }
        else{
            System.out.println("You see an odly placed, yet inviting cup of coffee sitting down at the end of this hallway, \n you decide to reach for it in hopes that it can give you some much needed energy for your current conditon");
            player.health+=5;
            System.out.println("You feel nourished! You gain 5 health \n Current health:");
            System.out.println(player.health);
        }

        //4.7 The teleport leading to the dragons lair 
        System.out.println("You find youself surrounded by the souls of the monsters you've slain pushing you down yet another hallway");
        Thread.sleep(2000);
        System.out.println("The souls have led you to a small room complete with a desk and a large stone structure");
        Thread.sleep(2000);
        System.out.println("A large glowing portal illuminates from the stone structure!");
        Thread.sleep(2000);
        System.out.println("You walk into the portal and find yourself surrounded by gold, and look up to hear the roar of the dragon.");

    


    }
}
