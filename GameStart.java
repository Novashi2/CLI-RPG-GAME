import java.util.Scanner;

public class GameStart {
    public static void main(String[] args) {
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

        //extra variables
        int dungeonDecider = 0; //decides which dungeon to put the player into 1.1, 2.1, then 1.2, 2.2
        String temp = "";
        //instantiates a new Scanner object
        Scanner console = new Scanner(System.in);

        System.out.print("First message");

        //main game loop, game keeps going if the player's health is above 0
        while(playerHealth>0) {
            //call to dungeon 1 OR 2 then to 3/4
            //if statement to determine which dungeon player will go to depending on previous dungeon
            System.out.println("What path do you want to go down? 1 or 2?");
            //finish input verification
            while(((dungeonDecider != 1) && (dungeonDecider != 2))) {
                dungeonDecider = console.nextInt();

            }

            if  (dungeonDecider == 1) {
                //call dungeon 1
                //call dungeon 2
            } 
            else if(dungeonDecider == 2) {
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
}